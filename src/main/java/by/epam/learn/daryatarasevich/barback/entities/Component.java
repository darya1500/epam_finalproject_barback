package by.epam.learn.daryatarasevich.barback.entities;

public class Component {
    private int ingredientID;
    private String ingredientAmount;
    private String description;
    private int orderNumber;

    public Component(int ingredientID, String ingredientAmount, String description, int orderNumber) {
        this.ingredientID = ingredientID;
        this.ingredientAmount = ingredientAmount;
        this.description = description;
        this.orderNumber = orderNumber;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(String ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Component{" +
                "ingredientID=" + ingredientID +
                ", ingredientAmount='" + ingredientAmount + '\'' +
                ", description='" + description + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
