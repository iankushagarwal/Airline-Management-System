import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField jtname, jtnationalty, jtaadhar, jtaddress, jtphone;
    JRadioButton jbgenderM, jbgenderF;

    public AddCustomer(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel head = new JLabel("Add Customer Details");
        head.setBounds(250, 20, 500, 35);
        head.setFont(new Font("Tahoma", Font.PLAIN ,32));
        head.setForeground(Color.BLUE);
        add(head);

        JLabel name = new JLabel("Name");
        name.setBounds(60 , 80, 150, 25);
        name.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(name);

        jtname = new JTextField();
        jtname.setBounds(220 , 80, 150, 25);
        add(jtname);

        JLabel nationalty = new JLabel("Nationality");
        nationalty.setBounds(60 , 130, 150, 25);
        nationalty.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(nationalty);

        jtnationalty = new JTextField();
        jtnationalty.setBounds(220 , 130, 150, 25);
        add(jtnationalty);

        JLabel aadhar = new JLabel("Aadhar No.");
        aadhar.setBounds(60 , 180, 150, 25);
        aadhar.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(aadhar);

        jtaadhar = new JTextField();
        jtaadhar.setBounds(220 , 180, 150, 25);
        add(jtaadhar);

        JLabel address = new JLabel("Address");
        address.setBounds(60 , 230, 150, 25);
        address.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(address);

        jtaddress = new JTextField();
        jtaddress.setBounds(220 , 230, 150, 25);
        add(jtaddress);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(60 , 280, 150, 25);
        gender.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(gender);

        jbgenderM = new JRadioButton("Male");
        jbgenderM.setBackground(Color.WHITE);
        jbgenderM.setBounds(220 , 280, 70, 25);
        add(jbgenderM);

        jbgenderF = new JRadioButton("Female");
        jbgenderF.setBackground(Color.WHITE);
        jbgenderF.setBounds(300 , 280, 70, 25);
        add(jbgenderF);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(jbgenderM);
        genderGroup.add(jbgenderF);

        JLabel phone = new JLabel("Phone No.");
        phone.setBounds(60 , 330, 150, 25);
        phone.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(phone);

        jtphone = new JTextField();
        jtphone.setBounds(220 , 330, 150, 25);
        add(jtphone);

        JButton save = new JButton("Save");
        save.setBackground(Color.black);
        save.setForeground(Color.white);
        save.addActionListener(this);
        save.setBounds(220 , 380, 150, 25);
        add(save);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons\\emp.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(450, 80, 280, 400);
        add(image);

        setSize(900, 600);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 150);
        setVisible(true);




    }

    public static void main(String[] args) {
        new AddCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String name = jtname.getText();
        String nationalty = jtnationalty.getText();
        String aadhar = jtaadhar.getText();
        String address = jtaddress.getText();
        String phone = jtphone.getText();

        String gender = null;
        if (jbgenderM.isSelected()) {
            gender = "Male";     
        } else{
            gender = "Female";
        }

        try{

            Conn conn = new Conn();

            String query = "insert into passenger values('" + name + "', '" + nationalty + "', '" + aadhar + "', '" + address + "', '" + phone + "', '" + gender + "')";

            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, name + " Details Added Sucessfully");
            setVisible(false);

        } catch (Exception e){

            e.printStackTrace();

        }


    }
    
}
