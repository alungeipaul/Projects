package model;

import dao.WarehouseDAO;

public class Client extends WarehouseDAO<Client> {
    private int idClient;
    private int ok = 0;
    private String firstName;
    private String lastName;
    private String city;

    public Client(){

    }

    public Client(int idClient, String firstName, String lastName, String city) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int isOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
