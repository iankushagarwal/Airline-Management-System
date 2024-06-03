import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener{

    JTable jtable;
    JTextField pnr;
    JButton show;

    public JourneyDetails(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lbpnr = new JLabel("PNR Details");
        lbpnr.setFont(new Font("Tahoma", Font.PLAIN ,16));
        lbpnr.setBounds(50 , 50, 100, 25);
        add(lbpnr);

        pnr = new JTextField();
        pnr.setBounds(160 , 50, 120, 25);
        add(pnr);

        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290 , 50, 120, 25);
        show.addActionListener(this);
        add(show);



        jtable = new JTable();

        JScrollPane jsp = new JScrollPane(jtable);
        jsp.setBounds(0,100,1200,150);
        jsp.setBackground(Color.white);
        add(jsp);


        setSize(1200, 700);
        setLocation(400, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            Conn conn = new Conn();

            ResultSet rs = conn.s.executeQuery("select * from booking where PNR = '" + pnr.getText() + "'");

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            
            jtable.setModel(DbUtils.resultSetToTableModel(rs)); 
        
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
