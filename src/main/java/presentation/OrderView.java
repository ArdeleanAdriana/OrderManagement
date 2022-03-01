package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * @author Ardelean Adriana Ioana
 * Aceasta este interfata grafica pentru tabela order
 */
public class OrderView extends JFrame{
    public JPanel contentPane;
    //butoane
    private JButton insertOrder;
    private JButton updateOrder;
    private JButton deleteOrder;



    //label pt insert

    private JLabel idClientLblI;


    //textfield pt insert

    private JTextField idClientTI;
    //label pt update
    private JLabel idLblU;
    private JLabel idClientLblU;
    //textfield pt update
    private JTextField idTU;
    private JTextField idClientTU;

    //label si textfield pt delete
    private JLabel idLblD;
    private JTextField idTD;

    //tabel
    private JTable clientTable;

    private static JFrame content=new JFrame("Order");

    /**
     * OrderView constructor
     * Butoanele si restul componentelor sunt adaugate interfetei grafice
     */
    public OrderView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //insert
        insertOrder = new JButton("Insert");
        insertOrder.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertOrder.setBounds(20, 10, 70, 20);
        contentPane.add(insertOrder);

        idClientLblI = new JLabel("idClient:");
        idClientLblI.setBounds(190, 10, 60, 20);
        contentPane.add(idClientLblI);

        idClientTI=new JTextField();
        idClientTI.setBounds(240, 10, 150, 20);
        contentPane.add(idClientTI);

    /*
        //update
        updateOrder = new JButton("Update");
        updateOrder.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        updateOrder.setBounds(20, 40, 70, 20);
        contentPane.add(updateOrder);

        idLblU = new JLabel("id:");
        idLblU.setBounds(100, 40, 20, 20);
        contentPane.add(idLblU);

        idTU=new JTextField();
        idTU.setBounds(130, 40, 40, 20);
        contentPane.add(idTU);

        idClientLblU = new JLabel("idClient:");
        idClientLblU.setBounds(190, 40, 60, 20);
        contentPane.add(idClientLblU);

        idClientTU=new JTextField();
        idClientTU.setBounds(240, 40, 150, 20);
        contentPane.add(idClientTU);
*/
        //delete
        deleteOrder = new JButton("Delete");
        deleteOrder.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        deleteOrder.setBounds(20, 40, 70, 20);
        contentPane.add(deleteOrder);

        idLblD = new JLabel("id:");
        idLblD.setBounds(100, 40, 20, 20);
        contentPane.add(idLblD);

        idTD=new JTextField();
        idTD.setBounds(130, 40, 40, 20);
        contentPane.add(idTD);




    }

    /**
     * butoanele si continutul textfield-urilor sunt returnate ca apoi sa fie facuta functionalitatea in clasa Controller
     */


    public JButton getInsertOrder()
    {
        return insertOrder;
    }


    public JButton getDeleteOrder()
    {
        return deleteOrder;
    }

    public String getUserInput() {
        return this.idTD.getText();
    }
    /*
    public String getIdU() {
        return this.idTU.getText();
    }
    public String getIdClientU() {return this.idClientTU.getText();}
*/

    public String getidClientI() {
        return this.idClientTI.getText();
    }


    public static void showError(String errMessage)
    {
        JOptionPane.showMessageDialog(content, errMessage);
    }

}
