package dao;

import connection.ConnectionFactory;
import model.OrderProduct;
import model.Orders;
import presentation.ClientView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Ardelean Adriana Ioana
 * clasa folosita pentru a gestiona comenziile
 */
public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (clientId)"
            + " VALUES (?)";
    private final static String findStatementString = "SELECT * FROM orders where id = ?";

    /**
     * @param orderId id-ul comenzii cautate
     * @return lista cu comenziile care au id ul dat ca parametru
     */
    public static List<Orders> findByOrderId(int orderId) {
        List<Orders> toReturn = new ArrayList<Orders>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement("SELECT * FROM orders where clientId = ?");
            findStatement.setInt(1, orderId);
            rs = findStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                Orders found = new Orders(id, orderId);
                toReturn.add(found);
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findByOrderId " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * @param orderId id-ul comenzii cautate
     * @return comanda cu id-ul din parametru
     */
    public static Orders findById(int orderId) {
        Orders toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderId);
            rs = findStatement.executeQuery();
            rs.next();


            int clientId = rs.getInt("clientId");
            toReturn = new Orders(orderId, clientId);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * @param orders comanda care vrem sa fie adaugata in tabela
     * @return id-ul comenzii inserate
     */
    public static int insert(Orders orders) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, orders.getClientId());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    /**
     * comanda poate fi stearsa doar daca nu exista orderproduct cu ea
     * @param id id-ul comenzii care vrem sa fie stearsa din tabela
     */
    public static void delete(int id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        int rowsaffected=0;
        Orders orders;
        try
        {
            deleteStatement = dbConnection.prepareStatement("DELETE FROM orders where id = ?", Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, id);
            if(findById(id)!=null)
                deleteStatement.executeUpdate();
            else {
                ClientView.showError("Nu exista  comanda cu acest id");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderDAO:delete " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    /*
    public static void update(int id,int clientId)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try
        {
            updateStatement = dbConnection.prepareStatement("UPDATE orders SET clientId=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, clientId);
            updateStatement.setInt(2, id);
            if(findById(id)!=null)
                updateStatement.executeUpdate();
            else {
                ClientView.showError("Nu exista  comanda cu acest id");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderDAO:update " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
*/


}
