
package chatting.application;


//socket programming in java is used for communication between the application that are running on different JRE.
//It can be either connection-oriented and connectionless.
//simply, a socket is a way to establish a connection between a client and a server.

import javax.swing.*;
import javax.swing.border.*; //contain border related classes
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Server implements ActionListener{
    JButton send;
    JTextField text;   
    JPanel a1;
    static DataOutputStream dout;
    static Box vertical = Box.createVerticalBox();
    //we can extend or can make a object of jframe class
    //making object of JFrame to make it static coz validate method given error as it is non-static and we are using it inside sttaic main class
     static JFrame f = new JFrame(); //created object bcoz of validate method
    Server(){
        f.setLayout(null); //calling using f object coz we have not extended jframne class here
        JPanel p1 = new JPanel(); //used to work on frame
        p1.setBackground(new Color(7,94,84)); //green color
        p1.setBounds(0,0,450,70); // x y width height
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3); //cant pass i2 directly into the label thats why created i3 object
        back.setBounds(5,20,25,25);
        p1.add(back);
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
            
            
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6); //can;t pass i2 directly into the label thats why created i3 object
        profile.setBounds(40,10,50,50);
        p1.add(profile);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9); //can;t pass i2 directly into the label thats why created i3 object
        video.setBounds(300,20,30,30);
        p1.add(video);
        
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i12 = i11.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel phone = new JLabel(i13); //can;t pass i2 directly into the label thats why created i3 object
        phone.setBounds(360,20,35,30);
        p1.add(phone);
        
        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i15 = i14.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel morevert = new JLabel(i16); //can;t pass i2 directly into the label thats why created i3 object
        morevert.setBounds(420,20,10,25);
        p1.add(morevert);
        
        JLabel name = new JLabel("Akku");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name);
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        p1.add(status);
        
        a1 = new JPanel(); //chatting section
        a1.setBounds(5,75,440,570);
        f.add(a1);
        
        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text);
        
        send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(send);
        
        
        
        
        
        
        f.setSize(450,700); //width length
        f.setLocation(200,50); //x y
        f.setUndecorated(true); //to remove by default header section
        //instead of both size and location we can use setBound class from Jframe with 4 arguments
        f.getContentPane().setBackground(Color.WHITE); //getcontentPane call to all the screen
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
       //when we use server there's a possibility of exception thats why we are using try and catch
        try{
            String out = text.getText();
        
        JPanel p2 = formatLabel(out);
        
        a1.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(10)); //space between two msg is 10
        a1.add(vertical,BorderLayout.PAGE_START); 
        
        //send message using writeUTF 
        dout.writeUTF(out);
        
                
        
        text.setText(""); //textarea will be empty after sending a msg
        f.repaint(); //reload frame to show msg on it
        f.invalidate();
        f.validate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style = \"width: 150px\">" + out + "</p></html>"); //to take a width of msg box same
        panel.add(output);
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true); //to show the background of msg
        output.setBorder(new EmptyBorder(15,15,15,50)); //padding left top bottom right       
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
        
    }
    public static void main(String[] args){
        new Server();
        
        try{
            ServerSocket skt = new ServerSocket(6001); //initiated serversocket class to make a server with 6001 port no.
            while(true){
             Socket s =   skt.accept(); //accept msg infinitely and store it in s var
             // datainputstream class to recieve a msg
             DataInputStream din = new DataInputStream(s.getInputStream()); //rcv msg with the help of socket s 
             //dataoutputstream to send a msg contain in io package
             dout = new DataOutputStream(s.getOutputStream());
             while(true){
                 //read a msg using readUTF method
                 String msg = din.readUTF(); //recieved msg stored it in msg varisbale
                 //to show a recieved msg from msg variable on panel
                 JPanel panel = formatLabel(msg);
                 //add recieved msg on left side
                 JPanel left = new JPanel(new BorderLayout());
                 left.add(panel,BorderLayout.LINE_START); //display msg where line start
                 vertical.add(left);
                 f.validate(); //to refresh a current page
                 
             }
             
            }
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
}
