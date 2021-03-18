package businessLayer;

import presentationLayer.ChefView;
import dataLayer.FileWriter;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

public class Restaurant extends Observable implements IRestaurantProcessing, Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<MenuItem> restMenu;
    private ArrayList<Order> restOrders;
    private ArrayList<CompositeProduct> compositeProducts;
    private HashMap<Order, ArrayList<MenuItem>> mapOrderItems;
    private int idOrder;

    public Restaurant(){
        this.restMenu = new ArrayList<MenuItem>();
        this.restOrders = new ArrayList<Order>();
        this.compositeProducts = new ArrayList<CompositeProduct>();
        this.mapOrderItems = new HashMap<Order, ArrayList<MenuItem>>();
        this.idOrder = restOrders.size();
    }

    /**
     * creates an new menu item, even if it is a base product or a composite product, because the functions detects which of
     * the parameters is null and creating the other one.
     * @param baseProduct if it is not null, the function will create a baseProduct menuItem
     * @param compositeProduct if it is not null, the function will create a compositeProduct menuItem
     */
    @Override
    public void createNewMenuItem(BaseProduct baseProduct, CompositeProduct compositeProduct) {
        assert (baseProduct != null || compositeProduct != null);

        int prevSize = restMenu.size();
        if(compositeProduct == null) {
            restMenu.add(baseProduct);
        }
        else{
            restMenu.add(compositeProduct);
        }
        assert prevSize == restMenu.size()+1;
    }

    /**
     * the function deletes a product given its name as a parameter
     * @param itemName product to be deleted
     */
    @Override
    public void deteleMenuItem(String itemName) {
        assert(itemName != null);
        int prevSize = restMenu.size();
        MenuItem itemCopy = null;
        for(MenuItem item : restMenu){
            if(item.getName().equals(itemName)){
                itemCopy = item;
            }
        }
        if(itemCopy != null){
            restMenu.remove(itemCopy);
            JOptionPane.showMessageDialog(null, "Menu Item deleted!");
        }
        else{
            JOptionPane.showMessageDialog(null, "No Menu Item to be deleted!");
        }
        assert prevSize == restMenu.size()-1;
    }

    /**
     * the function edits the price of a product by searching it by its name
     * @param itemName the product name to be edited
     * @param itemPrice the replacement price
     */
    @Override
    public void editMenuItem(String itemName, float itemPrice) {
        assert(itemName!=null);
        float prevPrice = 0.0f;
        float postPrice = 0.0f;
        for(MenuItem item : restMenu) {
            if (item.getName().equals(itemName)) {
                prevPrice = item.getPrice();
                item.setPrice(itemPrice);
                postPrice = item.getPrice();
            }
        }
        assert (prevPrice!=postPrice);
    }

    /**
     * creates an order
     * @param order the order to be created
     * @param menuItems the list of menu items of the order
     */
    @Override
    public void createNewOrder(Order order, ArrayList<MenuItem> menuItems) {
        assert(order!=null);
        assert(menuItems.size()!=0);

        restOrders.add(order);

        String notificationChef = "New order has been made\n" +
                "Order "+ order.getOrderId() +
                "\nProducts to be cooked:\n";
        for(MenuItem item : menuItems){
            notificationChef += item.toString() + "\n";
        }
        mapOrderItems.put(order,menuItems);

        /*if(isWellFormed())
        {
            System.out.println("Order is well formed!");
            ChefView chef = new ChefView(this);
            setChanged();
            notifyObservers(notificationChef);
        }
        else
        {
            System.out.println("Order is not well formed!");
        }*/
        if (isWellFormed()) {
            System.out.println("Order is wellFormed!\n");
                ChefView chef = new ChefView(this);
                setChanged();
                notifyObservers(notificationChef);
                clearChanged();

        } else
            System.out.println("Order is not wellFormed!\n");


        this.idOrder = restOrders.size();
    }

    /**
     * computes the total price of an order based on its ID
     * @param idOrder the id of the order
     * @return
     */
    @Override
    public float computePriceOfOrder(int idOrder) {
        float finalPrice=0.0f;
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

        for(Order order: restOrders){
            if(order.getOrderId() == idOrder){

                menuItems = mapOrderItems.get(order);
            }
        }

        for(MenuItem menuItem: menuItems){
            finalPrice += menuItem.getPrice();
        }

        assert(finalPrice!=0);
        return finalPrice;
    }

    /**
     * generates the bill of an order based on its id
     * @param idOrder the id of the order to be billed
     */
    @Override
    public void generateBill(int idOrder) {
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
        Order orderCopy = new Order();
        for(Order order: restOrders){
            if(order.getOrderId() == idOrder){
                orderCopy = order;
            }
        }
        menuItems = mapOrderItems.get(orderCopy);
        FileWriter writer = new FileWriter(orderCopy, menuItems);

    }

    public ArrayList<MenuItem> getRestMenu() {
        return restMenu;
    }

    public void setRestMenu(ArrayList<MenuItem> restMenu) {
        this.restMenu = restMenu;
    }

    public ArrayList<Order> getRestOrders() {
        return restOrders;
    }

    public void setRestOrders(ArrayList<Order> restOrders) {
        this.restOrders = restOrders;
    }

    public ArrayList<CompositeProduct> getCompositeProducts() {
        return compositeProducts;
    }

    public void setCompositeProducts(ArrayList<CompositeProduct> compositeProducts) {
        this.compositeProducts = compositeProducts;
    }

    public HashMap<Order, ArrayList<MenuItem>> getMapOrderItems() {
        return mapOrderItems;
    }

    public void setMapOrderItems(HashMap<Order, ArrayList<MenuItem>> mapOrderItems) {
        this.mapOrderItems = mapOrderItems;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
    public boolean isWellFormed() {
        Set<Order> set = mapOrderItems.keySet();

        for (Order order : set) {
            if (mapOrderItems.get(order) == null)
                return false;
        }
        return true;
    }
}
