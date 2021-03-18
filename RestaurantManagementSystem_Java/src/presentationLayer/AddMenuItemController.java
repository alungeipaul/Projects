package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddMenuItemController {
    private AddMenuItemView view;
    private Restaurant restaurant;
    private ArrayList<BaseProduct> components;


    public AddMenuItemController(AddMenuItemView view,Restaurant restaurant){
        this.view = view;
        this.components = new ArrayList<BaseProduct>();
        this.restaurant = restaurant;
        this.view.addBaseListener(new AddBaseListener());
        this.view.addCompositeListener(new AddCompositeListener());
        this.view.finishCompositeProductListener(new FinishCompositeProductListener());

    }

    /**
     * this class defines the listener functions of the Add base product button
     */
    class AddBaseListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getItemName();
            float price = Float.parseFloat(view.getItemPrice());
            int quantity = Integer.parseInt(view.getItemQuantity());
            int size = restaurant.getRestMenu().size();

            BaseProduct product = new BaseProduct(name,price,quantity);
            CompositeProduct comp = null;

            restaurant.createNewMenuItem(product,comp);
            RestaurantSerializator.serialize(restaurant);
            view.fillCompBox(restaurant,size);
            JOptionPane.showMessageDialog(null,"Menu Item added !");
            //System.out.println(restaurant.getRestMenu().toString());


        }
    }

    /**
     * adds a base product to the composite product
     */
    class AddCompositeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct selected = (BaseProduct) view.getCompBox().getSelectedItem();
            if(selected == null){
                System.out.println("No product selected");
            }
            components.add(selected);
            JOptionPane.showMessageDialog(null, "Component Product added!");

        }
    }

    /**
     * finalise and adds the composite product composed in the GUI into menu items list
     */
    class FinishCompositeProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameComp();
            int size = restaurant.getRestMenu().size();

            CompositeProduct comp = new CompositeProduct(name,components);
            BaseProduct base = null;
            restaurant.createNewMenuItem(base, comp);
            view.fillCompBox(restaurant, size);
            RestaurantSerializator.serialize(restaurant);
            JOptionPane.showMessageDialog(null, "Menu Item added!");
            //System.out.println(restaurant.getRestMenu().toString());
            components = new ArrayList<BaseProduct>();
        }
    }
}
