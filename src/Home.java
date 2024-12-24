package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Home extends JFrame implements ActionListener 
{

    JButton login,register;

    Home() 
    {
        setLayout(null);
        setTitle("Home");
        
        ImageIcon i1=new ImageIcon("C:\\Users\\shiva\\Downloads\\BannerNew.png");
        Image i2=i1.getImage().getScaledInstance(1120,630,Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1120,630);
        add(image);

        login=new JButton("Login");
        styleButton(login);
        login.setBounds(380,100,150,40);
        login.addActionListener(this);
        image.add(login);

        register=new JButton("Register");
        styleButton(register);
        register.setBounds(580,100,150,40);
        register.addActionListener(this);
        image.add(register);

        setSize(1120,630);
        setLocation(250,100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connectToDatabase();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial",Font.BOLD,18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(34,255,0));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(new Color(34,255,0),2,true));
    }

    private void connectToDatabase() 
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#");
            System.out.println("Connected to the database successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource()==login) 
        {
            setVisible(false);
            new LoginPage();
        } 
        else if (ae.getSource()==register) 
        {
            setVisible(false);
            new RegisterPage();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
