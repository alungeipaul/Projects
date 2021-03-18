package presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import businessLayer.MenuItem;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

import javax.swing.*;

public class AdminController {
    private AdminView view;
    private Restaurant restaurant;

    public AdminController(AdminView view,Restaurant restaurant){
        this.view = view;
        this.restaurant = restaurant;
        this.view.addMenuItemListener(new AddListener());
        this.view.editMenuItemListener(new EditListener());
        this.view.deleteMenuItemListener(new DeleteListener());
        this.view.viewAllMenuItemsListener(new ViewListener());
    }

    /**
     * the listener of the Add menu item button in Admin Tab which opens the AddMenuItem Tab
     */
    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddMenuItemView addView = new AddMenuItemView(restaurant);
            AddMenuItemController addController = new AddMenuItemController(addView,restaurant);
            addView.setVisible(true);
        }
    }

    /**
     * the listener of the Edit menu item  button in Admin Tab which opens the EditMenuItem Tab
     */
    class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            EditMenuItemView editView = new EditMenuItemView();
            EditMenuItemController editController = new EditMenuItemController(editView,restaurant);
            editView.setVisible(true);
        }
    }

    /**
     * the listener of the Delete item  button in Admin Tab
     */
    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getName();

            restaurant.deteleMenuItem(name);
            RestaurantSerializator.serialize(restaurant);

        }
    }

    /**
     * the listener of the View all menu items  button in Admin Tab which opens the View All MenuItem Tab
     */
    class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<MenuItem> menuItems = restaurant.getRestMenu();
            ViewAllMenuItemsView allView = new ViewAllMenuItemsView(menuItems);
            allView.setVisible(true);
        }
    }
}
