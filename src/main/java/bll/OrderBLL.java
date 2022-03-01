package bll;

import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Orders;

import java.util.NoSuchElementException;

public class OrderBLL {

    public static Orders findOrderById(int id) {
        Orders orders = OrderDAO.findById(id);
        if (orders == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return orders;
    }

    public static int insertOrder(Orders orders) {
        int id= OrderDAO.insert(orders);
        return id;
    }

    public static void deleteOrder(int id){
        OrderDAO.delete(id);
    }



}
