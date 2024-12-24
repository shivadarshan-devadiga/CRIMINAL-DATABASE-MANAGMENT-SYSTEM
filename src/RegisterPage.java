package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class RegisterPage extends JFrame implements ActionListener 
{
    
    JTextField operatorUserField,operatorNameField,captchaInput;
    JPasswordField operatorPassField;
    JButton registerButton,backButton;
    JLabel captchaLabel,captchaValue,captchaInputLabel;
    String generatedCaptcha;

    RegisterPage() 
    {
        setLayout(null);
        getContentPane().setBackground(new Color(240,240,240));
        
        JLabel heading=new JLabel("Registration");
        heading.setBounds(140,30,200,30);
        heading.setFont(new Font("Raleway",Font.BOLD,24));
        heading.setForeground(new Color(34,34,34));
        add(heading);
        
        JLabel operatorUserLabel=new JLabel("Operator User:");
        operatorUserLabel.setBounds(50,80,150,30);
        operatorUserLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(operatorUserLabel);
        
        operatorUserField=new JTextField();
        operatorUserField.setBounds(200,80,150,30);
        operatorUserField.setFont(new Font("Raleway",Font.PLAIN,16));
        add(operatorUserField);
        
        JLabel operatorNameLabel=new JLabel("Operator Name:");
        operatorNameLabel.setBounds(50,130,150,30);
        operatorNameLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(operatorNameLabel);
        
        operatorNameField=new JTextField();
        operatorNameField.setBounds(200,130,150,30);
        operatorNameField.setFont(new Font("Raleway",Font.PLAIN,16));
        add(operatorNameField);
        
        JLabel operatorPassLabel=new JLabel("Operator Password:");
        operatorPassLabel.setBounds(50,180,150,30);
        operatorPassLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(operatorPassLabel);
        
        operatorPassField=new JPasswordField();
        operatorPassField.setBounds(200,180,150,30);
        operatorPassField.setFont(new Font("Raleway",Font.PLAIN,16));
        add(operatorPassField);
        
        captchaLabel=new JLabel("Captcha: ");
        captchaLabel.setBounds(50,230,100,30);
        captchaLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(captchaLabel);
        
        generatedCaptcha=generateCaptcha();
        captchaValue=new JLabel(generatedCaptcha);
        captchaValue.setBounds(200,230,100,30);
        captchaValue.setFont(new Font("Raleway",Font.BOLD,18));
        captchaValue.setForeground(new Color(34,139,34));
        add(captchaValue);
        
        captchaInputLabel=new JLabel("Enter Captcha:");
        captchaInputLabel.setBounds(50,270,150,30);
        captchaInputLabel.setFont(new Font("Raleway",Font.PLAIN,18));
        add(captchaInputLabel);
        
        captchaInput=new JTextField();
        captchaInput.setBounds(200,270,150,30);
        captchaInput.setFont(new Font("Raleway",Font.PLAIN,16));
        add(captchaInput);
        
        registerButton=new JButton("Register");
        styleButton(registerButton);
        registerButton.setBounds(50,320,100,40);
        registerButton.addActionListener(this);
        add(registerButton);
        
        backButton=new JButton("Back");
        styleButton(backButton);
        backButton.setBounds(200,320,100,40);
        backButton.addActionListener(this);
        add(backButton);
        
        setSize(400,400);
        setLocation(400,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void styleButton(JButton button) 
    {
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
        if(ae.getSource()==backButton) 
        {
            setVisible(false);
            new LoginPage();
        } 
        else if(ae.getSource()==registerButton) 
        {
            String operatorUser=operatorUserField.getText();
            String operatorName=operatorNameField.getText();
            String operatorPass=new String(operatorPassField.getPassword());
            String captchaInputValue=captchaInput.getText();
            
            if(captchaInputValue.equals(generatedCaptcha)) 
            {
                if(registerOperator(operatorUser, operatorName, operatorPass)) 
                {
                    JOptionPane.showMessageDialog(this,"Registration successful!","Success",JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    new LoginPage();
                } 
                else{
                    JOptionPane.showMessageDialog(this, "Registration failed. Please try again.","Error",JOptionPane.ERROR_MESSAGE);
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid CAPTCHA. Please try again.","Error",JOptionPane.ERROR_MESSAGE);
                generatedCaptcha=generateCaptcha();
                captchaValue.setText(generatedCaptcha);
            }
        }
    }

    private boolean registerOperator(String operatorUser,String operatorName,String operatorPass) 
    {
        boolean isRegistered=false;
        Connection con=null;
        PreparedStatement pstmt=null;

        try 
        {
            String randomAdminUser=getRandomAdminUser();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal","root","Shiva2212#");

            String operatorSql="INSERT INTO operator (OperatorUser, OperatorName, OperatorPass, AdminUser) VALUES (?, ?, ?, ?)";
            pstmt=con.prepareStatement(operatorSql);
            pstmt.setString(1,operatorUser);
            pstmt.setString(2,operatorName);
            pstmt.setString(3,operatorPass);
            pstmt.setString(4,randomAdminUser);
            pstmt.executeUpdate();

            String loginSql="INSERT INTO login (UserName, Password, UserType) VALUES (?, ?, 'operator')";
            pstmt=con.prepareStatement(loginSql);
            pstmt.setString(1,operatorUser);
            pstmt.setString(2,operatorPass);
            pstmt.executeUpdate();
            isRegistered=true;
        } catch (Exception e) 
        {
            e.printStackTrace();
        } 
 
        return isRegistered;
    }

    private String getRandomAdminUser() 
    {
        String adminUser=null;
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#");
            String sql="SELECT AdminUser FROM Admin ORDER BY RAND() LIMIT 1"; // Randomly select an admin user
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();

            if(rs.next()) 
            {
                adminUser = rs.getString("AdminUser");
            }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }

        return adminUser;
    }

    public static void main(String[] args) 
    {
        new RegisterPage();
    }
}
