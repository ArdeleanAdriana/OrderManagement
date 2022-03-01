package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * @author Ardelean Adriana Ioana
 * Aceasta este interfata grafica pentru tabela product
 */
public class ProductView extends JFrame {
    public JPanel contentPane;
    //butoane
    private JButton insertProduct;
    private JButton updateProduct;
    private JButton deleteProduct;

    //label pt insert

    private JLabel nameLblI;
    private JLabel priceLblI;
    private JLabel stockLblI;

    //textfield pt insert

    private JTextField nameTI;
    private JTextField priceTI;
    private JTextField stockTI;

    //label pt update
    private JLabel idLblU;
    private JLabel nameLblU;
    private JLabel priceLblU;
    private JLabel stockLblU;

    //textfield pt update
    private JTextField idTU;
    private JTextField nameTU;
    private JTextField priceTU;
    private JTextField stockTU;

    //label si textfield pt delete
    private JLabel idLblD;
    private JTextField idTD;

    //tabel
    private JTable productTable;

    private static JFrame content = new JFrame("Product");
    /**
     * ProductView constructor
     * Butoanele si restul componentelor sunt adaugate interfetei grafice
     */
    public ProductView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //insert
        insertProduct = new JButton("Insert");
        insertProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertProduct.setBounds(20, 10, 70, 20);
        contentPane.add(insertProduct);

        nameLblI = new JLabel("name:");
        nameLblI.setBounds(190, 10, 40, 20);
        contentPane.add(nameLblI);

        nameTI = new JTextField();
        nameTI.setBounds(240, 10, 150, 20);
        contentPane.add(nameTI);

        priceLblI = new JLabel("price:");
        priceLblI.setBounds(410, 10, 60, 20);
        contentPane.add(priceLblI);

        priceTI = new JTextField();
        priceTI.setBounds(470, 10, 150, 20);
        contentPane.add(priceTI);

        stockLblI = new JLabel("stock:");
        stockLblI.setBounds(630, 10, 40, 20);
        contentPane.add(stockLblI);

        stockTI = new JTextField();
        stockTI.setBounds(680, 10, 100, 20);
        contentPane.add(stockTI);


        //update
        updateProduct = new JButton("Update");
        updateProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        updateProduct.setBounds(20, 40, 70, 20);
        contentPane.add(updateProduct);

        idLblU = new JLabel("id:");
        idLblU.setBounds(100, 40, 20, 20);
        contentPane.add(idLblU);

        idTU = new JTextField();
        idTU.setBounds(130, 40, 40, 20);
        contentPane.add(idTU);

        nameLblU = new JLabel("name:");
        nameLblU.setBounds(190, 40, 40, 20);
        contentPane.add(nameLblU);

        nameTU = new JTextField();
        nameTU.setBounds(240, 40, 150, 20);
        contentPane.add(nameTU);

        priceLblU = new JLabel("price:");
        priceLblU.setBounds(410, 40, 60, 20);
        contentPane.add(priceLblU);

        priceTU = new JTextField();
        priceTU.setBounds(470, 40, 150, 20);
        contentPane.add(priceTU);

        stockLblU = new JLabel("stock:");
        stockLblU.setBounds(630, 40, 40, 20);
        contentPane.add(stockLblU);

        stockTU = new JTextField();
        stockTU.setBounds(680, 40, 100, 20);
        contentPane.add(stockTU);

        //delete
        deleteProduct = new JButton("Delete");
        deleteProduct.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        deleteProduct.setBounds(20, 70, 70, 20);
        contentPane.add(deleteProduct);

        idLblD = new JLabel("id:");
        idLblD.setBounds(100, 70, 20, 20);
        contentPane.add(idLblD);

        idTD = new JTextField();
        idTD.setBounds(130, 70, 40, 20);
        contentPane.add(idTD);

    }

    /**
     * butoanele si continutul textfield-urilor sunt returnate ca apoi sa fie facuta functionalitatea in clasa Controller
     */

    public JButton getInsertProduct() {
        return insertProduct;
    }

    public JButton getUpdateProduct() {
        return updateProduct;
    }

    public JButton getDeleteProduct() {
        return deleteProduct;
    }

    public String getUserInput() {
        return this.idTD.getText();
    }

    public String getIdU() {
        return this.idTU.getText();
    }

    public String getNameU() {
        return this.nameTU.getText();
    }

    public String getPriceU() {
        return this.priceTU.getText();
    }

    public String getStockU() {
        return this.stockTU.getText();
    }


    public String getNameI() {
        return this.nameTI.getText();
    }

    public String getPriceI() {
        return this.priceTI.getText();
    }

    public String getStockI() {
        return this.stockTI.getText();
    }


    public static void showError(String errMessage) {
        JOptionPane.showMessageDialog(content, errMessage);
    }
}

