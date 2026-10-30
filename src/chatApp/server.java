package chatApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class server implements ActionListener {

    JTextField text;
    JPanel al;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    JScrollPane scrollPane;

    static JFrame f = new JFrame();

    server() {
        f.setTitle("Chat App");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);

        JPanel pl = new JPanel();
        pl.setBackground(new Color(7,94,89));
        pl.setBounds(0,0,450,70);
        pl.setLayout(null);
        f.add(pl);

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

        JLabel status = new JLabel("Active");
        status.setBounds(110,35,100,18);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        status.setForeground(Color.white);
        pl.add(status);

        al = new JPanel();
        al.setLayout(new BorderLayout());

        scrollPane = new JScrollPane(al);
        scrollPane.setBounds(5, 75, 440, 570);

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        f.add(scrollPane);

        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        text.addActionListener(this);
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.white);
        send.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        send.addActionListener(this);
        f.add(send);

        f.setSize(460,735);
        f.setLocation(200,50);
        f.getContentPane().setBackground(Color.WHITE);

        SwingUtilities.invokeLater(() -> text.requestFocusInWindow());
    }

    public void actionPerformed(ActionEvent e){
        try {
            String msg = text.getText();

            if (msg.isEmpty()) {
                return;
            }
            JPanel p2 = formatLabel(msg);

            al.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            al.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(msg);

            f.repaint();
            f.invalidate();
            f.validate();

            scrollToBottom();

            text.setText("");
            text.requestFocusInWindow();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static JPanel formatLabel(String s){
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel mesgs = new JLabel("<html><p style=\"width: 150px\">"+s+"</p></html>");
        mesgs.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        mesgs.setBackground(Color.GREEN);
        mesgs.setForeground(Color.BLACK);
        mesgs.setOpaque(true);
        mesgs.setBorder(new EmptyBorder(15,15,15,50));

        p.add(mesgs);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        p.add(time);

        return p;
    }

    private void scrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar bar = scrollPane.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
        });
    }

    static void main(String[] args) {
        new server();

        try{
            ServerSocket skt = new ServerSocket(6001);
            while (true){
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                while (true){
                    String msg = din.readUTF();
                    JPanel p = formatLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(p, BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}