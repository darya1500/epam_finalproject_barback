package by.epam.learn.daryatarasevich.barback.entity;

public class Help {
    private int id;
    private String name;
    private String email;
    private String message;
    private HelpStatus status;


    public Help(String name, String email, String message, HelpStatus status) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.status = status;
    }

    public Help(int id, String name, String email, String message, HelpStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelpStatus getStatus() {
        return status;
    }

    public void setStatus(HelpStatus status) {
        this.status = status;
    }
}
