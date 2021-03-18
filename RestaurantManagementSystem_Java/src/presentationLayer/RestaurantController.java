package presentationLayer;

import businessLayer.Restaurant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantController {
    private RestaurantView view;
    private Restaurant restaurant;

    public RestaurantController(RestaurantView view, Restaurant restaurant){
        this.view = view;
        this.restaurant = restaurant;
        this.view.adminButtonListener(new AdminListener());
        this.view.chefButtonListener(new ChefListener());
        this.view.waiterButtonListener(new WaiterListener());
    }

    /**
     * listener for the administrator button in the Restaurant Restaurant Manager Tab
     */
    class AdminListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminView adminView = new AdminView();
            AdminController adminCon = new AdminController(adminView,restaurant);
            adminView.setVisible(true);
        }
    }

    /**
     * listener for the chef button in the Restaurant Restaurant Manager Tab
     */
    class ChefListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ChefView chefView = new ChefView(restaurant);
            chefView.setVisible(true);
        }
    }

    /**
     * listener for the waiter button in the Restaurant Restaurant Manager Tab
     */
    class WaiterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            WaiterView waiterView = new WaiterView(restaurant);
            WaiterController waiterCon = new WaiterController(waiterView,restaurant);
            waiterView.setVisible(true);
        }
    }

}
