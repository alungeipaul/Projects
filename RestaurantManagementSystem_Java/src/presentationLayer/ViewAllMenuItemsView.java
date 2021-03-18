package presentationLayer;

import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ViewAllMenuItemsView extends JFrame {

    /**
     * the constructor builds a table with all the menu items products and their prices
     * @param menuItems gives all the menu items to the be written in the table
     */
    public ViewAllMenuItemsView(ArrayList<MenuItem> menuItems){
        String[] columns = {"Item Name","Item Price"};
        DefaultTableModel myTable = new DefaultTableModel();
        myTable.setColumnIdentifiers(columns);
        Object[] obj = new Object[2];
        for(MenuItem menuItem : menuItems){
            if(menuItem instanceof CompositeProduct){
                obj[0] = menuItem.toString();
            }
            else
                obj[0] = menuItem.getName();
            obj[1] = menuItem.getPrice();
            myTable.addRow(obj);
        }
        JTable myTable1 = new JTable(myTable);
        JScrollPane myScrollPane = new JScrollPane();
        myScrollPane.setBounds(250, 100, 600, 400);
        myScrollPane.setViewportView(myTable1);
        getContentPane().add(myScrollPane);

        this.setTitle("Menu Items");
        this.pack();
    }

}
