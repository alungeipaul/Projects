package businessLayer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ClientDAO;
import model.Client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ClientBLL {
    private ClientDAO clnt;

    public ClientBLL(){
        this.clnt = new ClientDAO();
    }

    /**
     * finds a client depending on its ID
     * @param id
     * @return
     */
    public Client findClientById(int id){
        Client client = clnt.findById(id);
        if(client == null)
            System.out.println("ERROR - Find client by ID");
        return client;
    }

    /**
     * finds all clients
     * @return
     */
    public ArrayList<Client> findAllClients(){
        ArrayList<Client> clients = new ArrayList<>();
        clients = clnt.findAll();
        if(clients == null)
            System.out.println("ERROR - Find all clients");
        return clients;
    }

    /**
     * creates a report of the clients in the database
     * @param clients
     * @param noRep
     */

    public void createReport(ArrayList<Client> clients,int noRep){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document,new FileOutputStream("ClientReport_" + noRep + ".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(4);

            PdfPCell c1 = new PdfPCell(new Phrase("idClient"));
            c1.setHorizontalAlignment((Element.ALIGN_CENTER));
            table.addCell(c1);


            c1 = new PdfPCell(new Phrase("firstName"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("lastName"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("city"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            table.setHeaderRows(1);

            for (Client client : clients) {
                table.addCell(Integer.toString(client.getIdClient()));
                table.addCell(client.getFirstName());
                table.addCell(client.getLastName());
                table.addCell(client.getCity());
            }
            document.add(table);
            document.close();
        }catch (FileNotFoundException | DocumentException e){
            e.getStackTrace();
            System.out.println("ERROR - ClientBLL Report");
        }
    }

    /**
     * delete a client from the database
     * @param id
     */
    public void deleteClient(int id){
        clnt.delete(id);
    }

    /**
     * adds a client in the database
     * @param client
     */
    public void addClient(Client client){
        clnt.insert(client);
    }

}
