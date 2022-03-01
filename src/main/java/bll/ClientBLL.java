package bll;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {


    public static Client findClientById(int id) {
        Client client = ClientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }
    public static int insertClient(Client client) {
        int id=ClientDAO.insert(client);
        return id;
    }

    public static void deleteClient(int id){
        ClientDAO.delete(id);
    }
    public static void updateClient(int id, String name, String address, String email, int age){
        ClientDAO.update(id,name,address,email,age);
    }
}
