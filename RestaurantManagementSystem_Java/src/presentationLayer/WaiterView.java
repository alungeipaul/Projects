package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WaiterView extends JFrame {
    private JComboBox<MenuItem> productBox = new JComboBox<MenuItem>();
    private JButton addOrder = new JButton("Add product to order");
    private JButton finishOrder = new JButton("Finish order");
    private JButton computePrice = new JButton("Compute price");
    private JButton generateBill = new JButton("Generate bill");
    private JTextField showIdOrder = new JTextField(15);
    private JTextField getNoTable = new JTextField(15);
    private JTextField idOrderForBill = new JTextField(15);

    public WaiterView(Restaurant restaurant){
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(8,0));

        JPanel panel0 = new JPanel();
        panel0.setLayout(new FlowLayout());
        JLabel text1 = new JLabel("Order ID:");
        String id = "" + restaurant.getIdOrder();
        showIdOrder.setEditable(true);
        showIdOrder.setText(id);
        showIdOrder.setEditable(false);
        panel0.add(text1);
        panel0.add(showIdOrder);

        JPanel panelTable = new JPanel();
        panelTable.setLayout(new FlowLayout());
        JLabel textTable = new JLabel("Table No':");
        panelTable.add(textTable);
        panelTable.add(getNoTable);

        fillProductBox(restaurant,0);

        JPanel panelSelect = new JPanel();
        panelSelect.setLayout(new FlowLayout());
        JLabel select = new JLabel("Choose products:");
        panelSelect.add(select);
        panelSelect.add(productBox);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,0));
        addOrder.setPreferredSize(new Dimension(400,50));
        panel1.add(addOrder);

        JPanel finishButton = new JPanel();
        finishButton.setLayout(new GridLayout(1,0));
        finishOrder.setPreferredSize(new Dimension(400,50));
        finishButton.add(finishOrder);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,0));
        computePrice.setPreferredSize(new Dimension(400,50));
        panel2.add(computePrice);

        JPanel panelOrder = new JPanel();
        panelOrder.setLayout(new FlowLayout());
        JLabel textOrder = new JLabel("Order ID:");
        panelOrder.add(textOrder);
        panelOrder.add(idOrderForBill);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,0));
        generateBill.setPreferredSize(new Dimension(400,50));
        panel3.add(generateBill);

        panelMain.add(panel0);
        panelMain.add(panelTable);
        panelMain.add(panelSelect);
        panelMain.add(panel1);
        panelMain.add(finishButton);
        panelMain.add(panelOrder);
        panelMain.add(panel2);
        panelMain.add(panel3);

        this.setContentPane(panelMain);
        this.setTitle("Waiter Tab");
        this.pack();
    }
    void addOrderListener(ActionListener l1){
        addOrder.addActionListener(l1);
    }
    void finishOrderListener(ActionListener l4){
        finishOrder.addActionListener(l4);
    }
    void computePriceListener(ActionListener l2){
        computePrice.addActionListener(l2);
    }
    void generateBillListener(ActionListener l3){
        generateBill.addActionListener(l3);
    }
    public String getOrderId(){
        return showIdOrder.getText();
    }
    public void setOrderId(String id){
        showIdOrder.setEditable(true);
        showIdOrder.setText(id);
        showIdOrder.setEditable(false);
    }
    public String getTableNo(){
        return getNoTable.getText();
    }
    public String getIdForBill(){
        return idOrderForBill.getText();
    }

    public JComboBox<MenuItem> getProductBox() {
        return productBox;
    }

    public void fillProductBox(Restaurant restaurant, int size){
        ArrayList<MenuItem> menu = restaurant.getRestMenu();

        for(int i=size ; i< menu.size() ; i++){
                productBox.addItem(menu.get(i));
        }
    }
}
