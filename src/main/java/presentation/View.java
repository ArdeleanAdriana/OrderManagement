package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * @author Ardelean Adriana Ioana
 * Aplicatie care simuleaza management-ul unei comenzi
 * Aceasta este interfata grafica
 */
public class View extends JFrame {
    private JPanel contentPane;
    private JButton showClient;
    private JButton showProduct;
    private JButton showOrder;
    private JButton showOrderProduct;
    /**
     * View constructor
     * Butoanele si restul componentelor sunt adaugate interfetei grafice
     */
    public View(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        showProduct = new JButton("Product");
        showProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        showProduct.setBounds(200, 100, 100, 20);
        contentPane.add(showProduct);

        showClient = new JButton("Client");
        showClient.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        showClient.setBounds(100, 100, 100, 20);
        contentPane.add(showClient);

        showOrder = new JButton("Order");
        showOrder.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        showOrder.setBounds(100, 200, 100, 20);
        contentPane.add(showOrder);

        showOrderProduct = new JButton("OrderProduct");
        showOrderProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        showOrderProduct.setBounds(200, 200, 100, 20);
        contentPane.add(showOrderProduct);



    }

    /**
     * butoanele sunt returnate ca apoi sa fie facuta functionalitatea in clasa Controller
     */
    public JButton getShowProduct()
    {
        return showProduct;
    }
    public JButton getShowClient()
    {
        return showClient;
    }
    public JButton getShowOrder()
    {
        return showOrder;
    }
    public JButton getShowOrderProduct()
    {
        return showOrderProduct;
    }
}
