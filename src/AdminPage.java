package src;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Statement;

public class AdminPage extends JFrame implements ActionListener 
{
    private JLabel adminNameLabel;
    private JLabel clockLabel;
    private String adminUser;
    private Timer timer;

    
    public AdminPage(String adminUser) 
    {
        this.adminUser=adminUser;
        setTitle("Admin Page - "+adminUser);
        setSize(600,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        adminNameLabel=new JLabel("Admin: "+adminUser);
        adminNameLabel.setFont(new Font("Arial",Font.BOLD,20));
        adminNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        clockLabel=new JLabel();
        clockLabel.setFont(new Font("Arial",Font.PLAIN,18));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timer=new Timer(1000,e->updateClock());
        timer.start();

        JPanel headerPanel=new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(adminNameLabel,BorderLayout.NORTH);
        headerPanel.add(clockLabel,BorderLayout.SOUTH);

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(10,10,20,10);
        add(headerPanel,gbc);

        JPanel buttonPanel=new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Admin Operations"));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        String[] buttonLabels={
            "Add Operator", "Update Operator", "View Operator", "Delete Operator",
            "Register FIR", "Update FIR", "Retrieve FIR", "Delete FIR",  "Add Criminal", "Update Criminal Record",
            "Retrieve Criminal Record", "Change Password"
        };

        gbc.gridwidth=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);

        for (int i=0;i<buttonLabels.length;i++) 
        {
            JButton button=new JButton(buttonLabels[i]);
            button.setActionCommand(buttonLabels[i].replace(" ","").toLowerCase());
            button.setFont(new Font("Arial",Font.PLAIN,14));
            button.addActionListener(this);
            gbc.gridx=i%2;
            gbc.gridy=i/2+1;
            buttonPanel.add(button,gbc);
        }

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(0,10,10,10);
        add(buttonPanel,gbc);

        setVisible(true);
    }

    
    private void updateClock() 
    {
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText("Time: "+formatter.format(new Date()));
    }

    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command=e.getActionCommand();
        switch (command) 
        {
            case "addoperator":
                addOperator();
                break;
            case "updateoperator":
                updateOperator();
                break;
            case "viewoperator":
                viewOperator();
                break;
            case "deleteoperator":
                deleteOperator();
                break;
            case "registerfir":
                registerFIR();
                break;
            case "updatefir":
                updateFIR();
                break;
            case "deletefir":
                deleteFIR();
                break;
            case "updatecriminalrecord":
                updateCriminalRecord();
                break;
            case "addcriminal":
                addCriminal();
                break;
            case "retrievefir":
                retrieveFIR();
                break;
            case "retrievecriminalrecord":
                retrieveCriminalRecord();
                break;
            case "changepassword":
                changePassword();
                break;
        }
    }    
    

	private void addOperator() {
        String defaultPassword="default123";

        String operatorUser=JOptionPane.showInputDialog(this, "Enter Operator Username:");
        if(operatorUser==null||operatorUser.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Operator Username cannot be empty.");
            return;
        }

        String operatorName=JOptionPane.showInputDialog(this,"Enter Operator Name:");
        if(operatorName== null||operatorName.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Operator Name cannot be empty.");
            return;
        }

        try (Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal","root","Shiva2212#")) 
        {
            String checkQuery="SELECT * FROM operator WHERE OperatorUser = ?";
            try (PreparedStatement checkStmt=conn.prepareStatement(checkQuery)) 
            {
                checkStmt.setString(1,operatorUser);
                ResultSet rs=checkStmt.executeQuery();
                if (rs.next()) 
                {
                    JOptionPane.showMessageDialog(this,"Operator Username already exists. Please choose a different username.");
                    return;
                }
            }

            String insertOperatorQuery="INSERT INTO operator (OperatorUser, OperatorName, OperatorPass, AdminUser) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt=conn.prepareStatement(insertOperatorQuery)) 
            {
                stmt.setString(1,operatorUser);
                stmt.setString(2,operatorName);
                stmt.setString(3,defaultPassword);
                stmt.setString(4,adminUser);

                int rowsInserted=stmt.executeUpdate();
                if (rowsInserted>0) 
                {
                    String insertLoginQuery="INSERT INTO login (UserName, Password, UserType) VALUES (?, ?, ?)";
                    try(PreparedStatement loginStmt=conn.prepareStatement(insertLoginQuery)) 
                    {
                        loginStmt.setString(1,operatorUser);
                        loginStmt.setString(2,defaultPassword);
                        loginStmt.setString(3,"operator");

                        int loginRowsInserted=loginStmt.executeUpdate();
                        if(loginRowsInserted>0) 
                        {
                            JOptionPane.showMessageDialog(this,"New operator added successfully, and login created.");
                        } 
                        else 
                        {
                            JOptionPane.showMessageDialog(this,"Operator added, but failed to create login.");
                        }
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"Failed to add new operator.");
                }
            }
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
     
    
	private void updateOperator() {
	    String operatorUser=JOptionPane.showInputDialog(this,"Enter Operator Username to update:");
	    if (operatorUser==null||operatorUser.trim().isEmpty()) 
	    {
	        JOptionPane.showMessageDialog(this,"Operator Username cannot be empty.");
	        return;
	    }

	    String checkQuery="SELECT * FROM operator WHERE OperatorUser = ? AND AdminUser = ?";
	    try(Connection conn=DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#");
	         PreparedStatement checkStmt=conn.prepareStatement(checkQuery)) 
	    {
	        
	        checkStmt.setString(1,operatorUser);
	        checkStmt.setString(2,adminUser);
	        ResultSet rs=checkStmt.executeQuery();
	        if(!rs.next()) 
	        {
	            JOptionPane.showMessageDialog(this,"You do not have permission to update this operator.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String newOperatorUser=JOptionPane.showInputDialog(this,"Enter new Operator Username (leave blank to keep the same):");
	        String newOperatorName=JOptionPane.showInputDialog(this,"Enter new Operator Name (leave blank to keep the same):");
	        String newOperatorPass=JOptionPane.showInputDialog(this,"Enter new Operator Password (leave blank to keep the same):");

	        StringBuilder updateQuery=new StringBuilder("UPDATE operator SET ");
	        boolean isFirst=true;

	        if(newOperatorUser!=null&&!newOperatorUser.trim().isEmpty()) 
	        {
	            updateQuery.append("OperatorUser = ?");
	            isFirst=false;
	        }
	        if(newOperatorName!=null&&!newOperatorName.trim().isEmpty()) 
	        {
	            if(!isFirst) updateQuery.append(", ");
	            updateQuery.append("OperatorName = ?");
	            isFirst=false;
	        }
	        if(newOperatorPass!=null&&!newOperatorPass.trim().isEmpty()) 
	        {
	            if(!isFirst) updateQuery.append(", ");
	            updateQuery.append("OperatorPass = ?");
	        }
	        
	        updateQuery.append(" WHERE OperatorUser = ? AND AdminUser = ?");

	        try(PreparedStatement updateStmt = conn.prepareStatement(updateQuery.toString())) 
	        {
	            int index=1;

	            if(newOperatorUser!=null&&!newOperatorUser.trim().isEmpty()) 
	            {
	                updateStmt.setString(index++,newOperatorUser);
	            }
	            if(newOperatorName!=null&&!newOperatorName.trim().isEmpty()) 
	            {
	                updateStmt.setString(index++,newOperatorName);
	            }
	            if(newOperatorPass!=null&&!newOperatorPass.trim().isEmpty()) 
	            {
	                updateStmt.setString(index++,newOperatorPass);
	            }
	            updateStmt.setString(index++,operatorUser);
	            updateStmt.setString(index,adminUser);

	            int rowsUpdated=updateStmt.executeUpdate();
	            if(rowsUpdated>0) 
	            {
	                JOptionPane.showMessageDialog(this,"Operator updated successfully.");

	                if(newOperatorUser!=null&&!newOperatorUser.trim().isEmpty()) 
	                {
	                    String updateCriminalQuery="UPDATE operator_criminal SET OperatorUser = ? WHERE OperatorUser = ?";
	                    try(PreparedStatement updateCriminalStmt=conn.prepareStatement(updateCriminalQuery)) {
	                        updateCriminalStmt.setString(1,newOperatorUser);
	                        updateCriminalStmt.setString(2,operatorUser);
	                        updateCriminalStmt.executeUpdate();
	                    }

	                    String updateFirQuery="UPDATE fir_operator SET OperatorUser = ? WHERE OperatorUser = ?";
	                    try(PreparedStatement updateFirStmt=conn.prepareStatement(updateFirQuery)) 
	                    {
	                        updateFirStmt.setString(1,newOperatorUser);
	                        updateFirStmt.setString(2,operatorUser);
	                        updateFirStmt.executeUpdate();
	                    }

	                    String updateLoginQuery="UPDATE login SET UserName = ? WHERE UserName = ?";
	                    try(PreparedStatement updateLoginStmt=conn.prepareStatement(updateLoginQuery)) 
	                    {
	                        updateLoginStmt.setString(1,newOperatorUser);
	                        updateLoginStmt.setString(2,operatorUser);
	                        updateLoginStmt.executeUpdate();
	                    }
	                }
	            } 
	            else 
	            {
	                JOptionPane.showMessageDialog(this,"Failed to update operator.");
	            }
	        }

	    } 
	    catch (SQLException ex) 
	    {
	        JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
	        ex.printStackTrace();
	    }
	}

    
	private void viewOperator() 
	{
	    String operatorUser=JOptionPane.showInputDialog(this,"Enter Operator Username to view details:");
	    if(operatorUser==null||operatorUser.trim().isEmpty()) 
	    {
	        JOptionPane.showMessageDialog(this,"Operator Username cannot be empty.");
	        return;
	    }

	    try(Connection conn=DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
	    {
	        String operatorQuery="SELECT OperatorName, AdminUser FROM operator WHERE OperatorUser = ? AND AdminUser = ?";
	        try(PreparedStatement operatorStmt = conn.prepareStatement(operatorQuery)) 
	        {
	            operatorStmt.setString(1,operatorUser);
	            operatorStmt.setString(2,adminUser);
	            ResultSet rs=operatorStmt.executeQuery();

	            if(rs.next()) 
	            {
	                String operatorName=rs.getString("OperatorName");

	                String criminalCountQuery="SELECT COUNT(*) AS CriminalCount FROM operator_criminal WHERE OperatorUser = ?";
	                int criminalCount=0;
	                try(PreparedStatement criminalStmt=conn.prepareStatement(criminalCountQuery)) {
	                    criminalStmt.setString(1,operatorUser);
	                    ResultSet criminalRs=criminalStmt.executeQuery();
	                    if(criminalRs.next()) 
	                    {
	                        criminalCount=criminalRs.getInt("CriminalCount");
	                    }
	                }

	                String firCountQuery="SELECT COUNT(*) AS FirCount FROM fir_operator WHERE OperatorUser = ?";
	                int firCount=0;
	                try(PreparedStatement firStmt=conn.prepareStatement(firCountQuery)) 
	                {
	                    firStmt.setString(1,operatorUser);
	                    ResultSet firRs=firStmt.executeQuery();
	                    if(firRs.next()) 
	                    {
	                        firCount=firRs.getInt("FirCount");
	                    }
	                }

	                String message=String.format(
	                        "Operator Username: %s\nOperator Name: %s\nAdmin User: %s\n" +
	                        "Total Criminals Assigned: %d\nTotal FIRs Assigned: %d",
	                        operatorUser, operatorName, adminUser, criminalCount, firCount);

	                JOptionPane.showMessageDialog(this,message,"Operator Details",JOptionPane.INFORMATION_MESSAGE);

	            } 
	            else 
	            {
	                JOptionPane.showMessageDialog(this,"You do not have permission to view this operator or the operator does not exist.", 
	                                              "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }

	    } 
	    catch(SQLException ex) 
	    {
	        JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
	        ex.printStackTrace();
	    }
	}

    
	private void deleteOperator() 
	{
	    String operatorUser=JOptionPane.showInputDialog(this,"Enter Operator Username to delete:");
	    if(operatorUser==null||operatorUser.trim().isEmpty()) 
	    {
	        JOptionPane.showMessageDialog(this,"Operator Username cannot be empty.");
	        return;
	    }

	    try(Connection conn=DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
	    {
	        String checkQuery="SELECT OperatorUser FROM operator WHERE OperatorUser = ? AND AdminUser = ?";
	        try(PreparedStatement checkStmt=conn.prepareStatement(checkQuery)) 
	        {
	            checkStmt.setString(1,operatorUser);
	            checkStmt.setString(2,adminUser);
	            ResultSet rs=checkStmt.executeQuery();
	            if(!rs.next()) 
	            {
	                JOptionPane.showMessageDialog(this,"Operator Username not found or you do not have permission to delete this operator.", 
	                                              "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        }

	        String randomOperatorQuery="SELECT OperatorUser FROM operator WHERE OperatorUser != ? AND AdminUser = ? ORDER BY RAND() LIMIT 1";
	        String randomOperatorUser=null;
	        try(PreparedStatement randomStmt=conn.prepareStatement(randomOperatorQuery)) 
	        {
	            randomStmt.setString(1,operatorUser);
	            randomStmt.setString(2,adminUser);
	            ResultSet randomRs=randomStmt.executeQuery();
	            if(randomRs.next()) 
	            {
	                randomOperatorUser=randomRs.getString("OperatorUser");
	            } 
	            else 
	            {
	                JOptionPane.showMessageDialog(this,"No other operators available to reassign.", 
	                                              "Error",JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        }

	        String reassignCriminalsQuery="UPDATE operator_criminal SET OperatorUser = ? WHERE OperatorUser = ?";
	        try(PreparedStatement reassignCriminalsStmt=conn.prepareStatement(reassignCriminalsQuery)) 
	        {
	            reassignCriminalsStmt.setString(1,randomOperatorUser);
	            reassignCriminalsStmt.setString(2,operatorUser);
	            reassignCriminalsStmt.executeUpdate();
	        }

	        String reassignFIRsQuery="UPDATE fir_operator SET OperatorUser = ? WHERE OperatorUser = ?";
	        try(PreparedStatement reassignFIRsStmt=conn.prepareStatement(reassignFIRsQuery)) 
	        {
	            reassignFIRsStmt.setString(1,randomOperatorUser);
	            reassignFIRsStmt.setString(2,operatorUser);
	            reassignFIRsStmt.executeUpdate();
	        }

	        String deleteOperatorQuery="DELETE FROM operator WHERE OperatorUser = ?";
	        try(PreparedStatement deleteOperatorStmt=conn.prepareStatement(deleteOperatorQuery)) 
	        {
	            deleteOperatorStmt.setString(1,operatorUser);
	            deleteOperatorStmt.executeUpdate();
	        }

	        String deleteLoginQuery="DELETE FROM login WHERE UserName = ?";
	        try(PreparedStatement deleteLoginStmt=conn.prepareStatement(deleteLoginQuery)) 
	        {
	            deleteLoginStmt.setString(1,operatorUser);
	            deleteLoginStmt.executeUpdate();
	        }

	        JOptionPane.showMessageDialog(this,"Operator deleted successfully and reassigned.");

	    } 
	    catch(SQLException ex) 
	    {
	        JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
	        ex.printStackTrace();
	    }
	}

    
    private void registerFIR() 
    {
        String date=JOptionPane.showInputDialog(this,"Enter FIR Date (YYYY-MM-DD):");
        if(date==null||date.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"FIR Date cannot be empty.");
            return;
        }

        String crime=JOptionPane.showInputDialog(this,"Enter Crime:");
        if(crime==null|| crime.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Crime cannot be empty.");
            return;
        }

        String criminalID=JOptionPane.showInputDialog(this,"Enter Criminal ID:");
        if(criminalID==null||criminalID.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Criminal ID cannot be empty.");
            return;
        }

        String crimeLocation=JOptionPane.showInputDialog(this,"Enter Crime Location:");
        if(crimeLocation==null||crimeLocation.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Crime Location cannot be empty.");
            return;
        }

        String detailedComplaint=JOptionPane.showInputDialog(this,"Enter Detailed Complaint:");
        if(detailedComplaint==null||detailedComplaint.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Detailed Complaint cannot be empty.");
            return;
        }

        String status=JOptionPane.showInputDialog(this,"Enter Status:");
        if(status==null||status.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Status cannot be empty.");
            return;
        }

        String accuserDetails=JOptionPane.showInputDialog(this,"Enter Accuser Details:");
        if(accuserDetails==null||accuserDetails.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Accuser Details cannot be empty.");
            return;
        }

        try(Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String randomOperatorQuery="SELECT OperatorUser FROM operator ORDER BY RAND() LIMIT 1";
            String assignedOperatorUser=null;
            try(PreparedStatement randomStmt=conn.prepareStatement(randomOperatorQuery)) 
            {
                ResultSet randomRs=randomStmt.executeQuery();
                if(randomRs.next()) 
                {
                    assignedOperatorUser=randomRs.getString("OperatorUser");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"No operators available to assign.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String insertFIRQuery="INSERT INTO fir (Date, Crime, CriminalID, CrimeLocation, DetailedComplaint, Status, AccuserDetails, OperatorUser) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            int firNo=0;
            try(PreparedStatement insertFIRStmt=conn.prepareStatement(insertFIRQuery,Statement.RETURN_GENERATED_KEYS)) 
            {
                insertFIRStmt.setString(1,date);
                insertFIRStmt.setString(2,crime);
                insertFIRStmt.setString(3,criminalID);
                insertFIRStmt.setString(4,crimeLocation);
                insertFIRStmt.setString(5,detailedComplaint);
                insertFIRStmt.setString(6,status);
                insertFIRStmt.setString(7,accuserDetails);
                insertFIRStmt.setString(8,assignedOperatorUser);
                int rowsInserted=insertFIRStmt.executeUpdate();

                if(rowsInserted>0) 
                {
                    ResultSet generatedKeys=insertFIRStmt.getGeneratedKeys();
                    if(generatedKeys.next()) 
                    {
                        firNo=generatedKeys.getInt(1);
                    }
                    JOptionPane.showMessageDialog(this,"FIR registered successfully.");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"Failed to register FIR.");
                    return;
                }
            }

            String insertFIROperatorQuery="INSERT INTO fir_operator (FIRNo, OperatorUser) VALUES (?, ?)";
            try(PreparedStatement insertFIROperatorStmt=conn.prepareStatement(insertFIROperatorQuery)) 
            {
                insertFIROperatorStmt.setInt(1,firNo);
                insertFIROperatorStmt.setString(2,assignedOperatorUser);
                insertFIROperatorStmt.executeUpdate();
            }

            String adminUser="admin_1";
            String insertFIRAdminQuery="INSERT INTO fir_admin (FIRNo, AdminUser) VALUES (?, ?)";
            try(PreparedStatement insertFIRAdminStmt=conn.prepareStatement(insertFIRAdminQuery)) 
            {
                insertFIRAdminStmt.setInt(1,firNo);
                insertFIRAdminStmt.setString(2,adminUser);
                insertFIRAdminStmt.executeUpdate();
            }

            String getCriminalAadharQuery="SELECT Aadhaar FROM criminal WHERE CriminalNo = ?";
            String criminalAadhaar=null;
            try(PreparedStatement aadharStmt=conn.prepareStatement(getCriminalAadharQuery)) 
            {
                aadharStmt.setString(1,criminalID);
                ResultSet aadhaarRs=aadharStmt.executeQuery();
                if(aadhaarRs.next()) 
                {
                    criminalAadhaar=aadhaarRs.getString("Aadhaar");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"No criminal found with the given ID.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String insertFIRCriminalQuery="INSERT INTO fir_criminal (CriminalNo, FIRNo, CriminalAadhar) VALUES (?, ?, ?)";
            try(PreparedStatement insertFIRCriminalStmt=conn.prepareStatement(insertFIRCriminalQuery)) 
            {
                insertFIRCriminalStmt.setString(1,criminalID);
                insertFIRCriminalStmt.setInt(2,firNo);
                insertFIRCriminalStmt.setString(3,criminalAadhaar);
                insertFIRCriminalStmt.executeUpdate();
            }

        } 
        catch(SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    private void updateFIR() 
    {
        String firNoStr=JOptionPane.showInputDialog(this,"Enter FIR Number to update:");
        if(firNoStr==null||firNoStr.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"FIR Number cannot be empty.");
            return;
        }

        int firNo;
        try 
        {
            firNo=Integer.parseInt(firNoStr);
        } 
        catch(NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this,"Invalid FIR Number.");
            return;
        }

        String checkFIRAdminQuery="SELECT COUNT(*) FROM fir_admin WHERE FIRNo = ? AND AdminUser = ?";

        try(Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#");
             PreparedStatement checkFIRAdminStmt=conn.prepareStatement(checkFIRAdminQuery)) 
        {
            checkFIRAdminStmt.setInt(1,firNo);
            checkFIRAdminStmt.setString(2,adminUser);

            System.out.println("Checking FIR ownership for AdminUser: "+adminUser+" and FIRNo: "+firNo);

            ResultSet adminResult=checkFIRAdminStmt.executeQuery();
            if(adminResult.next()&&adminResult.getInt(1)==0) 
            {
                JOptionPane.showMessageDialog(this,"You do not have permission to update this FIR.", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            } 
            else 
            {
                System.out.println("FIR ownership check passed for AdminUser: "+adminUser);
            }
        } 
        catch(SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
            return;
        }

        String selectFIRQuery="SELECT Crime, CriminalID, Status, OperatorUser FROM fir WHERE FIRNo = ?";
        try(Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#");
             PreparedStatement selectFIRStmt=conn.prepareStatement(selectFIRQuery)) 
        {
            selectFIRStmt.setInt(1,firNo);
            ResultSet firResult=selectFIRStmt.executeQuery();

            if(!firResult.next()) 
            {
                JOptionPane.showMessageDialog(this,"No FIR found with the given number.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            String currentCrime=firResult.getString("Crime");
            String currentCriminalID=firResult.getString("CriminalID");
            String currentStatus=firResult.getString("Status");
            String currentOperatorUser=firResult.getString("OperatorUser");

            String crime=JOptionPane.showInputDialog(this,"Enter new Crime (leave empty to keep current):", currentCrime);
            if(crime==null||crime.trim().isEmpty()) 
            {
                crime=currentCrime;
            }

            String criminalID=JOptionPane.showInputDialog(this,"Enter new Criminal ID (leave empty to keep current):", currentCriminalID);
            if(criminalID==null||criminalID.trim().isEmpty()) 
            {
                criminalID=currentCriminalID;
            }

            String status=JOptionPane.showInputDialog(this,"Enter new Status (leave empty to keep current):",currentStatus);
            if(status==null||status.trim().isEmpty()) 
            {
                status=currentStatus;
            }

            String operatorUser=JOptionPane.showInputDialog(this,"Enter new Operator User (leave empty to keep current):", currentOperatorUser);
            if(operatorUser==null||operatorUser.trim().isEmpty()) 
            {
                operatorUser=currentOperatorUser;
            }

            String updateFIRQuery="UPDATE fir SET Crime = ?, CriminalID = ?, Status = ?, OperatorUser = ? WHERE FIRNo = ?";
            try(PreparedStatement updateFIRStmt=conn.prepareStatement(updateFIRQuery)) 
            {
                updateFIRStmt.setString(1,crime);
                updateFIRStmt.setString(2,criminalID);
                updateFIRStmt.setString(3,status);
                updateFIRStmt.setString(4,operatorUser);
                updateFIRStmt.setInt(5,firNo);
                int rowsUpdated=updateFIRStmt.executeUpdate();

                if(rowsUpdated>0) 
                {
                    JOptionPane.showMessageDialog(this,"FIR updated successfully.");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"Failed to update FIR.");
                    return;
                }
            }

            if(!operatorUser.equals(currentOperatorUser)) 
            {
                String updateFIROperatorQuery="INSERT INTO fir_operator (FIRNo, OperatorUser) VALUES (?, ?) ON DUPLICATE KEY UPDATE OperatorUser = ?";
                try(PreparedStatement updateFIROperatorStmt=conn.prepareStatement(updateFIROperatorQuery)) 
                {
                    updateFIROperatorStmt.setInt(1,firNo);
                    updateFIROperatorStmt.setString(2,operatorUser);
                    updateFIROperatorStmt.setString(3,operatorUser);
                    updateFIROperatorStmt.executeUpdate();
                }
            }

            if(!criminalID.equals(currentCriminalID)) 
            {
                String getCriminalAadhaarQuery="SELECT Aadhaar FROM criminal WHERE CriminalNo = ?";
                String criminalAadhaar=null;
                try (PreparedStatement aadhaarStmt=conn.prepareStatement(getCriminalAadhaarQuery)) 
                {
                    aadhaarStmt.setString(1,criminalID);
                    ResultSet aadhaarRs=aadhaarStmt.executeQuery();
                    if(aadhaarRs.next()) 
                    {
                        criminalAadhaar=aadhaarRs.getString("Aadhaar");
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this,"No criminal found with the given ID.","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String updateFIRCriminalQuery="INSERT INTO fir_criminal (CriminalNo, FIRNo, CriminalAadhar) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE CriminalAadhar = ?";
                try (PreparedStatement updateFIRCriminalStmt=conn.prepareStatement(updateFIRCriminalQuery)) 
                {
                    updateFIRCriminalStmt.setString(1,criminalID);
                    updateFIRCriminalStmt.setInt(2,firNo);
                    updateFIRCriminalStmt.setString(3,criminalAadhaar);
                    updateFIRCriminalStmt.setString(4,criminalAadhaar);
                    updateFIRCriminalStmt.executeUpdate();
                }
            }

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }    
    
    
    private void deleteFIR() 
    {
        String firNo=JOptionPane.showInputDialog(this,"Enter FIR Number to delete:");
        if(firNo==null||firNo.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"FIR Number cannot be empty.");
            return;
        }

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String checkQuery="SELECT * FROM fir WHERE FIRNo = ?";
            try(PreparedStatement checkStmt=conn.prepareStatement(checkQuery)) 
            {
                checkStmt.setString(1,firNo);
                ResultSet rs=checkStmt.executeQuery();
                if(!rs.next()) 
                {
                    JOptionPane.showMessageDialog(this,"FIR not found.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String accessCheckQuery="SELECT * FROM fir_admin WHERE FIRNo = ? AND AdminUser = ?";
            try(PreparedStatement accessStmt=conn.prepareStatement(accessCheckQuery)) 
            {
                accessStmt.setString(1,firNo);
                accessStmt.setString(2,adminUser);
                ResultSet accessRs=accessStmt.executeQuery();
                if(!accessRs.next()) 
                {
                    JOptionPane.showMessageDialog(this,"You do not have permission to delete this FIR.", "Permission Denied", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String deleteOperatorQuery="DELETE FROM fir_operator WHERE FIRNo = ?";
            try(PreparedStatement deleteOperatorStmt=conn.prepareStatement(deleteOperatorQuery)) 
            {
                deleteOperatorStmt.setString(1,firNo);
                deleteOperatorStmt.executeUpdate();
            }

            String deleteCriminalQuery="DELETE FROM fir_criminal WHERE FIRNo = ?";
            try(PreparedStatement deleteCriminalStmt=conn.prepareStatement(deleteCriminalQuery)) 
            {
                deleteCriminalStmt.setString(1,firNo);
                deleteCriminalStmt.executeUpdate();
            }

            String deleteAdminQuery="DELETE FROM fir_admin WHERE FIRNo = ?";
            try(PreparedStatement deleteAdminStmt=conn.prepareStatement(deleteAdminQuery)) 
            {
                deleteAdminStmt.setString(1,firNo);
                deleteAdminStmt.executeUpdate();
            }

            String deleteFirQuery="DELETE FROM fir WHERE FIRNo = ?";
            try(PreparedStatement deleteFirStmt=conn.prepareStatement(deleteFirQuery)) 
            {
                deleteFirStmt.setString(1,firNo);
                deleteFirStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(this,"FIR deleted successfully.");

        } 
        catch(SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
   
    
    private void retrieveFIR() 
    {
        String firNo=JOptionPane.showInputDialog(this,"Enter FIR Number to retrieve:");
        if(firNo==null||firNo.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"FIR Number cannot be empty.");
            return;
        }

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String accessCheckQuery="SELECT * FROM fir_admin WHERE FIRNo = ? AND AdminUser = ?";
            try (PreparedStatement accessStmt=conn.prepareStatement(accessCheckQuery)) 
            {
                accessStmt.setString(1,firNo);
                accessStmt.setString(2,adminUser);
                ResultSet accessRs=accessStmt.executeQuery();
                if(!accessRs.next()) 
                {
                    JOptionPane.showMessageDialog(this,"You do not have permission to retrieve this FIR.", "Permission Denied", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String firDetailsQuery="SELECT Date, Crime, CriminalID, CrimeLocation, DetailedComplaint, Status, AccuserDetails " +
                                     "FROM fir WHERE FIRNo = ?";
            StringBuilder firDetails=new StringBuilder();
            try(PreparedStatement firStmt=conn.prepareStatement(firDetailsQuery)) 
            {
                firStmt.setString(1,firNo);
                ResultSet firRs=firStmt.executeQuery();
                if(firRs.next())
                {
                    String date=firRs.getString("Date");
                    String crime=firRs.getString("Crime");
                    String criminalId=firRs.getString("CriminalID");
                    String location=firRs.getString("CrimeLocation");
                    String complaint=firRs.getString("DetailedComplaint");
                    String status=firRs.getString("Status");
                    String accuserDetails=firRs.getString("AccuserDetails");
           

                    firDetails.append(String.format("FIR Number: %s\nDate: %s\nCrime: %s\nCriminal ID: %s\nCrime Location: %s\n" +
                                                    "Detailed Complaint: %s\nStatus: %s\nAccuser Details: %s\n",
                                                    firNo, date, crime, criminalId, location, complaint, status, accuserDetails));
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"FIR not found.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String operatorDetailsQuery="SELECT OperatorUser FROM fir_operator WHERE FIRNo = ?";
            try(PreparedStatement operatorStmt=conn.prepareStatement(operatorDetailsQuery)) 
            {
                operatorStmt.setString(1,firNo);
                ResultSet operatorRs=operatorStmt.executeQuery();
                if(operatorRs.next()) 
                {
                    String operatorUser=operatorRs.getString("OperatorUser");
                    firDetails.append("Assigned Operator: ").append(operatorUser).append("\n");
                }
            }

            String criminalDetailsQuery="SELECT GetCriminalAadhaar(?) AS CriminalAadhaarNumbers";
            try(PreparedStatement criminalStmt=conn.prepareStatement(criminalDetailsQuery)) 
            {
                criminalStmt.setInt(1,Integer.parseInt(firNo));
                ResultSet criminalRs=criminalStmt.executeQuery();
                
                if(criminalRs.next()) 
                {
                    String criminalAadhaar=criminalRs.getString("CriminalAadhaarNumbers");
                    firDetails.append("Criminal's Aadhaar Numbers: ").append(criminalAadhaar).append("\n");
                } 
                else 
                    firDetails.append("No Aadhaar numbers found for this FIR.\n");
            }

            JOptionPane.showMessageDialog(this,firDetails.toString(),"FIR Details",JOptionPane.INFORMATION_MESSAGE);

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    private void addCriminal() 
    {
        String criminalNo=JOptionPane.showInputDialog(this,"Enter Criminal Number:");
        String aadhaar=JOptionPane.showInputDialog(this,"Enter Aadhaar Number:");
        String name=JOptionPane.showInputDialog(this,"Enter Criminal Name:");
        String alias=JOptionPane.showInputDialog(this,"Enter Alias (if any):");
        String gender=JOptionPane.showInputDialog(this,"Enter Gender:");
        String address=JOptionPane.showInputDialog(this,"Enter Address:");
        
        if (criminalNo==null||aadhaar==null||name==null||gender==null||address==null||adminUser==null||
            criminalNo.trim().isEmpty()||aadhaar.trim().isEmpty()||name.trim().isEmpty()||gender.trim().isEmpty()||address.trim().isEmpty()||adminUser.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"All fields are required.");
            return;
        }

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String randomOperatorQuery="SELECT OperatorUser FROM operator ORDER BY RAND() LIMIT 1";
            String randomOperatorUser=null;

            try(PreparedStatement randomStmt=conn.prepareStatement(randomOperatorQuery)) 
            {
                ResultSet rs=randomStmt.executeQuery();
                if(rs.next()) 
                {
                    randomOperatorUser=rs.getString("OperatorUser");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"No operators available to assign.");
                    return;
                }
            }

            String criminalInsertQuery="INSERT INTO criminal (CriminalNo, Aadhaar, Name, Aliase, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement criminalStmt=conn.prepareStatement(criminalInsertQuery)) 
            {
                criminalStmt.setString(1,criminalNo);
                criminalStmt.setString(2,aadhaar);
                criminalStmt.setString(3,name);
                criminalStmt.setString(4,alias);
                criminalStmt.setString(5,gender);
                criminalStmt.setString(6,address);
                criminalStmt.executeUpdate();
            }

            String operatorCriminalInsertQuery="INSERT INTO operator_criminal (CriminalNo, OperatorUser, CriminalAadhar) VALUES (?, ?, ?)";
            try(PreparedStatement operatorCriminalStmt=conn.prepareStatement(operatorCriminalInsertQuery)) 
            {
                operatorCriminalStmt.setString(1,criminalNo);
                operatorCriminalStmt.setString(2,randomOperatorUser);
                operatorCriminalStmt.setString(3,aadhaar);
                operatorCriminalStmt.executeUpdate();
            }

            String criminalAdminInsertQuery="INSERT INTO criminal_admin (CriminalNo, AdminUser, CriminalAadhar) VALUES (?, ?, ?)";
            try(PreparedStatement criminalAdminStmt=conn.prepareStatement(criminalAdminInsertQuery)) 
            {
                criminalAdminStmt.setString(1,criminalNo);
                criminalAdminStmt.setString(2,adminUser);
                criminalAdminStmt.setString(3,aadhaar);
                criminalAdminStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(this,"Criminal added successfully and assigned to operator: "+randomOperatorUser);

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    private void updateCriminalRecord() 
    {
        String criminalId=JOptionPane.showInputDialog(this,"Enter Criminal ID to Update:");

        if(criminalId==null||adminUser==null||criminalId.trim().isEmpty()||adminUser.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Criminal ID and Admin username are required.");
            return;
        }

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String accessCheckQuery="SELECT criminal.CriminalNo, criminal.Aadhaar, criminal.Name, criminal.Aliase, criminal.Gender, criminal.Address " +
                                      "FROM criminal_admin " +
                                      "JOIN criminal ON criminal_admin.CriminalNo = criminal.CriminalNo " +
                                      "WHERE criminal_admin.CriminalNo = ? AND criminal_admin.AdminUser = ?";
            String criminalNo=null;
            String aadhaar=null;
            String name=null;
            String alias=null;
            String gender=null;
            String address=null;

            try(PreparedStatement accessStmt=conn.prepareStatement(accessCheckQuery)) 
            {
                accessStmt.setString(1,criminalId);
                accessStmt.setString(2,adminUser);
                ResultSet rs=accessStmt.executeQuery();
                if(rs.next()) 
                {
                    criminalNo=rs.getString("CriminalNo");
                    aadhaar=rs.getString("Aadhaar");
                    name=rs.getString("Name");
                    alias=rs.getString("Aliase");
                    gender=rs.getString("Gender");
                    address=rs.getString("Address");
                } else {
                    JOptionPane.showMessageDialog(this,"Access denied or Criminal not found.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String newAadhaar=JOptionPane.showInputDialog(this,"Enter New Aadhaar Number (current: " + aadhaar + "):", aadhaar);
            String newName=JOptionPane.showInputDialog(this,"Enter Criminal Name (current: " + name + "):", name);
            String newAlias=JOptionPane.showInputDialog(this,"Enter Alias (if any, current: " + alias + "):", alias);
            String newGender=JOptionPane.showInputDialog(this,"Enter Gender (current: " + gender + "):", gender);
            String newAddress=JOptionPane.showInputDialog(this,"Enter Address (current: " + address + "):", address);

            String changeOperator=JOptionPane.showInputDialog(this,"Change Operator? (yes/no):");
            String newOperatorUser=null;
            if("yes".equalsIgnoreCase(changeOperator)) 
            {
                String randomOperatorQuery="SELECT OperatorUser FROM operator ORDER BY RAND() LIMIT 1";
                try(PreparedStatement randomStmt=conn.prepareStatement(randomOperatorQuery)) 
                {
                    ResultSet randomRs=randomStmt.executeQuery();
                    if(randomRs.next()) 
                    {
                        newOperatorUser=randomRs.getString("OperatorUser");
                    }
                }
            }

            String updateCriminalQuery="UPDATE criminal SET Aadhaar = ?, Name = ?, Aliase = ?, Gender = ?, Address = ? WHERE CriminalNo = ?";
            try(PreparedStatement updateCriminalStmt=conn.prepareStatement(updateCriminalQuery)) 
            {
                updateCriminalStmt.setString(1,newAadhaar);
                updateCriminalStmt.setString(2,newName);
                updateCriminalStmt.setString(3,newAlias);
                updateCriminalStmt.setString(4,newGender);
                updateCriminalStmt.setString(5,newAddress);
                updateCriminalStmt.setString(6,criminalNo);
                updateCriminalStmt.executeUpdate();
            }

            if(!aadhaar.equals(newAadhaar)) 
            {
                String updateOperatorCriminalQuery="UPDATE operator_criminal SET CriminalAadhar = ? WHERE CriminalAadhar = ?";
                try(PreparedStatement updateOperatorCriminalStmt=conn.prepareStatement(updateOperatorCriminalQuery)) 
                {
                    updateOperatorCriminalStmt.setString(1,newAadhaar);
                    updateOperatorCriminalStmt.setString(2,aadhaar);
                    updateOperatorCriminalStmt.executeUpdate();
                }

                String updateCriminalAdminQuery="UPDATE criminal_admin SET CriminalAadhar = ? WHERE CriminalAadhar = ?";
                try(PreparedStatement updateCriminalAdminStmt=conn.prepareStatement(updateCriminalAdminQuery)) 
                {
                    updateCriminalAdminStmt.setString(1,newAadhaar);
                    updateCriminalAdminStmt.setString(2,aadhaar);
                    updateCriminalAdminStmt.executeUpdate();
                }

                String updateFIRCriminalQuery="UPDATE fir_criminal SET CriminalAadhar = ? WHERE CriminalAadhar = ?";
                try(PreparedStatement updateFIRCriminalStmt = conn.prepareStatement(updateFIRCriminalQuery)) 
                {
                    updateFIRCriminalStmt.setString(1,newAadhaar);
                    updateFIRCriminalStmt.setString(2,aadhaar);
                    updateFIRCriminalStmt.executeUpdate();
                }
            }

            if(newOperatorUser!=null) 
            {
                String updateOperatorAssignmentQuery="UPDATE operator_criminal SET OperatorUser = ? WHERE CriminalNo = ?";
                try(PreparedStatement updateOperatorAssignmentStmt=conn.prepareStatement(updateOperatorAssignmentQuery)) 
                {
                    updateOperatorAssignmentStmt.setString(1,newOperatorUser);
                    updateOperatorAssignmentStmt.setString(2,criminalNo);
                    updateOperatorAssignmentStmt.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(this,"Criminal details updated successfully.");

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
      
    
    private void retrieveCriminalRecord() 
    {
        String criminalId = JOptionPane.showInputDialog(this, "Enter Criminal ID to retrieve record:");
        if (criminalId == null || criminalId.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Criminal ID cannot be empty.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String query = "CALL GetCriminalRecordDetails(?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) 
            {
                stmt.setString(1, adminUser);
                stmt.setString(2, criminalId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) 
                {
                    String criminalNo = rs.getString("CriminalNo");
                    String aadhaar = rs.getString("Aadhaar");
                    String name = rs.getString("Name");
                    String alias = rs.getString("Aliase");
                    String gender = rs.getString("Gender");
                    String address = rs.getString("Address");
                    String operatorUser = rs.getString("OperatorUser");
                    String adminUser = rs.getString("AdminUser");
                    int numberOfCases = rs.getInt("NumberOfCases");

                    String message = String.format(
                            "Criminal ID: %s\nAadhaar: %s\nName: %s\nAlias: %s\nGender: %s\nAddress: %s\n" +
                            "Operator Handling: %s\nAdmin Handling: %s\nNumber of Cases: %d",
                            criminalNo, aadhaar, name, alias, gender, address, operatorUser, adminUser, numberOfCases);

                    JOptionPane.showMessageDialog(this, message, "Criminal Record Details", JOptionPane.INFORMATION_MESSAGE);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "No access to the requested criminal record.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
  
    
    private void changePassword() 
    {
        String currentPassword=JOptionPane.showInputDialog(this,"Enter your Current Password:");
        if(currentPassword==null||currentPassword.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Current Password cannot be empty.");
            return;
        }

        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String verifyPasswordQuery="SELECT AdminPass FROM admin WHERE AdminUser = ?";
            try(PreparedStatement verifyStmt=conn.prepareStatement(verifyPasswordQuery)) 
            {
                verifyStmt.setString(1,adminUser);
                ResultSet rs=verifyStmt.executeQuery();

                if(rs.next()) 
                {
                    String storedPassword=rs.getString("AdminPass");
                    if(!storedPassword.equals(currentPassword)) 
                    {
                        JOptionPane.showMessageDialog(this,"Current password is incorrect.","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Admin Username not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String newPassword=JOptionPane.showInputDialog(this,"Enter New Password:");
            if(newPassword==null||newPassword.trim().isEmpty()) 
            {
                JOptionPane.showMessageDialog(this,"New Password cannot be empty.");
                return;
            }

            String confirmPassword=JOptionPane.showInputDialog(this,"Confirm New Password:");
            if(!newPassword.equals(confirmPassword)) 
            {
                JOptionPane.showMessageDialog(this,"Passwords do not match.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            String updateLoginQuery="UPDATE login SET Password = ? WHERE UserName = ?";
            try (PreparedStatement updateLoginStmt=conn.prepareStatement(updateLoginQuery)) 
            {
                updateLoginStmt.setString(1,newPassword);
                updateLoginStmt.setString(2,adminUser);
                updateLoginStmt.executeUpdate();
            }

            String updateAdminQuery="UPDATE admin SET AdminPass = ? WHERE AdminUser = ?";
            try(PreparedStatement updateAdminStmt=conn.prepareStatement(updateAdminQuery)) 
            {
                updateAdminStmt.setString(1,newPassword);
                updateAdminStmt.setString(2,adminUser);
                updateAdminStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(this,"Password updated successfully.");
            
        } 
        catch(SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }      
}