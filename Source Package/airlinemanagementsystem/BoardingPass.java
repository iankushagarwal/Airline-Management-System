import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BoardingPass extends JFrame implements ActionListener {

    JTextField jtpnr;
    JButton enter, download;
    JLabel pnrde, gender, name, nationality, src, dst, phone, fname, fcode, dcdate;

    public BoardingPass() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel head = new JLabel("AIR INDIA");
        head.setBounds(380, 10, 450, 35);
        head.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(head);

        JLabel subhead = new JLabel("Boarding Pass");
        subhead.setBounds(380, 50, 300, 35);
        subhead.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subhead.setForeground(Color.BLUE);
        add(subhead);

        pnrde = new JLabel("PNR Details");
        pnrde.setBounds(60, 100, 150, 25);
        pnrde.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(pnrde);

        jtpnr = new JTextField();
        jtpnr.setBounds(220, 100, 150, 25);
        add(jtpnr);

        enter = new JButton("ENTER");
        enter.setBackground(Color.BLACK);
        enter.setForeground(Color.WHITE);
        enter.setBounds(380, 100, 120, 25);
        enter.addActionListener(this);
        add(enter);

        JLabel lbname = new JLabel("NAME");
        lbname.setBounds(60, 135, 150, 25);
        lbname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbname);

        name = new JLabel();
        name.setBounds(220, 135, 150, 25);
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(name);

        JLabel lbgender = new JLabel("GENDER");
        lbgender.setBounds(60, 170, 150, 25);
        lbgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbgender);

        gender = new JLabel();
        gender.setBounds(220, 170, 150, 25);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(gender);

        JLabel lbnationality = new JLabel("NATIONALITY");
        lbnationality.setBounds(60, 205, 150, 25);
        lbnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbnationality);

        nationality = new JLabel();
        nationality.setBounds(220, 205, 150, 25);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(nationality);

        JLabel lbsrc = new JLabel("SRC");
        lbsrc.setBounds(60, 240, 150, 25);
        lbsrc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbsrc);

        src = new JLabel();
        src.setBounds(220, 240, 150, 25);
        src.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(src);

        JLabel lbdst = new JLabel("DST");
        lbdst.setBounds(380, 240, 150, 25);
        lbdst.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbdst);

        dst = new JLabel();
        dst.setBounds(540, 240, 150, 25);
        dst.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(dst);

        JLabel lbphone = new JLabel("Phone No.");
        lbphone.setBounds(60, 275, 150, 25);
        lbphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbphone);

        phone = new JLabel();
        phone.setBounds(220, 275, 150, 25);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(phone);

        JLabel lbfname = new JLabel("Flight Name");
        lbfname.setBounds(60, 310, 150, 25);
        lbfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbfname);

        fname = new JLabel();
        fname.setBounds(220, 310, 150, 25);
        fname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(fname);

        JLabel lbfcode = new JLabel("Flight Code");
        lbfcode.setBounds(380, 310, 150, 25);
        lbfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbfcode);

        fcode = new JLabel();
        fcode.setBounds(540, 310, 150, 25);
        fcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(fcode);

        JLabel lbdate = new JLabel("Date");
        lbdate.setBounds(60, 345, 150, 25);
        lbdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbdate);

        dcdate = new JLabel();
        dcdate.setBounds(220, 345, 150, 25);
        dcdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(dcdate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/airindia.png"));
        Image i2 = i1.getImage().getScaledInstance(410, 270, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lbimage = new JLabel(image);
        lbimage.setBounds(520, 7, 410, 270);
        add(lbimage);

        download = new JButton("DOWNLOAD");
        download.setBackground(Color.BLACK);
        download.setForeground(Color.WHITE);
        download.setBounds(520, 345, 120, 25);
        download.addActionListener(this);
        add(download);

        setSize(1000, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(310, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BoardingPass();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == enter) {
            String pnr = jtpnr.getText().trim();

            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "PNR cannot be empty");
                return;
            }

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM booking WHERE PNR = '" + pnr + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    name.setText(rs.getString("Name"));
                    gender.setText(rs.getString("Gender"));
                    nationality.setText(rs.getString("Nationalty"));
                    src.setText(rs.getString("Source"));
                    dst.setText(rs.getString("Destination"));
                    phone.setText(rs.getString("Phone"));
                    fname.setText(rs.getString("F_Name"));
                    fcode.setText(rs.getString("F_Code"));
                    dcdate.setText(rs.getString("Date"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching PNR details");
            }
        } else if (ae.getSource() == download) {
            saveBoardingPassImage();
        }
    }

    private void saveBoardingPassImage() {
        try {
            Rectangle area = getContentPane().getBounds();
            BufferedImage image = new BufferedImage(area.width, area.height, BufferedImage.TYPE_INT_RGB);
            getContentPane().paint(image.getGraphics());

            File outputfile = new File("D:/boarding_pass.png");
            ImageIO.write(image, "png", outputfile);
            JOptionPane.showMessageDialog(null, "Boarding pass saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving boarding pass image.");
        }
    }
}
