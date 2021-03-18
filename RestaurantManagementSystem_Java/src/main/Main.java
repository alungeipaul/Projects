package main;

import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;
import presentationLayer.RestaurantController;
import presentationLayer.RestaurantView;

public class Main {
    public static void main(String args[]){
        Restaurant restaurant = RestaurantSerializator.deserialize("Restaurant.ser");
        RestaurantView view = new RestaurantView();
        RestaurantController control = new RestaurantController(view,restaurant);
        view.setVisible(true);
    }
}
