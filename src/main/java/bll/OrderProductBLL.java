package bll;

import dao.OrderProductDAO;
import dao.ProductDAO;
import model.OrderProduct;
import model.Product;

import java.util.NoSuchElementException;

public class OrderProductBLL {

    public static int insertOrderProduct(OrderProduct orderProduct) {
        int id= OrderProductDAO.insert(orderProduct);
        return id;
    }

    public static void deleteOrderProductProduct(int id){
        OrderProductDAO.deleteProduct(id);
    }
    public static void deleteOrderProductOrder(int id){
        OrderProductDAO.deleteOrder(id);
    }
}
