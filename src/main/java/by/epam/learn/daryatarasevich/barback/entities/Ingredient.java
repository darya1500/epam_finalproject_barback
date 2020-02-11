package by.epam.learn.daryatarasevich.barback.entities;

import java.util.Objects;

public class Ingredient {
    private int id;
    private String nameEN;
    private String nameRU;
    private boolean action;
    private boolean alcohol;


    public Ingredient(int id, String nameEN, String nameRU, boolean isAction, boolean isAlcohol) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameRU = nameRU;
        this.action = isAction;
        this.alcohol = isAlcohol;
    }

    public Ingredient(String nameEN, String nameRU, boolean isAction, boolean isAlcohol) {
        this.nameEN = nameEN;
        this.nameRU = nameRU;
        this.action = isAction;
        this.alcohol = isAlcohol;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        alcohol = alcohol;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        action = action;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameRU='" + nameRU + '\'' +
                ", isAction=" + action +
                ", isAlcohol=" + alcohol +
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
        Ingredient other = (Ingredient) obj;
        if (id != other.id) {
            return false;
        }
        if (!nameEN.equals(other.nameEN)) {
            return false;
        }
        if (!nameRU.equals(other.nameRU)) {
            return false;
        }
        if (action != other.action) {
            return false;
        }
        if (alcohol != other.alcohol) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEN, nameRU, action, alcohol);
    }
}
