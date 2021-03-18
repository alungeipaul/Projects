package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditMenuItemView extends JFrame {
    private JButton editBaseProduct = new JButton("Edit product");
    private JTextField nameBox = new JTextField(15);
    private JTextField priceBox = new JTextField(15);

    public EditMenuItemView(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,0));

        JPanel panelName = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel name = new JLabel("Name:");
        panelName.add(name);
        panelName.add(nameBox);

        JPanel panelPrice = new JPanel();
        panelName.setLayout(new FlowLayout());
        JLabel price = new JLabel("New price:");
        panelPrice.add(price);
        panelPrice.add(priceBox);

        JPanel editBaseButton = new JPanel();
        editBaseButton.setLayout(new GridLayout(1,0));
        editBaseProduct.setPreferredSize(new Dimension(400,50));
        editBaseButton.add(editBaseProduct);

        mainPanel.add(panelName);
        mainPanel.add(panelPrice);
        mainPanel.add(editBaseButton);

        this.setContentPane(mainPanel);
        this.setTitle("Edit Menu Item");
        this.pack();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /**
     * listener for de edit product button
     * @param l1
     */
    void editBaseListener(ActionListener l1){
        editBaseProduct.addActionListener(l1);
    }

    public String getItemName(){
        return nameBox.getText();
    }
    public String getItemPrice(){
        return priceBox.getText();
    }
}
