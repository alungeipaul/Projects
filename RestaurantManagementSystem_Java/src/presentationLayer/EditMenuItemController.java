package presentationLayer;

import businessLayer.MenuItem;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenuItemController {
    private EditMenuItemView view;
    private Restaurant restaurant;

    public EditMenuItemController(EditMenuItemView view,Restaurant restaurant) {
        this.view = view;
        this.restaurant = restaurant;
        this.view.editBaseListener(new EditBaseListener());

        }

    /**
     * listener for the edit product button in the Edit Product Tab, which edits the price of a given product
     */
    class EditBaseListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getItemName();
                float price = Float.parseFloat(view.getItemPrice());

                restaurant.editMenuItem(name,price);
                RestaurantSerializator.serialize(restaurant);
                JOptionPane.showMessageDialog(null, "Menu Item Price edited!");
            }
        }

}
