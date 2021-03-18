package businessLayer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.OrdersDAO;
import dao.ProductDAO;
import model.Checkpoint;
import model.Client;
import model.Orders;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class OrdersBLL {
    private OrdersDAO ordr;
    private ProductDAO prdct;

    public OrdersBLL(){
        this.ordr = new OrdersDAO();
        this.prdct = new ProductDAO();
    }
    /**
     * finds an order by its ID
     * @param id
     * @return
     */
    public Orders findOrderById(int id){
        Orders order = ordr.findById(id);
        if(order == null)
            System.out.println("ERROR - Find order by ID");
        return order;
    }

    /**
     * finds all orders
     * @return
     */
    public ArrayList<Orders> findAllOrders(){
        ArrayList<Orders> orders = new ArrayList<>();
        orders = ordr.findAll();
        if(orders == null)
            System.out.println("ERROR - Find all orders");
        return orders;
    }

    /**
     * creates a report of the orders in the database
     * @param orders
     * @param noRep
     */
    public void createReport(ArrayList<Orders> orders,int noRep){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream("OrderReport_" + noRep + ".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(5);

            PdfPCell c1 = new PdfPCell(new Phrase("idOrder"));
            c1.setHorizontalAlignment((Element.ALIGN_CENTER));
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("idProduct"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("idClient"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);


            c1 = new PdfPCell(new Phrase("quantityOrder"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("totalPrice"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            table.setHeaderRows(1);

            for(Orders order: orders ){
                table.addCell(Integer.toString(order.getIdOrder()));
                table.addCell(Integer.toString(order.getIdProduct()));
                table.addCell(Integer.toString(order.getIdClient()));
                table.addCell(Integer.toString(order.getQuantityOrder()));
                table.addCell(Float.toString(order.getTotalPrice()));
            }
            document.add(table);

            document.close();
        }catch (FileNotFoundException | DocumentException e){
            e.getStackTrace();
            System.out.println("ERROR - OrdersBLL Report");
        }

    }

    /**
     * creates a bill when an order is made by a client for a certain product, which also includes the checkpoint where
     * the order was processed
     * @param client
     * @param product
     * @param order
     * @param checkpoint
     */

    public void createBill(Client client, Product product, Orders order, Checkpoint checkpoint){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream("BillOf_" + client.getFirstName()+client.getLastName()+"_"+order.getIdOrder() + ".pdf"));
            document.open();

            Paragraph message = new Paragraph("Order Bill of client with ID: "+client.getIdClient() + "\n");
            document.add(message);

            message = new Paragraph("Name: "+client.getFirstName()+" "+client.getLastName()+"\n");
            document.add(message);

            message = new Paragraph("Product: "+product.getProductName()+"\n");
            document.add(message);

            message = new Paragraph("Ordered quantity and price: "+order.getQuantityOrder()+", "+product.getPrice()+"\n");
            document.add(message);

            message = new Paragraph("Total price of order: "+order.getTotalPrice());
            document.add(message);

            message = new Paragraph("Order proccessed at checkpoint "+checkpoint.getIdCheck() +"-"+checkpoint.getSeries()+".");
            document.add(message);

            document.close();
        }catch (FileNotFoundException | DocumentException e){
            e.getStackTrace();
            System.out.println("ERROR - OrdersBLL Bill");
        }

    }

    public void underStockProduct(String productName){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream("UnderStockBill.pdf"));
            document.open();

            Paragraph message = new Paragraph("The '"+productName+"' product is under stock - the order cannot be proccessed.");
            document.add(message);

            document.close();
        }catch (FileNotFoundException | DocumentException e){
            e.getStackTrace();
            System.out.println("ERROR - OrdersBLL UnderStockBill");
        }
    }

    /**
     * delete an order from the database
     * @param id
     */
    public void deleteOrder(int id){
        ordr.delete(id);
    }

    /**
     * adds an order in the database and updates the remaining quantity of the product that has been ordered
     * @param order
     */
    public void addOrder(Orders order){
        Product product = prdct.findById(order.getIdProduct());
        ordr.insert(order);
        int newQuantity = product.getQuantityProduct()-order.getQuantityOrder();
        prdct.update(product.getIdProduct(),newQuantity);
    }
}
