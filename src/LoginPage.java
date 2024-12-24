package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class LoginPage extends JFrame implements ActionListener 
{
    
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, backButton;
    JRadioButton adminRadio, operatorRadio;
    JLabel captchaLabel, captchaValue, captchaInputLabel;
    JTextField captchaInput;
    String generatedCaptcha;

    LoginPage() 
    {
        setLayout(null);
        getContentPane().setBackground(new Color(240,240,240));
        
        JLabel heading=new JLabel("Welcome to Criminal Database Management System");
        heading.setBounds(40,30,400,30);
        heading.setFont(new Font("Raleway",Font.BOLD,24));
        heading.setForeground(new Color(34,34,34));
        add(heading);
       
        JLabel userTypeLabel=new JLabel("Select User Type:");
        userTypeLabel.setBounds(100,70,200,30);
        userTypeLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(userTypeLabel);
        
        adminRadio=new JRadioButton("Admin");
        adminRadio.setBounds(100,100,100,30);
        adminRadio.setFont(new Font("Raleway",Font.PLAIN,18));
        operatorRadio=new JRadioButton("Operator");
        operatorRadio.setBounds(200,100,100,30);
        operatorRadio.setFont(new Font("Raleway",Font.PLAIN,18));
        
        ButtonGroup userTypeGroup=new ButtonGroup();
        userTypeGroup.add(adminRadio);
        userTypeGroup.add(operatorRadio);
        add(adminRadio);
        add(operatorRadio);

        JLabel userLabel=new JLabel("Username:");
        userLabel.setBounds(100,140,100,30);
        userLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(userLabel);
        
        usernameField=new JTextField();
        usernameField.setBounds(200,140,150,30);
        usernameField.setFont(new Font("Raleway",Font.PLAIN,16));
        add(usernameField);
        
        JLabel passLabel=new JLabel("Password:");
        passLabel.setBounds(100,180,100,30);
        passLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(passLabel);
        
        passwordField=new JPasswordField();
        passwordField.setBounds(200,180,150,30);
        passwordField.setFont(new Font("Raleway",Font.PLAIN,16));
        add(passwordField);
        
        captchaLabel=new JLabel("Captcha: ");
        captchaLabel.setBounds(100,220,100,30);
        captchaLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(captchaLabel);
        
        generatedCaptcha=generateCaptcha();
        captchaValue=new JLabel(generatedCaptcha);
        captchaValue.setBounds(200,220,100,30);
        captchaValue.setFont(new Font("Raleway",Font.BOLD,18));
        captchaValue.setForeground(new Color(34,139,34));
        add(captchaValue);
        
        captchaInputLabel=new JLabel("Enter Captcha:");
        captchaInputLabel.setBounds(100,260,100,30);
        captchaInputLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(captchaInputLabel);
        
        captchaInput=new JTextField();
        captchaInput.setBounds(200,260,150,30);
        captchaInput.setFont(new Font("Raleway",Font.PLAIN,16));
        add(captchaInput);
        
        loginButton=new JButton("Login");
        styleButton(loginButton);
        loginButton.setBounds(100,320,100,40);
        loginButton.addActionListener(this);
        add(loginButton);
        
        backButton=new JButton("Back");
        styleButton(backButton);
        backButton.setBounds(250,320,100,40);
        backButton.addActionListener(this);
        add(backButton);
        
        setSize(400,400);
        setLocation(400,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Raleway",Font.BOLD,18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(34,139,34));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,true));
    }

    private String generateCaptcha() 
    {
        Random random=new Random();
        int captcha=1000+random.nextInt(9000);
        return String.valueOf(captcha);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource()==backButton) 
        {
            setVisible(false);
            new Home();
        } 
        else if (ae.getSource()==loginButton) 
        {
            String username=usernameField.getText();
            String password=new String(passwordField.getPassword());
            String captchaInputValue=captchaInput.getText();
            
            if (captchaInputValue.equals(generatedCaptcha)) 
            {
                if(validateLogin(username, password)) 
                {
                    if(adminRadio.isSelected()) 
                    {
                        System.out.println("Admin logged in: "+username);
                        new AdminPage(username);
                    } 
                    else if (operatorRadio.isSelected()) 
                    {
                        System.out.println("Operator logged in: "+username);
                        new OperatorPage(username);
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"Invalid username or password.","Error",JOptionPane.ERROR_MESSAGE);
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(this,"Invalid CAPTCHA. Please try again.","Error",JOptionPane.ERROR_MESSAGE);
                generatedCaptcha=generateCaptcha();
                captchaValue.setText(generatedCaptcha);
            }
        }
    }

    private boolean validateLogin(String username,String password) 
    {
        boolean isValid=false;
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal","root","Shiva2212#");
            
            String sql;
            if (adminRadio.isSelected()) 
            {
                sql="SELECT * FROM Admin WHERE AdminUser = ? AND AdminPass = ?";
            } 
            else if (operatorRadio.isSelected()) 
            {
                sql="SELECT * FROM Operator WHERE OperatorUser = ? AND OperatorPass = ?";
            } 
            else 
            {
                return false;
            }
            
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();

            if(rs.next()) 
            {
                isValid=true;
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        return isValid;
    }

    public static void main(String[] args) 
    {
        new LoginPage();
    }
}
