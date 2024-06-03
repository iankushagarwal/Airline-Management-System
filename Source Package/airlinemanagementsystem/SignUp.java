import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class SignUp extends JFrame implements ActionListener {

    JButton reset, signupButton;
    JTextField jtuser;
    JPasswordField jtpwd;
    Connection connection;

    public SignUp() {

        try {
        
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinemanagementsystem", "root", "qwerty123");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
            System.exit(1);
        }

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

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(250, 120, 120, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(700, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SignUp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String username = jtuser.getText();
            String password = new String(jtpwd.getPassword());

            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO login (username, password) VALUES (?, ?)");
                statement.setString(1, username);
                statement.setString(2, password);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Signup successful!");
                    setVisible(false);
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(this, "Signup failed.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while signing up.");
            }
        }
    }

}