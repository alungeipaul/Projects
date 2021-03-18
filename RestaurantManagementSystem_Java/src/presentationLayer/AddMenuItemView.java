package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import businessLayer.MenuItem;

public class AddMenuItemView extends JFrame {
    private JButton addBaseProduct = new JButton("Add base product");
    private JButton addCompositeProduct = new JButton("Add component product");
    private JTextField nameBox = new JTextField(15);
    private JTextField priceBox = new JTextField(15);
    private JTextField quantityBox = new JTextField(15);
    private JTextField nameCompBox = new JTextField(15);
    private JComboBox<MenuItem> compBox = new JComboBox<MenuItem>();
    private JButton finishCompositeProduct = new JButton("Finish and add composite product");

    public AddMenuItemView(Restaurant restaurant){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8,0));

        JPanel panelName = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel name = new JLabel("Name:");
        panelName.add(name);
        panelName.add(nameBox);

        JPanel panelPrice = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel price = new JLabel("Price:");
        panelPrice.add(price);
        panelPrice.add(priceBox);

        JPanel panelQuantity = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel quantity = new JLabel("Quantity:");
        panelQuantity.add(quantity);
        panelQuantity.add(quantityBox);

        JPanel addBaseButton = new JPanel();
        addBaseButton.setLayout(new GridLayout(1,0));
        addBaseProduct.setPreferredSize(new Dimension(400,50));
        addBaseButton.add(addBaseProduct);

        fillCompBox(restaurant,0);

        JPanel panelCompName = new JPanel();
        panelCompName.setLayout(new FlowLayout());
        JLabel nameComp = new JLabel("Composite product name:");
        panelCompName.add(nameComp);
        panelCompName.add(nameCompBox);

        JPanel panelSelect = new JPanel();
        panelSelect.setLayout(new FlowLayout());
        JLabel select = new JLabel("Choose base products:");
        panelSelect.add(select);
        panelSelect.add(compBox);

        JPanel addCompositeButton = new JPanel();
        addCompositeButton.setLayout(new GridLayout(1,0));
        addCompositeProduct.setPreferredSize(new Dimension(400,50));
        addCompositeButton.add(addCompositeProduct);

        JPanel finishCompositeButton = new JPanel();
        finishCompositeButton.setLayout(new GridLayout(1,0));
        finishCompositeProduct.setPreferredSize(new Dimension(400,50));
        finishCompositeButton.add(finishCompositeProduct);

        mainPanel.add(panelName);
        mainPanel.add(panelPrice);
        mainPanel.add(panelQuantity);
        mainPanel.add(addBaseButton);
        mainPanel.add(panelCompName);
        mainPanel.add(panelSelect);
        mainPanel.add(addCompositeButton);
        mainPanel.add(finishCompositeButton);

        this.setContentPane(mainPanel);
        this.setTitle("Add Menu Item");
        this.pack();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /**
     * listener for add base product button
     * @param l1
     */
    void addBaseListener(ActionListener l1){
        addBaseProduct.addActionListener(l1);
    }

    /**
     * listener for Add product button which add a product to the composite product
     * @param l2
     */
    void addCompositeListener(ActionListener l2){
        addCompositeProduct.addActionListener(l2);
    }

    /**
     * listener for the Finish and add composite product button
     * @param l3
     */
    void finishCompositeProductListener(ActionListener l3){
        finishCompositeProduct.addActionListener(l3);
    }

    /**
     * gets the text from the name text field box
     * @return
     */
    public String getItemName(){
        return nameBox.getText();
    }

    /**
     * gets the text from the price text field box
     * @return
     */
    public String getItemPrice(){
        return priceBox.getText();
    }
    /**
     * gets the text from the quantity text field box
     * @return
     */
    public String getItemQuantity(){
        return quantityBox.getText();
    }

    /**
     * gets the text from the name of composite product text field box
     * @return
     */
    public String getNameComp() {
        return nameCompBox.getText();
    }

    /**
     * gets the elements from the combo box where all the products are available for adding a composite product
     * @return
     */
    public JComboBox<MenuItem> getCompBox() {
        return compBox;
    }

    /**
     * the function actualise the combo box with the possible selections for a composite product
     * @param restaurant represents the source of all the products that have been already added to the menu
     * @param size it is used to know the actual size of the combo box
     */
    public void fillCompBox(Restaurant restaurant, int size){
        ArrayList<MenuItem> menu = restaurant.getRestMenu();

        for(int i=size ; i< menu.size() ; i++){
            if(menu.get(i) instanceof BaseProduct)
                compBox.addItem(menu.get(i));
        }
    }
}
