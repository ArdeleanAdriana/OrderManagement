package dao;

import connection.ConnectionFactory;
import model.Client;
import presentation.ClientView;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Ardelean Adriana Ioana
 * clasa folosita pentru a gestiona clientii
 */
public class ClientDAO {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client (name,address,email,age)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM client where id = ?";

    /**
     * @param clientId id-ul clientului cautat
     * @return clientul cu id-ul din parametru
     */
    public static Client findById(int clientId) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, clientId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            int age = rs.getInt("age");
            toReturn = new Client(clientId, name, address, email, age);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * @param client clientul care vrem sa fie adaugat in tabela
     * @return id-ul clientului inserat
     */
    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getAddress());
            insertStatement.setString(3, client.getEmail());
            insertStatement.setInt(4, client.getAge());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * clientul poate fi sters doar daca nu exista comanda facuta de el
     * @param id id-ul clientului care vrem sa fie sters din tabela
     */
    public static void delete(int id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        int rowsaffected=0;
        Client client;
        try
        {
            deleteStatement = dbConnection.prepareStatement("DELETE FROM client where id = ?", Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, id);
            if(findById(id)!=null)
                deleteStatement.executeUpdate();
            else {
                ClientView.showError("Nu exista  client cu acest id");
            }
        }
        catch (SQLException e)
        {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    private ClientView clientView;
    /**
     * @param id al clientului care vrem sa il modificam
     * @param name numele nou
     * @param address adresa noua
     * @param email email ul nou
     * @param age varsta noua
     */
    public static void update(int id, String name, String address, String email, int age)
    {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try
        {
            updateStatement = dbConnection.prepareStatement("UPDATE client SET name=?, address=?, email=?, age=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
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

}
