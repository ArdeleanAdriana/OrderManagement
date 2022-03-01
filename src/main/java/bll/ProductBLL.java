package bll;
import dao.ProductDAO;
import model.Product;

import java.util.NoSuchElementException;

public class ProductBLL {

    public static Product findProductById(int id) {
        Product product= ProductDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }
    public static int insertProduct(Product product) {
        int id=ProductDAO.insert(product);
        return id;
    }

    public static void deleteProduct(int id){
        ProductDAO.delete(id);
    }
    public static void updateProduct(int id, String name, int stock, int price){
        ProductDAO.update(id,name,price,stock);
    }
    public static void updateStock(int id, int stock){
        ProductDAO.updateStock(id,stock);
    }
}
