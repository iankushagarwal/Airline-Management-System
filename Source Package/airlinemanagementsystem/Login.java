import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton reset, submit, signup;
    JTextField jtuser;
    JPasswordField jtpwd;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel juser = new JLabel("Username");
        juser.setBounds(20, 20, 100, 20);
        add(juser);

        jtuser = new JTextField();
        jtuser.setBounds(130, 20, 200, 20);
        add(jtuser);

        JLabel jpwd = new JLabel("Password");
        jpwd.setBounds(20, 60, 100, 20);
        add(jpwd);

        jtpwd = new JPasswordField();
        jtpwd.setBounds(130, 60, 200, 20);
        add(jtpwd);

        reset = new JButton("Reset");
        reset.setBounds(40, 120, 120, 20);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("LogIn");
        submit.setBounds(250, 120, 120, 20);
        submit.addActionListener(this);
        add(submit);

        JLabel jsign = new JLabel("Don't Have an Account?");
        jsign.setBounds(140, 170, 150, 20);
        add(jsign);

        signup = new JButton("Sign Up");
        signup.setBounds(145, 200, 120, 20);
        signup.addActionListener(this);
        add(signup);

        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(700, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == reset) {
            jtuser.setText("");
            jtpwd.setText("");
        } else if (ae.getSource() == submit) {
            String username = jtuser.getText();
            @SuppressWarnings("deprecation")
            String password = jtpwd.getText();

            try {
                Conn co = new Conn();
                String query = "select * from login where username='" + username + "' and password='" + password + "'";
                ResultSet rs = co.s.executeQuery(query);

                if (rs.next()) {
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == signup) {
            new SignUp();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}