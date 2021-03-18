package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class WaiterController {
    private WaiterView view;
    private Restaurant restaurant;
    private ArrayList<MenuItem> menuItems;

    public WaiterController(WaiterView view, Restaurant restaurant){
        this.view = view;
        this.restaurant = restaurant;
        this.menuItems = new ArrayList<MenuItem>();
        this.view.addOrderListener(new AddOrderListener());
        this.view.finishOrderListener(new FinishOrderListener());
        this.view.computePriceListener(new ComputePriceListener());
        this.view.generateBillListener(new GenerateBillListener());
    }

    /**
     * listener of the add product button which adds a product to the order
     */
    class AddOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem selected = (MenuItem) view.getProductBox().getSelectedItem();
            if(selected == null){
                JOptionPane.showMessageDialog(null, "No Product selected!");
            }
            menuItems.add(selected);
            JOptionPane.showMessageDialog(null, "Product added to order!");

        }
    }

    /**
     * listener for the finish order button which finishes and creates an order
     */
    class FinishOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int orderId = Integer.parseInt(view.getOrderId());
            Date today = new Date();
            today.setHours(0);
            int tableNo = Integer.parseInt(view.getTableNo());

            Order newOrder = new Order(orderId,today,tableNo);

                restaurant.createNewOrder(newOrder,menuItems);
            RestaurantSerializator.serialize(restaurant);

                view.setOrderId(""+ restaurant.getIdOrder());

                JOptionPane.showMessageDialog(null, "Order added!");
               // System.out.println(restaurant.getRestOrders().toString());
                menuItems = new ArrayList<MenuItem>();

        }
    }

    /**
     * listener for the compute price button which calculates and show the total price of an order
     */
    class ComputePriceListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int idOrder = Integer.parseInt(view.getIdForBill());

            float computedPrice = restaurant.computePriceOfOrder(idOrder);
            RestaurantSerializator.serialize(restaurant);

            JOptionPane.showMessageDialog(null, "The total price of the Order "+idOrder+" is "+computedPrice);

        }
    }

    /**
     * listener for the generate bill button which creates a txt file with the bill
     */
    class GenerateBillListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int idOrder = Integer.parseInt(view.getIdForBill());
            restaurant.generateBill(idOrder);

            JOptionPane.showMessageDialog(null, "Bill generated!");
        }
    }
}
