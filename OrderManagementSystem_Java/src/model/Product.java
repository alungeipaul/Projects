package model;

import dao.WarehouseDAO;

public class Product extends WarehouseDAO<Product> {
    private int idProduct;
    private int ok = 0;
    private String productName;
    private float price;
    private int quantityProduct;

    public Product(){

    }

    public Product(int idProduct, String productName,int quantityProduct, float price){
        this.idProduct = idProduct;
        this.productName = productName;
        this.price = price;
        this.quantityProduct = quantityProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int isOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
}
