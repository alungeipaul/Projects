package businessLayer;

import dataLayer.RestaurantSerializator;

public class BaseProduct extends MenuItem {
    private static final long serialVersionUID = 1L;
    private int quantity;

    public BaseProduct(String name, float price, int quantity) {
        super(name, price);
        this.quantity = quantity;
    }

    @Override
    public float computePrice() {
        return this.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
