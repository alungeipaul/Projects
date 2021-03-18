package dataLayer;

import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriter  {

    public FileWriter(Order order, ArrayList<MenuItem> menuItems) {
       try {
           PrintWriter writer = new PrintWriter("BillNo"+order.getOrderId()+".txt");
           writer.println("Date: "+ order.getDate());
           writer.println("The Order " + order.getOrderId() + " is served at Table " + order.getTable());
           float totalPrice=0.0f;
           writer.println("Ordered products:");
           for(MenuItem menuItem: menuItems){
               writer.println(menuItem.getName()+" - "+menuItem.getPrice());
               totalPrice+= menuItem.getPrice();
           }
           writer.print("The total price is: "+totalPrice);

           writer.close();
       }catch(FileNotFoundException e){
           e.getStackTrace();
       }
    }
}
