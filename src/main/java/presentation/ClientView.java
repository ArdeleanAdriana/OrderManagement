package presentation;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * @author Ardelean Adriana Ioana
 * Aceasta este interfata grafica pentru tabela client
 */
public class ClientView extends JFrame {
    public JPanel contentPane;
    //butoane
    private JButton insertClient;
    private JButton updateClient;
    private JButton deleteClient;

    //label pt insert

    private JLabel nameLblI;
    private JLabel addressLblI;
    private JLabel emailLblI;
    private JLabel ageLblI;

    //textfield pt insert

    private JTextField nameTI;
    private JTextField addressTI;
    private JTextField emailTI;
    private JTextField ageTI;
    //label pt update
    private JLabel idLblU;
    private JLabel nameLblU;
    private JLabel addressLblU;
    private JLabel emailLblU;
    private JLabel ageLblU;
    //textfield pt update
    private JTextField idTU;
    private JTextField nameTU;
    private JTextField addressTU;
    private JTextField emailTU;
    private JTextField ageTU;

    //label si textfield pt delete
    private JLabel idLblD;
    private JTextField idTD;

    //tabel
    private JTable clientTable;

    private static JFrame content=new JFrame("Client");

    /**
     * ClientView constructor
     * Butoanele si restul componentelor sunt adaugate interfetei grafice
     */
    public ClientView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //insert
        insertClient = new JButton("Insert");
        insertClient.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        insertClient.setBounds(20, 10, 70, 20);
        contentPane.add(insertClient);

        nameLblI = new JLabel("name:");
        nameLblI.setBounds(190, 10, 40, 20);
        contentPane.add(nameLblI);

        nameTI=new JTextField();
        nameTI.setBounds(240, 10, 150, 20);
        contentPane.add(nameTI);

        addressLblI = new JLabel("address:");
        addressLblI.setBounds(410, 10, 60, 20);
        contentPane.add(addressLblI);

        addressTI=new JTextField();
        addressTI.setBounds(470, 10, 150, 20);
        contentPane.add(addressTI);

        emailLblI = new JLabel("email:");
        emailLblI.setBounds(630, 10, 40, 20);
        contentPane.add(emailLblI);

        emailTI=new JTextField();
        emailTI.setBounds(680, 10, 100, 20);
        contentPane.add(emailTI);

        ageLblI = new JLabel("age:");
        ageLblI.setBounds(790, 10, 30, 20);
        contentPane.add(ageLblI);

        ageTI=new JTextField();
        ageTI.setBounds(830, 10, 30, 20);
        contentPane.add(ageTI);



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


        //delete
        deleteClient = new JButton("Delete");
        deleteClient.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        deleteClient.setBounds(20, 70, 70, 20);
        contentPane.add(deleteClient);

        idLblD = new JLabel("id:");
        idLblD.setBounds(100, 70, 20, 20);
        contentPane.add(idLblD);

        idTD=new JTextField();
        idTD.setBounds(130, 70, 40, 20);
        contentPane.add(idTD);

    }

    /**
     * butoanele si continutul textfield-urilor sunt returnate ca apoi sa fie facuta functionalitatea in clasa Controller
     */

    public JButton getInsertClient()
    {
        return insertClient;
    }
    public JButton getUpdateClient()
    {
        return updateClient;
    }
    public JButton getDeleteClient()
    {
        return deleteClient;
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
    public String getAddressU() {
        return this.addressTU.getText();
    }
    public String getEmailU() {
        return this.emailTU.getText();
    }
    public String getAgeU() {
        return this.ageTU.getText();
    }


    public String getNameI() {
        return this.nameTI.getText();
    }
    public String getAddressI() {
        return this.addressTI.getText();
    }
    public String getEmailI() {
        return this.emailTI.getText();
    }
    public String getAgeI() {
        return this.ageTI.getText();
    }

    public static void showError(String errMessage)
    {
        JOptionPane.showMessageDialog(content, errMessage);
    }



}
