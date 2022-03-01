package dao;

import connection.ConnectionFactory;
import model.Client;
import model.Product;
import presentation.ClientView;
import presentation.OrderProductView;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Ardelean Adriana Ioana
 * clasa folosita pentru a gestiona produsele
 */
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (name,price,stock)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM product where id = ?";

    /**
     * @param productId id-ul produsului cautat
     * @return produsul cu id-ul din parametru
     */
    public static Product findById(int productId) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            toReturn = new Product(productId, name, price, stock);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * @param product produsul care vrem sa fie adaugat in tabela
     * @return id-ul produsului inserat
     */
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getPrice());
            insertStatement.setInt(3, product.getStock());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    /**
     * produsul poate fi sters doar daca nu exista comanda cu el
     * @param id id-ul produsului care vrem sa fie sters din tabela
     */
    public static void delete(int id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        int rowsaffected=0;
        Product product;
        try
        {
            deleteStatement = dbConnection.prepareStatement("DELETE FROM product where id = ?", Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, id);
            if(findById(id)!=null)
                deleteStatement.executeUpdate();
            else {
                ClientView.showError("Nu exista  produs cu acest id");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    /**
     * @param id al produsului pe care vrem sa il modificam
     * @param name numele nou
     * @param price pretul nou
     * @param stock stocul nou
     */
    public static void update(int id, String name, int price, int stock)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try
        {
            updateStatement = dbConnection.prepareStatement("UPDATE product SET name=?, price=?, stock=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, name);
            updateStatement.setInt(2, price);
            updateStatement.setInt(3, stock);
            updateStatement.setInt(4, id);
            if(findById(id)!=null)
                updateStatement.executeUpdate();
            else {
                ClientView.showError("Nu exista  produs cu acest id");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    /**
     * aceastaa functie modifica stocul produsului
     * @param id id ul produsului
     * @param stock stocul produsului
     */
    public static void updateStock(int id, int stock)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try
        {
            updateStatement = dbConnection.prepareStatement("UPDATE product SET stock=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, stock);
            updateStatement.setInt(2, id);
            if(findById(id)!=null)
                updateStatement.executeUpdate();
            else {
                OrderProductView.showError("Nu exista  produs cu acest id");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "ProductDAO:updateStock " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
}
