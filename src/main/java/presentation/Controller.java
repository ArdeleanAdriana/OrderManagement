package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.OrderProductBLL;
import bll.ProductBLL;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import connection.ConnectionFactory;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.OrderProductDAO;
import dao.ProductDAO;
import model.Client;
import model.OrderProduct;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
/**
 * @author Ardelean Adriana Ioana
 * Aceasta este clasa Controller, in aceasta clasa se face functionalitatea butoanelor, functionalitatea interfetei grafice
 */
public class Controller {
    private View view;
    private ClientView clientView;
    private OrderView orderView;
    private ProductView productView;
    private OrderProductView orderProductView;
    private int idClt;
    private int ageCltU;
    private int idI;
    private int cltId;
    private int idProdus;
    private int stock;
    private int price;
    private int orderId;
    private int productId;
    private int quantity;
    /**
     * Cotroller constructor
     */
    public Controller(View view,ClientView clientView, OrderView orderView, ProductView productView, OrderProductView orderProductView) {
        this.view = view;
        this.clientView=clientView;
        this.orderView=orderView;
        this.productView=productView;
        this.orderProductView=orderProductView;
        this.view.getShowClient().addActionListener(e->{
                this.clientView.setVisible(true);
                showClients();

        });
        this.view.getShowOrder().addActionListener(e->{
            this.orderView.setVisible(true);
            showOrders();

        });
        this.view.getShowProduct().addActionListener(e->{
            this.productView.setVisible(true);
            showProducts();

        });
        this.view.getShowOrderProduct().addActionListener(e->{
            this.orderProductView.setVisible(true);
            showOrderProducts();

        });
        this.clientView.getDeleteClient().addActionListener(e->{
            String idClient = clientView.getUserInput();
            try {
               idClt= Integer.parseInt(idClient);

                if(idClt<=0)
                    ClientView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
                else if(OrderDAO.findByOrderId(idClt).isEmpty())
                    ClientBLL.deleteClient(idClt);
                else
                    ClientView.showError("acest client are comanda");
            }catch (NumberFormatException nfex)
            {
                ClientView.showError("Id ul ar trebui sa fie un numar intreg");
            }

            showClients();
        });
        this.clientView.getUpdateClient().addActionListener(e->{
            String idClient = clientView.getIdU();

            String nameClientU = clientView.getNameU();
            String addressClientU = clientView.getAddressU();
            String emailClientU = clientView.getEmailU();
            String ageClientU = clientView.getAgeU();

            try {
                idClt= Integer.parseInt(idClient);
            }catch (NumberFormatException nfex)
            {
                ClientView.showError("Id ul ar trebui sa fie un numar intreg");
            }
            try {
                ageCltU= Integer.parseInt(ageClientU);
            }catch (NumberFormatException nfex)
            {
                ClientView.showError("Varsta ar trebui sa fie un numar intreg");
            }
            if(idClt<=0)
                ClientView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
            else
                if (ageCltU < 16)
                    ClientView.showError("Varsta ar trebui sa fie un numar intreg pozitiv >=16");
                else
                    ClientBLL.updateClient(idClt,nameClientU,addressClientU,emailClientU,ageCltU);
            showClients();
        });
        this.clientView.getInsertClient().addActionListener(e->{
            String nameClientI = clientView.getNameI();
            String addressClientI = clientView.getAddressI();
            String emailClientI = clientView.getEmailI();
            String ageClientU = clientView.getAgeI();

            try {
                ageCltU= Integer.parseInt(ageClientU);
            }catch (NumberFormatException nfex)
            {
                ClientView.showError("Varsta ar trebui sa fie un numar intreg");
            }

            Client client = new Client (nameClientI,addressClientI,emailClientI,ageCltU);
            if (ageCltU < 16)
                ClientView.showError("Varsta ar trebui sa fie un numar intreg pozitiv >=16");
            else
                 idI = ClientBLL.insertClient(client);

            showClients();
        });

        this.orderProductView.getInsertOrderProduct().addActionListener(e->{
            String orderIdI = orderProductView.getOrderI();
            String productIdI = orderProductView.getProductI();
            String quantityI = orderProductView.getquantityI();

            try {
                orderId= Integer.parseInt(orderIdI);
                productId= Integer.parseInt(productIdI);
                quantity= Integer.parseInt(quantityI);

            }catch (NumberFormatException nfex)
            {
                OrderProductView.showError("Inputurile ar trebui sa fie numere intregi pozitive");
            }


                if(OrderBLL.findOrderById(orderId)!=null) {
                    if (ProductBLL.findProductById(productId) != null) {
                        Product updateQuantityProduct= ProductBLL.findProductById(productId);
                        int stock=updateQuantityProduct.getStock();
                        int newstock=stock-quantity;
                        OrderProduct orderProduct = new OrderProduct(orderId,productId,quantity);
                        if (newstock < 0)
                            OrderProductView.showError("stoc insuficient");
                        else {
                            ProductBLL.updateStock(productId, newstock);
                            orderId = OrderProductBLL.insertOrderProduct(orderProduct);
                        }
                    } else
                        OrderProductView.showError("Nu exista  product cu acest id");
                }else
                    OrderProductView.showError("Nu exista  order cu acest id");

            showOrderProducts();
        });

        /////order
        this.orderView.getDeleteOrder().addActionListener(e->{
            String idOrder = orderView.getUserInput();
            try {
                idClt= Integer.parseInt(idOrder);
                if(idClt<=0)
                    OrderView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
                else if(OrderProductDAO.findByOrderId(idClt).isEmpty())
                    OrderBLL.deleteOrder(idClt);
                else
                    OrderView.showError("Exista orderProduct cu acest orderId");
            }catch (NumberFormatException nfex)
            {
                OrderView.showError("Id ul ar trebui sa fie un numar intreg");
            }

            showOrders();
        });
        /*
        this.orderView.getUpdateOrder().addActionListener(e->{
            String idClient = orderView.getIdU();

            String clientId = orderView.getIdClientU();

            try {
                idClt= Integer.parseInt(idClient);
            }catch (NumberFormatException nfex)
            {
                ClientView.showError("Id ul ar trebui sa fie un numar intreg");
            }
            try {
                cltId= Integer.parseInt(clientId);
            }catch (NumberFormatException nfex)
            {
                OrderView.showError("Id ul ar trebui sa fie un numar intreg");
            }
            if(idClt<=0)
                OrderView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
            else
            if (cltId <= 0)
                OrderView.showError("Varsta ar trebui sa fie un numar intreg pozitiv ");
            else
                OrderDAO.update(idClt,cltId);
            showOrders();
        });*/
        this.orderView.getInsertOrder().addActionListener(e->{
            String clientId = orderView.getidClientI();
            try {
                cltId= Integer.parseInt(clientId);
            }catch (NumberFormatException nfex)
            {
                OrderView.showError("Id ul ar trebui sa fie un numar intreg");
            }

            Orders order = new Orders (cltId);
            if (cltId <=0)
                OrderView.showError("Id ul ar trebui sa fie un numar intreg pozitiv ");
            else
                if(ClientBLL.findClientById(cltId)!=null)
                    idI = OrderBLL.insertOrder(order);
                else
                    OrderView.showError("nu exista client cu acest id ");

            showOrders();
        });
        /////product
        this.productView.getDeleteProduct().addActionListener(e->{
            String idProduct = productView.getUserInput();
            try {
                idProdus= Integer.parseInt(idProduct);
                if(idProdus<=0)
                    ProductView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
                else {
                    if(OrderProductDAO.findByProductId(idProdus).isEmpty())
                        ProductBLL.deleteProduct(idProdus);
                    else
                        ProductView.showError("Exista orderproduct cu acest produs");

                }
            }catch (NumberFormatException nfex)
            {
                ProductView.showError("Id ul ar trebui sa fie un numar intreg");
            }

            showProducts();
        });
        this.productView.getUpdateProduct().addActionListener(e->{
            String idProduct = productView.getIdU();
            String nameProductU = productView.getNameU();
            String priceProductU = productView.getPriceU();
            String stockProductU = productView.getStockU();


            try {
                idProdus= Integer.parseInt(idProduct);
                price= Integer.parseInt(priceProductU);
                stock= Integer.parseInt(stockProductU);
                if(idProdus<=0)
                    ProductView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
                else
                if (price <= 0)
                    ProductView.showError("Pretul ar trebui sa fie un numar intreg pozitiv ");
                else
                if(stock<=0)
                    ProductView.showError("Stocul ar trebui sa fie un numar intreg pozitiv ");
                else
                    ProductBLL.updateProduct(idProdus,nameProductU,price,stock);
            }catch (NumberFormatException nfex)
            {
                ProductView.showError("Id ul , pretul si stocul ar trebui sa fie un numere intregi");
            }


            showProducts();
        });
        this.productView.getInsertProduct().addActionListener(e->{
            String nameProductI = productView.getNameI();
            String stockProductI = productView.getStockI();
            String priceProductI = productView.getPriceI();

            try {
                stock= Integer.parseInt(stockProductI);
                price= Integer.parseInt(priceProductI);
                Product product = new Product (nameProductI,price,stock);
                if (stock <=0)
                    ProductView.showError("Stocul ar trebui sa fie un numar intreg pozitiv ");
                else
                if (price <=0)
                    ProductView.showError("Pretul ar trebui sa fie un numar intreg pozitiv ");
                else
                    idI = ProductBLL.insertProduct(product);
            }catch (NumberFormatException nfex)
            {
               ProductView.showError("Stocul si pretul ar trebui sa fie numere intregi");
            }

            showProducts();
        });

        this.orderProductView.getDeleteOrder().addActionListener(e->{
            String idOrder = orderProductView.getUserInputO();
            try {
                orderId= Integer.parseInt(idOrder);
                if(orderId<=0)
                    OrderProductView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
                else
                    OrderProductBLL.deleteOrderProductOrder(orderId);
            }catch (NumberFormatException nfex)
            {
                OrderProductView.showError("Id ul ar trebui sa fie un numar intreg");
            }

            showOrderProducts();
        });
        this.orderProductView.getDeleteProduct().addActionListener(e->{
            String idProduct = orderProductView.getUserInputP();
            try {
                productId= Integer.parseInt(idProduct);
                if(productId<=0)
                    OrderProductView.showError("Id ul ar trebui sa fie un numar intreg pozitiv");
                else
                    OrderProductDAO.deleteProduct(productId);
            }catch (NumberFormatException nfex)
            {
                OrderProductView.showError("Id ul ar trebui sa fie un numar intreg");
            }

            showOrderProducts();
        });
        this.orderProductView.getGenerateOrder().addActionListener(e->{
            String fileName="orderFiles/order_" + orderProductView.getGenerateId() +".txt";
            try
            {
                BufferedWriter br=new BufferedWriter(new FileWriter(fileName));

                Object[][] objects=OrderProductDAO.generateFiles(Integer.parseInt(orderProductView.getGenerateId()));

                String line[] ={ "Product: ", "Price: ", "Quantity: ", "Total Price: "};
                int sum=0;
                for(int i=0;i<objects.length;i++)
                {
                    for(int j=0;j<4;j++)
                    {
                        br.write(line[j]);
                        br.write(""+objects[i][j]);
                        br.newLine();
                    }
                    sum=sum+(int)(objects[i][3]);
                    br.newLine();
                }
                br.write("Final price of the order: "+sum);
                br.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        });

    }
    JTable clientTable;
    public void showClients()
    {
        Connection c = ConnectionFactory.getConnection();

        try
        {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM client");
            ResultSet resultSet = preparedStatement.executeQuery();

            Object columnNames[] = { "id", "name", "address", "email", "age" };

            List<Object> clients = new ArrayList<>();
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                Client client = new Client(id, name, address, email, age);
                clients.add(client);
            }
            clientTable=createTable(clients,columnNames);
            clientTable.setEnabled(false);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            System.out.println(e1.getMessage());
        }
        JScrollPane ClientScroll = new JScrollPane(clientTable);
        ClientScroll.setEnabled(false);
        clientView.contentPane.add(ClientScroll).setBounds(100,100,800,200);
    }
    JTable orderTable;
    public void showOrders()
    {
        Connection c = ConnectionFactory.getConnection();

        try
        {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = preparedStatement.executeQuery();

            Object columnNames[] = { "id", "clientId" };

            List<Object> orders = new ArrayList<>();
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("clientId");

                Orders order = new Orders(id, clientId);
                orders.add(order);
            }
            orderTable=createTable(orders,columnNames);
            orderTable.setEnabled(false);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            System.out.println(e1.getMessage());
        }
        JScrollPane ClientScroll = new JScrollPane(orderTable);
        ClientScroll.setEnabled(false);
        orderView.contentPane.add(ClientScroll).setBounds(100,100,800,200);
    }

    JTable productTable;
    public void showProducts()
    {
        Connection c = ConnectionFactory.getConnection();

        try
        {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = preparedStatement.executeQuery();

            Object columnNames[] = { "id", "name", "price", "stock"};

            List<Object> products = new ArrayList<>();
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");

                Product product = new Product(id, name, price, stock);
                products.add(product);
            }
            productTable=createTable(products,columnNames);
            productTable.setEnabled(false);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            System.out.println(e1.getMessage());
        }
        JScrollPane ClientScroll = new JScrollPane(productTable);
        ClientScroll.setEnabled(false);
        productView.contentPane.add(ClientScroll).setBounds(100,100,800,200);
    }


    JTable orderproductTable;
    public void showOrderProducts()
    {
        Connection c = ConnectionFactory.getConnection();

        try
        {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM orderproduct");
            ResultSet resultSet = preparedStatement.executeQuery();

            Object columnNames[] = { "id", "orderId", "productId", "quantity" };

            List<Object> orderproducts = new ArrayList<>();
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                int orderId = resultSet.getInt("orderId");
                int productId = resultSet.getInt("productId");
                int quantity = resultSet.getInt("quantity");

                OrderProduct orderproduct = new OrderProduct(id, orderId, productId, quantity);
                orderproducts.add(orderproduct);
            }
            orderproductTable=createTable(orderproducts,columnNames);
            orderproductTable.setEnabled(false);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            System.out.println(e1.getMessage());
        }
        JScrollPane OrderProductScroll = new JScrollPane(orderproductTable);
        OrderProductScroll.setEnabled(false);
        orderProductView.contentPane.add(OrderProductScroll).setBounds(100,100,800,200);
    }

    /**
     * functie care creaza un JTable utilizand parametrii
     * @param objects lista de obiecte care pot fi clienti, produse, comenzi sau orderproducts
     * @param columnNames - numele coloanelor
     * @return un JTable
     */
    JTable createTable(List<Object> objects, Object columnNames[])
    {
        JTable table = null;
        List<Object> properties = retrieveProperties(objects.get(0)); // properties is the list of fields of an object from the list of objects
        Object rowData[][] = new Object[objects.size()][properties.size()]; // matrix rowData has object size rows and properties size columns
        int i = 0; // used as row iterator in the matrix
        for(Object obj: objects)
        {
            properties = retrieveProperties(objects.get(i)); //gets the value of the fields of the next object
            int j = 0; // used as column iterator in the matrix
            for(Object prop: properties)
                rowData[i][j++] = prop; // put the data in the matrix
            i++;
        }
        table = new JTable(rowData,columnNames); // create the table using the matrix and a parameter
        return table;
    }
    /**
     * @param object un obiect care poate fi client/product/order/orderproduct
     * @return o lista  reprezentand  field urile obiectului
     */
    public static List<Object> retrieveProperties(Object object)
    {
        Object value;
        List<Object> properties = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            try
            {
                value = field.get(object);
                properties.add(value);
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
