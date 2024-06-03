import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;


public class Cancel extends JFrame implements ActionListener {

    JTextField jtpnr;
    JButton fetchDetails, cancelB;
    JLabel gender, name, nationalty, cancel, fcode, date;
    JDateChooser dcdate;

    public Cancel(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random = new Random();


        JLabel head = new JLabel("CANCELATTION");
        head.setBounds(320, 20, 500, 35);
        head.setFont(new Font("Tahoma", Font.PLAIN ,32));
        add(head);

        JLabel pnr_n = new JLabel("PNR No.");
        pnr_n.setBounds(60 , 80, 150, 25);
        pnr_n.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(pnr_n);

        jtpnr = new JTextField();
        jtpnr.setBounds(220 , 80, 150, 25);
        add(jtpnr);

        fetchDetails = new JButton("Show Details");
        fetchDetails.setBackground(Color.black);
        fetchDetails.setForeground(Color.white);
        fetchDetails.setBounds(380, 80, 120, 25);
        fetchDetails.addActionListener(this);
        add(fetchDetails);

        JLabel lbname = new JLabel("Name");
        lbname.setBounds(60, 130, 150, 25);
        lbname.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbname);

        name = new JLabel();
        name.setBounds(220 , 130, 150, 25);
        name.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(name);

        JLabel lbgender = new JLabel("Gender");
        lbgender.setBounds(60, 180, 150, 25);
        lbgender.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbgender);

        gender = new JLabel();
        gender.setBounds(220 , 180, 150, 25);
        gender.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(gender);

        JLabel lbnationalty = new JLabel("Nationality");
        lbnationalty.setBounds(60, 230, 150, 25);
        lbnationalty.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbnationalty);

        nationalty = new JLabel();
        nationalty.setBounds(220 , 230, 150, 25);
        nationalty.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(nationalty);

        JLabel lbcancel = new JLabel("Cancellation No.");
        lbcancel.setBounds(60, 280, 150, 25);
        lbcancel.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbcancel);

        cancel = new JLabel("" +random.nextInt(1000000));
        cancel.setBounds(220 , 280, 150, 25);
        cancel.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(cancel);

        JLabel lbfcode = new JLabel("Flight Code");
        lbfcode.setBounds(60, 330, 150, 25);
        lbfcode.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbfcode);

        fcode = new JLabel();
        fcode.setBounds(220 , 330, 150, 25);
        fcode.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(fcode);

        JLabel lbdate = new JLabel("Date");
        lbdate.setBounds(60, 380, 150, 25);
        lbdate.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbdate);

        date = new JLabel();
        date.setBounds(220 , 380, 150, 25);
        date.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(date);

        cancelB = new JButton("Cancel");
        cancelB.setBackground(Color.black);
        cancelB.setForeground(Color.white);
        cancelB.setBounds(220 , 430, 120, 25);
        cancelB.addActionListener(this);
        add(cancelB);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons\\cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 150, 250, 250);
        add(image);

        setSize(900, 650);
        setLocation(410, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Cancel();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == fetchDetails) {
            String pnr = jtpnr.getText();

            try{
                
                Conn conn = new Conn();
            
                String query = "select * from booking where PNR = '" + pnr + "'";
            
                ResultSet rs =  conn.s.executeQuery(query);

                if (rs.next()) {
            
                    gender.setText(rs.getString("gender"));
                    name.setText(rs.getString("name"));
                    nationalty.setText(rs.getString("nationalty"));
                    fcode.setText(rs.getString("F_Code"));
                    date.setText(rs.getString("Date"));

                } else {
                    JOptionPane.showMessageDialog(null," Please Enter Correct PNR");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource()== cancelB) {
            
            String pnr = jtpnr.getText();
            String nme = name.getText();
            String gen = gender.getText();
            String ntlty = nationalty.getText();
            String cancl = cancel.getText();
            String f_code = fcode.getText();
            String dte = date.getText();

            try{
            
                Conn conn = new Conn();

                String query = "INSERT INTO cancel values ('" + pnr + "', '" + nme + "', '" + gen + "', '" + ntlty + "', '" + cancl + "', '" + f_code + "', '" + dte + "')";

                conn.s.executeUpdate(query);
                conn.s.executeUpdate("Delete from booking where pnr ='" + pnr + "'");

                JOptionPane.showMessageDialog(null,"Ticket Cancelled");
                setVisible(false);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}