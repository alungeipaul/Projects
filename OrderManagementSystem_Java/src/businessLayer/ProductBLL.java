package businessLayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ProductDAO;
import model.Client;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ProductBLL {
    private ProductDAO prdct;

    public ProductBLL(){
        this.prdct = new ProductDAO();
    }
    /**
     * finds a product by its ID
     * @param id
     * @return
     */
    public Product findProductById(int id){
        Product product = prdct.findById(id);
        if(product == null)
            System.out.println("ERROR - Find product by ID");
        return product;
    }

    /**
     * finds all of the same type products
     * @return
     */
    public ArrayList<Product> findAllProducts(){
        ArrayList<Product> products = new ArrayList<>();
        products = prdct.findAll();
        if(products == null)
            System.out.println("ERROR - Find all products");
        return products;
    }

    /**
     * creates a report of the products in the database
     * @param products
     * @param noRep
     */
    public void createReport(ArrayList<Product> products, int noRep){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream("ProductReport_" + noRep + ".pdf"));
            document.open();

            PdfPTable table = new PdfPTable(4);

            PdfPCell c1 = new PdfPCell(new Phrase("idProduct"));
            c1.setHorizontalAlignment((Element.ALIGN_CENTER));
            table.addCell(c1);


            c1 = new PdfPCell(new Phrase("productName"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("price"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("quantityProduct"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            table.setHeaderRows(1);

            for(Product product: products ){
                table.addCell(Integer.toString(product.getIdProduct()));
                table.addCell(product.getProductName());
                table.addCell(Float.toString(product.getPrice()));
                table.addCell(Integer.toString(product.getQuantityProduct()));
            }
            document.add(table);
            document.close();
        }catch (FileNotFoundException | DocumentException e){
            e.getStackTrace();
            System.out.println("ERROR - ProductBLL Report");
        }

    }

    /**
     * updates a product from the database
     * @param id
     * @param quantity
     */
    public void updateProduct(int id, int quantity){
        prdct.update(id,quantity);
    }

    /**
     * deletes a product from the database
     * @param id
     */
    public void deleteProduct(int id){
        prdct.delete(id);
    }

    /**
     * adds a product to the database
     * @param product
     */
    public void addProduct(Product product){
        prdct.insert(product);
    }

}
