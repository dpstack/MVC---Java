package model;

public class ClientModel {

    private String login;
    private String name;
    private String lastname;
    private String email;
    private long cellphone;
    private String password;
    private String date;

    public ClientModel(String login, String name, String lastname, String email, long cellphone, String password,
            String date) {
        this.login = login;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.cellphone = cellphone;
        this.password = password;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ClientModel [cellphone=" + cellphone + ", date=" + date + ", email=" + email + ", lastname=" + lastname
                + ", login=" + login + ", name=" + name + ", password=" + password + "]";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(int cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}