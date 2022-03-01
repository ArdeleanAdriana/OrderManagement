package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * @author Ardelean Adriana Ioana
 * Aceasta este interfata grafica pentru tabela orderproduct
 */
public class OrderProductView extends JFrame {
    public JPanel contentPane;
    //butoane
    private JButton insertOrderProduct;
    //rivate JButton updateClient;
    private JButton deleteProduct;
    private JButton deleteOrder;


    private JButton generateOrderFile=new JButton("Generate Order File (.txt)");
    private JTextField orderIdFile=new JTextField("Type OrderID");
    //label pt insert

    private JLabel orderIdLblI;
    private JLabel productIdLblI;
    private JLabel quantityLblI;

    //textfield pt insert

    private JTextField orderIdTI;
    private JTextField productIdTI;
    private JTextField quantityTI;
    //label pt update
    //private JLabel idLblU;
    //private JLabel nameLblU;
    //private JLabel addressLblU;
    //private JLabel emailLblU;
    //private JLabel ageLblU;
    //textfield pt update
    //private JTextField idTU;
    //private JTextField nameTU;
    //private JTextField addressTU;
    //private JTextField emailTU;
    //private JTextField ageTU;

    //label si textfield pt delete order
    private JLabel idOrderLblD;
    private JTextField idOrderTD;
    //label si textfield pt delete product
    private JLabel idProductLblD;
    private JTextField idProductTD;

    //tabel
    private JTable orderproductTable;

    private static JFrame content=new JFrame("OrderProduct");
    /**
     * OrderProductView constructor
     * Butoanele si restul componentelor sunt adaugate interfetei grafice
     */
    public OrderProductView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //insert
        insertOrderProduct = new JButton("Insert");
        insertOrderProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertOrderProduct.setBounds(20, 10, 70, 20);
        contentPane.add(insertOrderProduct);

        orderIdLblI = new JLabel("orderId:");
        orderIdLblI.setBounds(190, 10, 60, 20);
        contentPane.add(orderIdLblI);

        orderIdTI=new JTextField();
        orderIdTI.setBounds(260, 10, 100, 20);
        contentPane.add(orderIdTI);

        productIdLblI = new JLabel("productId:");
        productIdLblI.setBounds(370, 10, 60, 20);
        contentPane.add(productIdLblI);

        productIdTI=new JTextField();
        productIdTI.setBounds(440, 10, 100, 20);
        contentPane.add(productIdTI);

        quantityLblI = new JLabel("quantity:");
        quantityLblI.setBounds(550, 10, 60, 20);
        contentPane.add(quantityLblI);

        quantityTI=new JTextField();
        quantityTI.setBounds(620, 10, 100, 20);
        contentPane.add(quantityTI);

/*
        //update
        updateClient = new JButton("Update");
        updateClient.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        updateClient.setBounds(20, 40, 70, 20);
        contentPane.add(updateClient);

        idLblU = new JLabel("id:");
        idLblU.setBounds(100, 40, 20, 20);
        contentPane.add(idLblU);

        idTU=new JTextField();
        idTU.setBounds(130, 40, 40, 20);
        contentPane.add(idTU);

        nameLblU = new JLabel("name:");
        nameLblU.setBounds(190, 40, 40, 20);
        contentPane.add(nameLblU);

        nameTU=new JTextField();
        nameTU.setBounds(240, 40, 150, 20);
        contentPane.add(nameTU);

        addressLblU = new JLabel("address:");
        addressLblU.setBounds(410, 40, 60, 20);
        contentPane.add(addressLblU);

        addressTU=new JTextField();
        addressTU.setBounds(470, 40, 150, 20);
        contentPane.add(addressTU);

        emailLblU = new JLabel("email:");
        emailLblU.setBounds(630, 40, 40, 20);
        contentPane.add(emailLblU);

        emailTU=new JTextField();
        emailTU.setBounds(680, 40, 100, 20);
        contentPane.add(emailTU);

        ageLblU = new JLabel("age:");
        ageLblU.setBounds(790, 40, 30, 20);
        contentPane.add(ageLblU);

        ageTU=new JTextField();
        ageTU.setBounds(830, 40, 30, 20);
        contentPane.add(ageTU);

*/
        //delete
        deleteOrder = new JButton("Delete Order");
        deleteOrder.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        deleteOrder.setBounds(20, 40, 105, 20);
        contentPane.add(deleteOrder);

        idOrderLblD = new JLabel("idOrder:");
        idOrderLblD.setBounds(130, 40, 60, 20);
        contentPane.add(idOrderLblD);

        idOrderTD=new JTextField();
        idOrderTD.setBounds(200, 40, 40, 20);
        contentPane.add(idOrderTD);
        //delete
        deleteProduct = new JButton("Delete Product");
        deleteProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        deleteProduct.setBounds(20, 70, 105, 20);
        contentPane.add(deleteProduct);

        idProductLblD = new JLabel("idProduct:");
        idProductLblD.setBounds(130, 70, 60, 20);
        contentPane.add(idProductLblD);

        idProductTD=new JTextField();
        idProductTD.setBounds(200, 70, 40, 20);
        contentPane.add(idProductTD);

        generateOrderFile = new JButton("Generate");
        generateOrderFile.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        generateOrderFile.setBounds(800, 20, 100, 20);
        contentPane.add(generateOrderFile);

        orderIdFile=new JTextField();
        orderIdFile.setBounds(910, 20, 40, 20);
        contentPane.add(orderIdFile);


    }

    /**
     * butoanele si continutul textfield-urilor sunt returnate ca apoi sa fie facuta functionalitatea in clasa Controller
     */




    public JButton getInsertOrderProduct()
    {
        return insertOrderProduct;
    }

    public JButton getDeleteOrder()
    {
        return deleteOrder;
    }
    public JButton getDeleteProduct()
    {
        return deleteProduct;
    }

    public String getUserInputO() {
        return this.idOrderTD.getText();
    }
    public String getUserInputP() {
        return this.idProductTD.getText();
    }

    public String getOrderI() {
        return this.orderIdTI.getText();
    }
    public String getProductI() {
        return this.productIdTI.getText();
    }
    public String getquantityI() {
        return this.quantityTI.getText();
    }

    public static void showError(String errMessage)
    {
        JOptionPane.showMessageDialog(content, errMessage);
    }


    public JButton getGenerateOrder()
    {
        return generateOrderFile;
    }


    public String getGenerateId() {
        return this.orderIdFile.getText();
    }
}
