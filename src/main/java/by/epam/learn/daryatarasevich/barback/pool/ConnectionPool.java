package by.epam.learn.daryatarasevich.barback.pool;

import by.epam.learn.daryatarasevich.barback.exception.ConnectionPoolException;
import by.epam.learn.daryatarasevich.barback.exception.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {
    POOL;
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> occupiedConnections;
    DBManager dbManager = DBManager.getInstance();
    private String driver = dbManager.getValue(DBParameter.DB_DRIVER);
    private String url = dbManager.getValue(DBParameter.DB_URL);
    private String user = dbManager.getValue(DBParameter.DB_USER);
    private String password = dbManager.getValue(DBParameter.DB_PASSWORD);
    private int poolCapacity = Integer.parseInt(dbManager.getValue(DBParameter.DB_POOLCAPACITY));
    private AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    public AtomicBoolean isCreated() {
        return isCreated;
    }
    /**
     * To init pool data.
     *
     * @throws ConnectionPoolException
     */
    public void initPoolData() throws ConnectionPoolException {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Class.forName(driver);
            occupiedConnections = new ArrayBlockingQueue<>(poolCapacity);
            availableConnections = new ArrayBlockingQueue<>(poolCapacity);
            for (int i = 0; i < poolCapacity; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                availableConnections.add(pooledConnection);
            }
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.sqlexceptioninconnectionpool"));
            throw new ConnectionPoolException(MessageManager.getProperty("message.sqlexceptioninconnectionpool"), e);
        } catch (ClassNotFoundException e) {
            LOGGER.error(MessageManager.getProperty("message.cannotfinddatabasedriverclass"));
            throw new ConnectionPoolException(MessageManager.getProperty("message.cannotfinddatabasedriverclass"), e);
        }
    }
    /**
     * To dispose pool data.
     *
     */
    public void dispose() {
        clearConnectionQueue();
    }
    /**
     * To clear connection queue.
     *
     */
    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(occupiedConnections);
            closeConnectionsQueue(availableConnections);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,MessageManager.getProperty("message.errorclosingtheconnection"));
        }
    }
    /**
     * To get connection.
     *
     * @throws ConnectionPoolException
     */
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            occupiedConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error(MessageManager.getProperty("message.errorconnectingtodatasource"));
            throw new ConnectionPoolException(MessageManager.getProperty("message.errorconnectingtodatasource"), e);
        }
        return connection;
    }
    /**
     * To close connection.
     *
     * @param connection
     * @param statement
     * @param resultSet
     */
    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionnotreturntopool"));
        }
        try {
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.resultsetisnotclosed"));
        }
        try {
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.statementisnotclosed"));
        }
    }
    /**
     * To close connection.
     *
     * @param connection
     * @param statement
     */
    public void closeConnection(Connection connection, Statement statement) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.connectionnotreturntopool"));
        }
        try {
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(MessageManager.getProperty("message.statementisnotclosed"));
        }
    }
    /**
     * To close connections queue
     *
     * @param queue
     * @throws SQLException
     */
    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }
        /**
         * To close connection.
         *
         * @throws SQLException
         */
        public void reallyClose() throws SQLException {
            connection.close();
        }
        /**
         * To clear warnings
         *
         * @throws SQLException
         */
        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }
        /**
         * To close connection
         *
         * @throws SQLException
         */
        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException(MessageManager.getProperty("message.attempingtocloseclosedconnection"));
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!occupiedConnections.remove(this)) {
                throw new SQLException(MessageManager.getProperty("message.errordeletingconnection"));
            }
            if (!availableConnections.offer(this)) {
                throw new SQLException(MessageManager.getProperty("message.errorallocating"));
            }
        }
        /**
         * To commit.
         *
         * @throws SQLException
         */
        @Override
        public void commit() throws SQLException {
            connection.commit();
        }
        /**
         * To create array.
         *
         * @param typeName
         * @param elements
         * @throws SQLException
         */
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }
        /**
         * To create blob.
         *
         * @throws SQLException
         */
        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }
        /**
         * To create clob.
         *
         * @throws SQLException
         */
        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }
        /**
         * To create NClob.
         *
         * @throws SQLException
         */
        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }
        /**
         * To create SQL XML.
         *
         * @throws SQLException
         */
        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }
        /**
         * To create statement.
         *
         * @return statement
         * @throws SQLException
         */
        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }
        /**
         * To create statement.
         *
         * @param resultSetType
         * @param resultSetConcurrency
         * @return statement
         * @throws SQLException
         */
        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }
        /**
         * To create statement.
         *
         * @param resultSetType
         * @param resultSetConcurrency
         * @param resultSetHoldability
         * @return statement
         * @throws SQLException
         */
        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }
        /**
         * To create struct.
         *
         * @param typeName
         * @param attributes
         * @return struct
         * @throws SQLException
         */
        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }
        /**
         * To get auto commit.
         *
         * @return true or false
         * @throws SQLException
         */
        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }
        /**
         * To get auto catalog.
         *
         * @return String
         * @throws SQLException
         */
        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }
        /**
         * To get client info.
         *
         * @return Properties
         * @throws SQLException
         */
        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }
        /**
         * To get client info.
         *
         * @param name
         * @return String
         * @throws SQLException
         */
        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }
        /**
         * To get client holdability.
         *
         * @return Pint
         * @throws SQLException
         */
        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }
        /**
         * To get Metadata.
         *
         * @return DatabaseMetaData
         * @throws SQLException
         */
        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }
        /**
         * To get transaction isolation.
         *
         * @return int
         * @throws SQLException
         */
        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }
        /**
         * To get type map.
         *
         * @return Map
         * @throws SQLException
         */
        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }
        /**
         * To get warnings.
         *
         * @return SQLWarning
         * @throws SQLException
         */
        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }
        /**
         * To check if is closed.
         *
         * @return true or false
         * @throws SQLException
         */
        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }
        /**
         * To check if is read only.
         *
         * @return true or false
         * @throws SQLException
         */
        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }
        /**
         * To check if is valid.
         *
         * @param timeout
         * @return true or false
         * @throws SQLException
         */
        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }
        /**
         * To return native SQL.
         *
         * @param sql
         * @return String
         * @throws SQLException
         */
        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }
        /**
         * To prepare call.
         *
         * @param sql
         * @return CallableStatement
         * @throws SQLException
         */
        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }
        /**
         * To prepare call.
         *
         * @param sql
         * @param resultSetType
         * @param resultSetConcurrency
         * @return CallableStatement
         * @throws SQLException
         */
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }
        /**
         * To prepare call.
         *
         * @param sql
         * @param resultSetType
         * @param resultSetConcurrency
         * @param resultSetHoldability
         * @return CallableStatement
         * @throws SQLException
         */
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }
        /**
         * To prepare statement.
         *
         * @param sql
         * @return PreparedStatement
         * @throws SQLException
         */
        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }
        /**
         * To prepare statement.
         *
         * @param sql
         * @param autoGeneratedKeys
         * @return PreparedStatement
         * @throws SQLException
         */
        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }
        /**
         * To prepare statement.
         *
         * @param sql
         * @param columnIndexes
         * @return PreparedStatement
         * @throws SQLException
         */
        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }
        /**
         * To prepare statement.
         *
         * @param sql
         * @param columnNames
         * @return PreparedStatement
         * @throws SQLException
         */
        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }
        /**
         * To prepare statement.
         *
         * @param sql
         * @param resultSetType
         * @param resultSetConcurrency
         * @return PreparedStatement
         * @throws SQLException
         */
        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }
        /**
         * To prepare statement.
         *
         * @param sql
         * @param resultSetType
         * @param resultSetConcurrency
         * @param resultSetHoldability
         * @return PreparedStatement
         * @throws SQLException
         */
        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }
        /**
         * To rollback.
         *
         * @throws SQLException
         */
        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }
        /**
         * To set auto commit.
         *
         * @param autoCommit
         * @throws SQLException
         */
        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }
        /**
         * To set catalog.
         *
         * @param catalog
         * @throws SQLException
         */
        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }
        /**
         * To set client info.
         *
         * @param name
         * @param value
         * @throws SQLClientInfoException
         */
        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }
        /**
         * To set holdability.
         *
         * @param holdability
         * @throws SQLException
         */
        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }
        /**
         * To set read only.
         *
         * @param readOnly
         * @throws SQLException
         */
        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }
        /**
         * To set savepoint.
         *
         * @return Savepoint
         * @throws SQLException
         */
        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }
        /**
         * To set savepoint.
         *
         * @param name
         * @return Savepoint
         * @throws SQLException
         */
        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }
        /**
         * To set transaction isolation.
         *
         * @param level
         * @throws SQLException
         */
        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }
        /**
         * To check if wrapper is for iface.
         *
         * @param iface
         * @return true or false
         * @throws SQLException
         */
        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
        /**
         * To unwrap.
         *
         * @param iface
         * @return T
         * @throws SQLException
         */
        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }
        /**
         * To abort.
         *
         * @param arg0
         * @throws SQLException
         */
        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);
        }
        /**
         * To get network timeout
         *
         * @return int
         * @throws SQLException
         */
        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }
        /**
         * To get schema.
         *
         * @return String
         * @throws SQLException
         */
        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }
        /**
         * To release savepoint.
         *
         * @param arg0
         * @throws SQLException
         */
        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }
        /**
         * To roll back.
         *
         * @param arg0
         * @throws SQLException
         */
        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }
        /**
         * To set client info.
         *
         * @param arg0
         * @throws SQLClientInfoException
         */
        @Override
        public void setClientInfo(Properties arg0) throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }
        /**
         * To set network timeout.
         *
         * @param arg0
         * @param arg1
         * @throws SQLException
         */
        @Override
        public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }
        /**
         * To set schema.
         *
         * @param arg0
         * @throws SQLException
         */
        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }
        /**
         * To set type map.
         *
         * @param arg0
         * @throws SQLException
         */
        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }
    }
}