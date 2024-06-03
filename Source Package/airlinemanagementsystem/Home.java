import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {

    public Home() {
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons\\front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1940, 900);
        add(image);

        JLabel head = new JLabel("Air India Welcomes You");
        head.setBounds(770, 20, 400, 40);
        head.setForeground(Color.blue);
        head.setFont(new Font("tahoma", Font.PLAIN, 36));
        image.add(head);

        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);

        JMenu mnu = new JMenu("Details");
        mbar.add(mnu);

        JMenuItem mnuItem1 = new JMenuItem("Flight Deatils");
        mnuItem1.addActionListener(this);
        mnu.add(mnuItem1);

        JMenuItem mnuItem2 = new JMenuItem("Add Customer Deatils");
        mnuItem2.addActionListener(this);
        mnu.add(mnuItem2);

        JMenuItem mnuItem3 = new JMenuItem("Book Flight");
        mnuItem3.addActionListener(this);
        mnu.add(mnuItem3);

        JMenuItem mnuItem4 = new JMenuItem("Journey Details");
        mnuItem4.addActionListener(this);
        mnu.add(mnuItem4);

        JMenuItem mnuItem5 = new JMenuItem("Tickets Cancellation");
        mnuItem5.addActionListener(this);
        mnu.add(mnuItem5);

        JMenu mnu2 = new JMenu("Tickets");
        mbar.add(mnu2);

        JMenuItem mnuItem21 = new JMenuItem("Boarding Pass");
        mnu2.add(mnuItem21);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
   
    public static void main(String[] args) {
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String text = ae.getActionCommand();

        if (text.equals("Add Customer Deatils")) {
            new AddCustomer();
            
        } else if (text.equals("Flight Deatils")){
            new FlightInfo();

        } else if (text.equals("Book Flight")){
            new BookFlight();
            
        } else if (text.equals("Journey Details")){
            new JourneyDetails();

        } else if (text.equals("Tickets Cancellation")){
            new Cancel();
        }

    }

}