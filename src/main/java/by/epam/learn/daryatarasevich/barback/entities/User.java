package by.epam.learn.daryatarasevich.barback.entities;

import java.util.Objects;

public class User implements java.io.Serializable {
    private int id;
    private String name;
    private String nameRU;
    private String email;
    private String password;
    private String description;
    private Status status;

    public User(String name, String nameRU, String email, String password, String description, Status status) {
        this.name = name;
        this.nameRU = nameRU;
        this.email = email;
        this.password = password;
        this.description = description;
        this.status = status;
    }

    public User(int id, String name, String nameRU, String email, String password, String description, Status status) {
        this.id = id;
        this.name = name;
        this.nameRU = nameRU;
        this.email = email;
        this.password = password;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameRU='" + nameRU + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }
        if (!email.equals(other.email)) {
            return false;
        }
        if (!password.equals(other.password)) {
            return false;
        }
        if (!status.equals(other.status)) {
            return false;
        }
        if (!name.equals(other.name)) {
            return false;
        }
        if (!nameRU.equals(other.nameRU)) {
            return false;
        }
        if (!description.equals(other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameRU, email, password, description, status);
    }
}
