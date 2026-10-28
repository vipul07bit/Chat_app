package chatApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class server extends JFrame {

    server() {
        setTitle("Chat App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel pl = new JPanel();
        pl.setBackground(new Color(7,94,89));
        pl.setBounds(0,0,450,70);
        pl.setLayout(null);
        add(pl);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        pl.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon ia1 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image ia2 = ia1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon ia3 = new ImageIcon(ia2);
        JLabel profile = new JLabel(ia3);
        profile.setBounds(40,15,35,35);
        pl.add(profile);

        ImageIcon ib1 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image ib2 = ib1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon ib3 = new ImageIcon(ib2);
        JLabel video = new JLabel(ib3);
        video.setBounds(300,20,30,30);
        pl.add(video);

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image ic2 = ic1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon ic3 = new ImageIcon(ic2);
        JLabel phone = new JLabel(ic3);
        phone.setBounds(360,20,35,30);
        pl.add(phone);

        ImageIcon id1 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image id2 = id1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon id3 = new ImageIcon(id2);
        JLabel menu = new JLabel(id3);
        menu.setBounds(420,20,10,25);
        pl.add(menu);

        JLabel name = new JLabel("Gaintonde");
        name.setBounds(110,15,100,18);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        name.setForeground(Color.white);
        pl.add(name);

        JLabel status = new JLabel("Gaintonde");
        status.setBounds(110,35,100,18);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        status.setForeground(Color.white);
        pl.add(status);

        JPanel al = new JPanel();
        al.setBounds(5,75,440,570);
        add(al);

        JTextField text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        add(text);

        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.white);
        send.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        add(send);

        setSize(460,735);
        setVisible(true);
        setLocation(200,50);
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        new server();
    }
}