package start;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import bll.OrderBLL;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.OrderProductDAO;
import dao.ProductDAO;
import model.Client;
import model.OrderProduct;
import model.Orders;
import model.Product;
import presentation.*;

/**
 * @author Ardelean Adriana Ioana
 * @since Apr  2021
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {
		/*
		Client client = new Client("dummy name", "dummy address", "dummy@address.co", 12);
		Client client1 = new Client("dummy name1", "dummy address12", "dummy1@address.co", 12);
		ClientBLL clientBll = new ClientBLL();
		int id = clientBll.insertClient(client);


		if (id > 0) {
			clientBll.findClientById(id);
		}
		int id1 = clientBll.insertClient(client1);

		if (id1 > 0) {
			clientBll.findClientById(id1);
		}
		// Generate error
		try {
			clientBll.findClientById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		ClientDAO.delete(7);
		ClientDAO.update(8, "nume", "adresa", "mail@yahoo,com" , 14);




		Orders orders = new Orders(16);

		int idOrder = OrderDAO.insert(orders);
		if (idOrder > 0) {
			OrderDAO.findById(idOrder);
		}
		try {
			OrderDAO.findById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		OrderDAO.delete(3);
		OrderDAO.update(5, 3);



		Product product = new Product("numeasda", 23,3);
		int idProduct = ProductDAO.insert(product);
		if (idProduct > 0) {
			ProductDAO.findById(idProduct);
		}
		try {
			ProductDAO.findById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		ProductDAO.delete(2);
		ProductDAO.update(4, "adri", 11, 5);

		//obtain field-value pairs for object through reflection
		ReflectionExample.retrieveProperties(client);
		ReflectionExample.retrieveProperties(client1);
		ReflectionExample.retrieveProperties(orders);
		ReflectionExample.retrieveProperties(product);
		*/
	/*
		int idOrder;
		int productId=44;
		int orderId=38;
		int quantity=1;
		Product updateQuantityProduct= ProductDAO.findById(productId);
		int stock=updateQuantityProduct.getStock();
		OrderProduct orderProduct = new OrderProduct(orderId,productId,quantity);
		try {
			OrderDAO.findById(orderId);
			ProductDAO.findById(productId);
			idOrder = OrderProductDAO.insert(orderProduct);
			ProductDAO.updateStock(productId,stock-quantity);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}


		int orderId=38;
		try {
			OrderProductDAO.deleteOrder(38);

		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}

*/


		/*aici incepe gui*/
		View view = new View();
		ClientView clientView = new ClientView();
		OrderView orderView=new OrderView();
		ProductView productView=new ProductView();
		OrderProductView orderProductView=new OrderProductView();
		Controller controller = new Controller(view,clientView,orderView,productView, orderProductView);
		view.setVisible(true);
	}



	

}
