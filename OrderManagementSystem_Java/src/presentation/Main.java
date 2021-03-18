package presentation;

import businessLayer.CheckpointBLL;
import businessLayer.ClientBLL;
import businessLayer.OrdersBLL;
import businessLayer.ProductBLL;
import model.Checkpoint;
import model.Client;
import model.Orders;
import model.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        File file = null;
        ArrayList<String> arg = new ArrayList<String>();

        ClientBLL clientBll = new ClientBLL();
        ProductBLL productBll = new ProductBLL();
        OrdersBLL ordersBll = new OrdersBLL();
        CheckpointBLL checkpointBLL = new CheckpointBLL();

        ArrayList<Client> clients = clientBll.findAllClients();
        ArrayList<Product> products = productBll.findAllProducts();
        ArrayList<Orders> orders = ordersBll.findAllOrders();
        ArrayList<Checkpoint> checkpoints = checkpointBLL.findAllCheckpoints();

        int maxIdClient = 0, maxIdProduct = 0, maxIdOrder = 0, maxIdCheck = 0;
        int clientToBeDeleted = 0;
        int clientToGetOrder = 0;
        int productToBeOrdered = 0;
        int productToBeOrderedQuantity = 0;
        float productToBeOrderedPrice = 0.0f;
        int orderReportNo = 1, clientReportNo = 1, productReportNo = 1;
        int oldProduct = 0;

        Client clientCopy = new Client();
        Product productCopy = new Product();

        Random random = new Random();

        for(Client client: clients){
            if(client.getIdClient() > maxIdClient){
                maxIdClient = client.getIdClient();
            }
        }
        for(Product product: products){
            if(product.getIdProduct()>maxIdProduct){
                maxIdProduct = product.getIdProduct();
            }
        }
        for(Orders order: orders ){
            if(order.getIdOrder()>maxIdOrder){
                maxIdOrder = order.getIdOrder();
            }
        }

        for(Checkpoint checkpoint: checkpoints ){
            if(checkpoint.getIdCheck()>maxIdCheck){
                maxIdCheck = checkpoint.getIdCheck();
            }
        }

        file = new File(args[0]);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String s = "";
                s = scanner.nextLine();
                arg.add(s);
            }

            for(String line : arg ){
                String[] aux = line.split(" ");
                switch (aux[0]){
                    case "Insert":
                        if(aux[1].equals("client:")) {
                            maxIdClient++;
                            String lastN = aux[3];
                            lastN = lastN.replace(",","");
                            Client client = new Client(maxIdClient,aux[2], lastN, aux[4]);
                            clientBll.addClient(client);
                            clients.add(client);
                        }
                        else{
                            maxIdProduct++;
                            String nameP = aux[2], quantity = aux[3];
                            nameP = nameP.replace(",","");
                            quantity = quantity.replace(",","");

                            oldProduct = 0;
                            for(Product product: products){
                                if(product.getProductName().equals(nameP)){
                                    productBll.updateProduct(product.getIdProduct(),product.getQuantityProduct() + Integer.parseInt(quantity));
                                    product.setQuantityProduct(product.getQuantityProduct() + Integer.parseInt(quantity));
                                    oldProduct = 1;
                                }
                            }
                            if(oldProduct==0) {
                                Product product = new Product(maxIdProduct, nameP, Integer.parseInt(quantity), Float.parseFloat(aux[4]));
                                productBll.addProduct(product);
                                products.add(product);
                            }
                        }
                        break;
                    case "Report":
                        String typeReport = aux[1];
                        switch (typeReport){
                            case "client":
                                clients = clientBll.findAllClients();
                                clientBll.createReport(clients,clientReportNo);
                                clientReportNo++;
                                break;
                            case "order":
                                orders = ordersBll.findAllOrders();
                                ordersBll.createReport(orders,orderReportNo);
                                orderReportNo++;
                                break;
                            case "product":
                                products = productBll.findAllProducts();
                                productBll.createReport(products,productReportNo);
                                productReportNo++;
                                break;
                            default:
                                break;
                        }

                        break;
                    case "Delete":
                        String type = aux[1];
                        if(type.equals("client:")){
                            String firstN = aux[2], lastN = aux[3],city = aux[4];
                            lastN = lastN.replace(",","");

                            for(Client client : clients){
                                if(client.getFirstName().equals(firstN) && client.getLastName().equals(lastN) && client.getCity().equals(city)){
                                    clientToBeDeleted = client.getIdClient();
                                    clientBll.deleteClient(clientToBeDeleted);
                                    client.setOk(1);
                                    break;
                                }
                            }
                            for(Orders order: orders){
                                if(order.getIdClient() == clientToBeDeleted){
                                    ordersBll.deleteOrder(order.getIdOrder());
                                    order.setOk(1);
                                    break;
                                }
                            }
                        }
                        else{
                            String productN = aux[2];
                            for(Product product : products){
                                if(product.getProductName().equals(productN)){
                                    productBll.deleteProduct(product.getIdProduct());
                                    product.setOk(1);
                                    break;
                                }
                            }
                        }

                        break;
                    case "Order:":

                        String firstN = aux[1], lastN = aux[2];
                        lastN = lastN.replace(",","");

                        String productN = aux[3];
                        productN = productN.replace(",","");

                        String quantity = aux[4];

                        for(Client client : clients){
                            if(client.getFirstName().equals(firstN) && client.getLastName().equals(lastN)){
                                clientCopy = client;
                                clientToGetOrder = client.getIdClient();
                                break;
                            }
                        }

                        for(Product product : products){
                            if(product.getProductName().equals(productN)){
                                productCopy = product;
                                productToBeOrdered = product.getIdProduct();
                                productToBeOrderedQuantity = product.getQuantityProduct();
                                productToBeOrderedPrice = product.getPrice();
                                break;
                            }
                        }

                        if(Integer.parseInt(quantity) <= productToBeOrderedQuantity) {
                            maxIdOrder++;
                            Orders order = new Orders(maxIdOrder, productCopy.getIdProduct(), clientToGetOrder, Integer.parseInt(quantity));
                            order.setTotalPrice(productToBeOrderedPrice * Integer.parseInt(quantity));
                            ordersBll.addOrder(order);
                            orders.add(order);
                            ordersBll.createBill(clientCopy,productCopy,order,checkpoints.get(random.nextInt(checkpoints.size())));
                        }
                        else{
                            ordersBll.underStockProduct(productCopy.getProductName());
                        }

                        break;
                    default:
                        break;
                }

            }



        } catch (Exception e){
            e.printStackTrace();
            System.out.println("File - ERROR");
        }

    }
}
