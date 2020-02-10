package by.epam.learn.daryatarasevich.barback.entities;

import java.util.List;
import java.util.Objects;

public class Cocktail {
    private int id;
    private String nameEN;
    private String nameRU;
    private User author;
    List<Component> components;

    public Cocktail(String nameEN, String nameRU, User author, List<Component> components) {
        this.nameEN = nameEN;
        this.nameRU = nameRU;
        this.author = author;
        this.components = components;
    }

    public Cocktail(int id, String nameEN, String nameRU, User author, List<Component> components) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameRU = nameRU;
        this.author = author;
        this.components = components;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "id=" + id +
                ", nameEN='" + nameEN + '\'' +
                ", nameRU='" + nameRU + '\'' +
                ", author=" + author +
                ", components=" + components +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cocktail)) return false;
        Cocktail cocktail = (Cocktail) o;
        return id == cocktail.id &&
                nameEN.equals(cocktail.nameEN) &&
                Objects.equals(nameRU, cocktail.nameRU) &&
                author.equals(cocktail.author) &&
                components.equals(cocktail.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEN, nameRU, author, components);
    }
}
