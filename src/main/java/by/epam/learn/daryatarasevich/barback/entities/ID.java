package by.epam.learn.daryatarasevich.barback.entities;

public class ID {

    public ID() {
    }

    public int createID(int idCurrent) {
        int id = idCurrent + 1;
        return id;
    }
}

