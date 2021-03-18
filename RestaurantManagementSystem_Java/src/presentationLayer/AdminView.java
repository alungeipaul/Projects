package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private JButton addMenuItemButton = new JButton("Add Menu Item");
    private JButton editMenuItemButton = new JButton("Edit Menu Item");
    private JButton deleteMenuItemButton = new JButton("Delete Menu Item");
    private JButton viewAllMenuItemsButton = new JButton("View All Menu Items");
    private JTextField textBox = new JTextField(15);

    public AdminView(){
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(5,0));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,0));

        JLabel text1 = new JLabel("Select operation:",SwingConstants.CENTER);
        panel1.add(text1);

        addMenuItemButton.setPreferredSize(new Dimension(200,50));
        editMenuItemButton.setPreferredSize(new Dimension(200,50));
        deleteMenuItemButton.setPreferredSize(new Dimension(150,50));
        viewAllMenuItemsButton.setPreferredSize(new Dimension(200,50));


        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,0));
        panel2.add(addMenuItemButton);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,0));
        panel3.add(editMenuItemButton);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        JLabel text2 = new JLabel("Name:");
        panel4.add(text2);
        panel4.add(textBox);
        panel4.add(deleteMenuItemButton);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(1,0));
        panel5.add(viewAllMenuItemsButton);

        panelMain.add(panel1);
        panelMain.add(panel2);
        panelMain.add(panel3);
        panelMain.add(panel4);
        panelMain.add(panel5);

        this.setContentPane(panelMain);
        this.setTitle("Administrator Tab");
        this.pack();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /**
     * listener for the Add menu item button
     * @param l1
     */
    void addMenuItemListener(ActionListener l1){
        addMenuItemButton.addActionListener(l1);
    }

    /**
     * listener for the Edit menu item button
     * @param l2
     */
    void editMenuItemListener(ActionListener l2){
        editMenuItemButton.addActionListener(l2);
    }

    /**
     * listener for the Delete item button
     * @param l3
     */
    void deleteMenuItemListener(ActionListener l3){
        deleteMenuItemButton.addActionListener(l3);
    }

    /**
     * listener for the View all menu items button
     * @param l4
     */
    void viewAllMenuItemsListener(ActionListener l4){
        viewAllMenuItemsButton.addActionListener(l4);
    }

    /**
     *
     * @return the name of the item to be deleted written in the Admin Tab
     */
    public String getName(){
        return textBox.getText();
    }

}
