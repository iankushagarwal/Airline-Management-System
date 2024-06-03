import java.awt.Choice;
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


public class BookFlight extends JFrame implements ActionListener {

    JTextField jtaadhar;
    JButton fetchDetails, fetchflight, bookflight;
    Choice sources, destinations;
    JLabel aadhar, gender, name, nationalty, address, phone, fname, fcode;
    JDateChooser dcdate;

    public BookFlight(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel head = new JLabel("Book Flight");
        head.setBounds(420, 20, 500, 35);
        head.setFont(new Font("Tahoma", Font.PLAIN ,32));
        head.setForeground(Color.BLUE);
        add(head);

        aadhar = new JLabel("Aadhar");
        aadhar.setBounds(60 , 80, 150, 25);
        aadhar.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(aadhar);

        jtaadhar = new JTextField();
        jtaadhar.setBounds(220 , 80, 150, 25);
        add(jtaadhar);

        fetchDetails = new JButton("Fetch Details");
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

        JLabel lbaddress = new JLabel("Address");
        lbaddress.setBounds(60, 280, 150, 25);
        lbaddress.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbaddress);

        address = new JLabel();
        address.setBounds(220 , 280, 150, 25);
        address.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(address);

        JLabel lbphone = new JLabel("Phone No.");
        lbphone.setBounds(60, 330, 150, 25);
        lbphone.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbphone);

        phone = new JLabel();
        phone.setBounds(220 , 330, 150, 25);
        phone.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(phone);
        
        JLabel source = new JLabel("Source");
        source.setBounds(60 , 380, 150, 25);
        source.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(source);

        sources = new Choice();
        sources.setBounds(220, 380, 150, 25);
        // sources.add("delhi");
        add(sources);

        JLabel destination = new JLabel("Destination");
        destination.setBounds(60 , 430, 150, 25);
        destination.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(destination);

        destinations = new Choice();
        destinations.setBounds(220, 430, 150, 25);
        add(destinations);

        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs =  c.s.executeQuery(query);

            while (rs.next()) {
                sources.add(rs.getString("source"));
                destinations.add(rs.getString("destination"));
            }

        } catch (Exception e) {
            
        }

        fetchflight = new JButton("Fetch Flights");
        fetchflight.setBackground(Color.black);
        fetchflight.setForeground(Color.white);
        fetchflight.setBounds(380 , 430, 120, 25);
        fetchflight.addActionListener(this);
        add(fetchflight);

        JLabel lbfname = new JLabel("Flight Name");
        lbfname.setBounds(60, 480, 150, 25);
        lbfname.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbfname);

        fname = new JLabel();
        fname.setBounds(220 , 480, 150, 25);
        fname.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(fname);

        JLabel lbfcode = new JLabel("Flight Code");
        lbfcode.setBounds(60, 530, 150, 25);
        lbfcode.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbfcode);

        fcode = new JLabel();
        fcode.setBounds(220 , 530, 150, 25);
        fcode.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(fcode);

        JLabel lbdate = new JLabel("Date");
        lbdate.setBounds(60, 580, 150, 25);
        lbdate.setFont(new Font("Tahoma", Font.PLAIN ,16));
        add(lbdate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220,580,150,25);
        add(dcdate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons\\details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lbimage = new JLabel(image);
        lbimage.setBounds(550, 80, 500, 410);
        add(lbimage);

        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.black);
        bookflight.setForeground(Color.white);
        bookflight.setBounds(220 , 630, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);

        setSize(1100, 800);
        setLocation(410, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BookFlight();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource()== fetchDetails) {
            
            String fbyaadhar = jtaadhar.getText();
            
            try{

                Conn conn = new Conn();

                String query = "select * from passenger where aadhar = '" + fbyaadhar + "'";

                ResultSet rs =  conn.s.executeQuery(query);
                if (rs.next()) {

                    gender.setText(rs.getString("gender"));
                    name.setText(rs.getString("name"));
                    nationalty.setText(rs.getString("nationalty"));
                    address.setText(rs.getString("address"));
                    phone.setText(rs.getString("phone"));

                } else {
                    JOptionPane.showMessageDialog(null," Please enter correct aadhar");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource()== fetchflight) {
            
            String src = sources.getSelectedItem();
            String dest = destinations.getSelectedItem();

            
            try{

                Conn conn = new Conn();

                String query = "select * from flight where source = '" + src + "' and destination = '"+ dest +"'";

                ResultSet rs =  conn.s.executeQuery(query);
                if (rs.next()) {

                    fname.setText(rs.getString("f_name"));
                    fcode.setText(rs.getString("f_code"));

                } else {
                    JOptionPane.showMessageDialog(null,"No flight Found");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else if (ae.getSource()== bookflight){

            Random random = new Random();
            String aadharbk = jtaadhar.getText();
            String genderbk = gender.getText();
            String namebk = name.getText();
            String nationaltybk = nationalty.getText();
            String addressbk = address.getText();
            String phonebk = phone.getText();
            String fnamebk = fname.getText();
            String fcodebk = fcode.getText();
            String srcbk = sources.getSelectedItem();
            String destbk = destinations.getSelectedItem();
            String ddatebk = ((JTextField)dcdate.getDateEditor().getUiComponent()).getText();

            try{

                Conn conn = new Conn();

                String query = "INSERT INTO booking VALUES ('PNR-" + random.nextInt(1000000) + "', 'TIC-" + random.nextInt(1000) + "', '" + aadharbk + "', '" + genderbk + "', '" + namebk + "', '" + nationaltybk + "', '" + addressbk + "', '" + phonebk + "', '" + fnamebk + "', '" + fcodebk + "', '" + srcbk + "', '" + destbk + "', '" + ddatebk + "')"; 

                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Ticket Booked succesfully");
                setVisible(false);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
