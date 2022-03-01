package dao;

import connection.ConnectionFactory;
import model.Client;
import model.OrderProduct;
import presentation.ClientView;
import presentation.OrderProductView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Ardelean Adriana Ioana
 * clasa folosita pentru a gestiona comenziile finale
 */
public class OrderProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orderproduct (orderId,productId,quantity)"
            + " VALUES (?,?,?)";

    /**
     * @param orderId id-ul comenzii cautate
     * @return lista cu comenziile care au id ul dat ca parametru
     */
    public static List<OrderProduct> findByOrderId(int orderId) {
        List<OrderProduct> toReturn = new ArrayList<OrderProduct>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement("SELECT * FROM orderproduct where orderId = ?");
            findStatement.setInt(1, orderId);
            rs = findStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                int productId = rs.getInt("productId");
                int quantity = rs.getInt("quantity");
                OrderProduct found = new OrderProduct(id, orderId, productId, quantity);

                toReturn.add(found);
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findByOrderId " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * @param productId id-ul produsului cautat
     * @return lista cu comenziile care contin produsul dat ca parametru
     */
    public static List<OrderProduct> findByProductId(int productId) {
        List<OrderProduct> toReturn = new ArrayList<OrderProduct>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement("SELECT * FROM orderproduct where productId = ?");
            findStatement.setInt(1, productId);
            rs = findStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                int orderId = rs.getInt("orderId");
                int quantity = rs.getInt("quantity");
                OrderProduct found = new OrderProduct(id, orderId, productId, quantity);

                toReturn.add(found);
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findByProductId " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * @param orderProduct comanda care vrem sa fie adaugata in tabela
     * @return id-ul comenzii inserate
     */
    public static int insert(OrderProduct orderProduct) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, orderProduct.getOrderId());
            insertStatement.setInt(2, orderProduct.getProductId());
            insertStatement.setInt(3, orderProduct.getQuantity());

            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    /**
     * @param idOrder id-ul comenzii care vrem sa fie stearsa
     */
    public static void deleteOrder(int idOrder)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try
        {
            deleteStatement = dbConnection.prepareStatement("DELETE FROM orderproduct where orderId = ?", Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, idOrder);
            if(!findByOrderId(idOrder).isEmpty())
                deleteStatement.executeUpdate();
            else {
                OrderProductView.showError("Nu exista  orderproduct cu acest orderId");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderProductDAO:delete " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    /**
     * @param idProduct id-ul produsului care vrem sa fie stears
     */
    public static void deleteProduct(int idProduct)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try
        {
            deleteStatement = dbConnection.prepareStatement("DELETE FROM orderproduct where productId = ?", Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, idProduct);
            if(!findByProductId(idProduct).isEmpty())
                deleteStatement.executeUpdate();
            else {
                OrderProductView.showError("Nu exista  orderproduct cu acest productId");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderProductDAO:delete " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }


    /*
   // private OrderProductView orderProductView;
    public static void updateQuantity(int id, String name, String address, String email, int age)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try
        {
            updateStatement = dbConnection.prepareStatement("UPDATE orderproduct SET quantity=?, address=?, email=?, age=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, name);
            updateStatement.setString(2, address);
            updateStatement.setString(3, email);
            updateStatement.setInt(4, age);
            updateStatement.setInt(5, id);
            if(findById(id)!=null)
                updateStatement.executeUpdate();
            else {
                ClientView.showError("Nu exista  client cu acest id");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    */
    /**
     * functie care genereaza fisiere .txt pt comenzi
     */
    public static Object[][] generateFiles(int id)
    {
        Connection c = ConnectionFactory.getConnection();

        class ProductCharacteristics
        {
            String numeprodus;
            int pretprodus;
            int cantitate;
            public ProductCharacteristics(String numeprodus, int pretprodus, int cantitate)
            {
                super();
                this.numeprodus = numeprodus;
                this.pretprodus = pretprodus;
                this.cantitate = cantitate;
            }
            public String getNumeprodus()
            {
                return numeprodus;
            }
            public int getPretprodus()
            {
                return pretprodus;
            }
            public int getCantitate()
            {
                return cantitate;
            }
            public int getTotal()
            {
                return pretprodus*cantitate;
            }
        }
        List<ProductCharacteristics> productList=new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT product.name, product.price, orderproduct.quantity FROM orderproduct INNER JOIN product ON product.id=orderproduct.productId WHERE orderId=?");

            preparedStatement.setInt(1, id);
            ResultSet rs=preparedStatement.executeQuery();

            while(rs.next())
            {
                String name=rs.getString(1);
                int price=rs.getInt(2);
                int quantity=rs.getInt(3);
                productList.add(new ProductCharacteristics(name,price,quantity));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        Object[][] objects=new Object[productList.size()][4];
        int i=0;
        for(ProductCharacteristics product: productList)
        {
            objects[i][0]=product.getNumeprodus();
            objects[i][1]=product.getPretprodus();
            objects[i][2]=product.getCantitate();
            objects[i++][3]=product.getTotal();
        }
        return objects;
    }
}
