package model;

import dao.WarehouseDAO;

public class Orders extends WarehouseDAO<Orders> {
    private int idOrder;
    private int idProduct;
    private int idClient;
    private int ok = 0;
    private int quantityOrder;
    private float totalPrice;

    public Orders(){

    }

    public Orders(int idOrder, int idProduct, int idClient, int quantityOrder){
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.idClient = idClient;
        this.quantityOrder = quantityOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
