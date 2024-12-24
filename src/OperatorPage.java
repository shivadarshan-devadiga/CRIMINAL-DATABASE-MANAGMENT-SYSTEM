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

public class OperatorPage extends JFrame implements ActionListener 
{
    private JLabel operatorNameLabel;
    private JLabel clockLabel;
    private String operatorUser;
    private Timer timer;

    
    public OperatorPage(String operatorUser) 
    {
        this.operatorUser=operatorUser;
        setTitle("Operator Page - "+operatorUser);
        setSize(600,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        operatorNameLabel=new JLabel("Operator: "+operatorUser);
        operatorNameLabel.setFont(new Font("Arial",Font.BOLD,20));
        operatorNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        clockLabel=new JLabel();
        clockLabel.setFont(new Font("Arial",Font.PLAIN,18));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timer=new Timer(1000,e->updateClock());
        timer.start();

        JPanel headerPanel=new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(operatorNameLabel,BorderLayout.NORTH);
        headerPanel.add(clockLabel,BorderLayout.SOUTH);

        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(10,10,20,10);
        add(headerPanel,gbc);

        JPanel buttonPanel=new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Operator Operations"));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        String[] buttonLabels={
            "Register FIR", "Update FIR", "Retrieve FIR","Add Criminal", 
            "Update Criminal Record", "Retrieve Criminal Record","Change Password"
        };

        gbc.gridwidth=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets=new Insets(10,10,10,10);

        for(int i=0;i<buttonLabels.length;i++) 
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
            case "registerfir":
                registerFIR();
                break;
            case "updatefir":
                updateFIR();
                break;
            case "addcriminal":
                addCriminal();
                break;
            case "updatecriminalrecord":
                updateCriminalRecord();
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
    
    
    private void registerFIR() 
    {
        String date=JOptionPane.showInputDialog(this,"Enter FIR Date (YYYY-MM-DD):");
        if(date==null||date.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"FIR Date cannot be empty.");
            return;
        }

        String crime=JOptionPane.showInputDialog(this,"Enter Crime:");
        if(crime==null||crime.trim().isEmpty()) 
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
                insertFIRStmt.setString(8,operatorUser);

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
                insertFIROperatorStmt.setString(2,operatorUser);
                insertFIROperatorStmt.executeUpdate();
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
            try (PreparedStatement insertFIRCriminalStmt=conn.prepareStatement(insertFIRCriminalQuery)) 
            {
                insertFIRCriminalStmt.setString(1,criminalID);
                insertFIRCriminalStmt.setInt(2,firNo);
                insertFIRCriminalStmt.setString(3,criminalAadhaar);
                insertFIRCriminalStmt.executeUpdate();
            }

            String[] admins={"admin_1","admin_2"};
            String selectedAdmin=admins[new java.util.Random().nextInt(admins.length)];

            String insertFIRAdminQuery="INSERT INTO fir_admin (FIRNo, AdminUser) VALUES (?, ?)";
            try (PreparedStatement insertFIRAdminStmt=conn.prepareStatement(insertFIRAdminQuery)) 
            {
                insertFIRAdminStmt.setInt(1,firNo);
                insertFIRAdminStmt.setString(2,selectedAdmin);
                insertFIRAdminStmt.executeUpdate();
            }

        } 
        catch (SQLException ex) 
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

        String checkFIROperatorQuery="SELECT COUNT(*) FROM fir_operator WHERE FIRNo = ? AND OperatorUser = ?";
        try(Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#");
             PreparedStatement checkFIROperatorStmt=conn.prepareStatement(checkFIROperatorQuery)) 
        {
            checkFIROperatorStmt.setInt(1,firNo);
            checkFIROperatorStmt.setString(2,operatorUser);

            System.out.println("Checking FIR ownership for OperatorUser: "+operatorUser+" and FIRNo: "+firNo);

            ResultSet operatorResult=checkFIROperatorStmt.executeQuery();
            if(operatorResult.next()&&operatorResult.getInt(1)==0) 
            {
                JOptionPane.showMessageDialog(this,"You do not have permission to update this FIR.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            else 
            {
                System.out.println("FIR ownership check passed for OperatorUser: "+operatorUser);
            }
        } 
        catch(SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
            return;
        }

        String selectFIRQuery="SELECT Crime, CriminalID, Status FROM fir WHERE FIRNo = ?";
        try(Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#");
             PreparedStatement selectFIRStmt=conn.prepareStatement(selectFIRQuery)) 
        {
            selectFIRStmt.setInt(1,firNo);
            ResultSet firResult=selectFIRStmt.executeQuery();

            if(!firResult.next()) 
            {
                JOptionPane.showMessageDialog(this, "No FIR found with the given number.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            String currentCrime=firResult.getString("Crime");
            String currentCriminalID=firResult.getString("CriminalID");
            String currentStatus=firResult.getString("Status");

            String crime=JOptionPane.showInputDialog(this,"Enter new Crime (leave empty to keep current):", currentCrime);
            if(crime==null||crime.trim().isEmpty()) 
            {
                crime=currentCrime;
            }

            String criminalID=JOptionPane.showInputDialog(this,"Enter new Criminal ID (leave empty to keep current):",currentCriminalID);
            if(criminalID==null||criminalID.trim().isEmpty()) 
            {
                criminalID=currentCriminalID;
            }

            String status=JOptionPane.showInputDialog(this,"Enter new Status (leave empty to keep current):",currentStatus);
            if(status==null||status.trim().isEmpty()) 
            {
                status=currentStatus;
            }

            String updateFIRQuery="UPDATE fir SET Crime = ?, CriminalID = ?, Status = ? WHERE FIRNo = ?";
            try (PreparedStatement updateFIRStmt=conn.prepareStatement(updateFIRQuery)) 
            {
                updateFIRStmt.setString(1,crime);
                updateFIRStmt.setString(2,criminalID);
                updateFIRStmt.setString(3,status);
                updateFIRStmt.setInt(4,firNo);
                int rowsUpdated = updateFIRStmt.executeUpdate();

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

            if(!criminalID.equals(currentCriminalID)) 
            {
                String getCriminalAadhaarQuery="SELECT Aadhaar FROM criminal WHERE CriminalNo = ?";
                String criminalAadhaar=null;
                try(PreparedStatement aadhaarStmt=conn.prepareStatement(getCriminalAadhaarQuery)) 
                {
                    aadhaarStmt.setString(1,criminalID);
                    ResultSet aadhaarRs=aadhaarStmt.executeQuery();
                    if(aadhaarRs.next()) 
                    {
                        criminalAadhaar=aadhaarRs.getString("Aadhaar");
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this,"No criminal found with the given ID.", "Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String updateFIRCriminalQuery="INSERT INTO fir_criminal (CriminalNo, FIRNo, CriminalAadhar) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE CriminalAadhar = ?";
                try(PreparedStatement updateFIRCriminalStmt=conn.prepareStatement(updateFIRCriminalQuery)) 
                {
                    updateFIRCriminalStmt.setString(1,criminalID);
                    updateFIRCriminalStmt.setInt(2,firNo);
                    updateFIRCriminalStmt.setString(3,criminalAadhaar);
                    updateFIRCriminalStmt.setString(4,criminalAadhaar);
                    updateFIRCriminalStmt.executeUpdate();
                }
            }

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
            String accessCheckQuery="SELECT * FROM fir_operator WHERE FIRNo = ? AND OperatorUser = ?";
            try(PreparedStatement accessStmt=conn.prepareStatement(accessCheckQuery)) 
            {
                accessStmt.setString(1,firNo);
                accessStmt.setString(2,operatorUser);
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

            firDetails.append("Assigned Operator: ").append(operatorUser).append("\n");

            String criminalDetailsQuery="SELECT CriminalAadhar FROM fir_criminal WHERE FIRNo = ?";
            try(PreparedStatement criminalStmt=conn.prepareStatement(criminalDetailsQuery)) 
            {
                criminalStmt.setString(1,firNo);
                ResultSet criminalRs=criminalStmt.executeQuery();
                firDetails.append("Criminal's Aadhaar Numbers: ");
                while (criminalRs.next()) 
                {
                    String criminalAadhar=criminalRs.getString("CriminalAadhar");
                    firDetails.append(criminalAadhar).append("\n");
                }
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
        
        if(criminalNo==null||aadhaar==null||name==null||gender==null||address==null||
            criminalNo.trim().isEmpty()||aadhaar.trim().isEmpty()||name.trim().isEmpty()|| 
            gender.trim().isEmpty()||address.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"All fields are required.");
            return;
        }

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
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
                operatorCriminalStmt.setString(2,operatorUser);
                operatorCriminalStmt.setString(3,aadhaar);
                operatorCriminalStmt.executeUpdate();
            }

            String selectedAdminUser=Math.random()<0.5?"admin_1":"admin_2";
            
            String criminalAdminInsertQuery="INSERT INTO criminal_admin (CriminalNo, AdminUser, CriminalAadhar) VALUES (?, ?, ?)";
            try(PreparedStatement criminalAdminStmt=conn.prepareStatement(criminalAdminInsertQuery)) 
            {
                criminalAdminStmt.setString(1,criminalNo);
                criminalAdminStmt.setString(2,selectedAdminUser);
                criminalAdminStmt.setString(3,aadhaar);
                criminalAdminStmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(this,"Criminal added successfully and assigned to operator: " 
                + operatorUser + " and admin: " + selectedAdminUser);

        } 
        catch(SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    private void updateCriminalRecord() 
    {
        String criminalId=JOptionPane.showInputDialog(this,"Enter Criminal ID to Update:");

        if(criminalId==null||criminalId.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Criminal ID is required.");
            return;
        }

        try(Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String getAdminUserQuery="SELECT AdminUser FROM criminal_admin WHERE CriminalNo = ?";
            String adminUser=null;

            try(PreparedStatement adminStmt=conn.prepareStatement(getAdminUserQuery)) 
            {
                adminStmt.setString(1,criminalId);
                ResultSet adminRs=adminStmt.executeQuery();
                if(adminRs.next()) 
                {
                    adminUser=adminRs.getString("AdminUser");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"No admin associated with this Criminal ID or Criminal ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

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
                ResultSet rs = accessStmt.executeQuery();
                if(rs.next()) 
                {
                    criminalNo=rs.getString("CriminalNo");
                    aadhaar=rs.getString("Aadhaar");
                    name=rs.getString("Name");
                    alias=rs.getString("Aliase");
                    gender=rs.getString("Gender");
                    address=rs.getString("Address");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"Access denied or Criminal not found.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String newAadhaar=JOptionPane.showInputDialog(this,"Enter New Aadhaar Number (current: " + aadhaar + "):", aadhaar);
            String newName=JOptionPane.showInputDialog(this,"Enter Criminal Name (current: " + name + "):", name);
            String newAlias=JOptionPane.showInputDialog(this,"Enter Alias (if any, current: " + alias + "):", alias);
            String newGender=JOptionPane.showInputDialog(this,"Enter Gender (current: " + gender + "):", gender);
            String newAddress=JOptionPane.showInputDialog(this,"Enter Address (current: " + address + "):", address);

            String updateCriminalQuery="UPDATE criminal SET Aadhaar = ?, Name = ?, Aliase = ?, Gender = ?, Address = ? WHERE CriminalNo = ?";
            try(PreparedStatement updateCriminalStmt = conn.prepareStatement(updateCriminalQuery)) 
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
                try(PreparedStatement updateFIRCriminalStmt=conn.prepareStatement(updateFIRCriminalQuery)) 
                {
                    updateFIRCriminalStmt.setString(1,newAadhaar);
                    updateFIRCriminalStmt.setString(2,aadhaar);
                    updateFIRCriminalStmt.executeUpdate();
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
        String criminalId=JOptionPane.showInputDialog(this,"Enter Criminal ID to retrieve record:");
        if(criminalId==null||criminalId.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"Criminal ID cannot be empty.");
            return;
        }

        try(Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String query="""
                SELECT 
                    c.CriminalNo, 
                    c.Aadhaar, 
                    c.Name, 
                    c.Aliase, 
                    c.Gender, 
                    c.Address,
                    o.OperatorUser,
                    a.AdminUser,
                    COUNT(fc.FIRNo) AS NumberOfCases
                FROM criminal c
                LEFT JOIN operator_criminal o ON c.CriminalNo = o.CriminalNo
                LEFT JOIN criminal_admin a ON c.CriminalNo = a.CriminalNo
                LEFT JOIN fir_criminal fc ON c.CriminalNo = fc.CriminalNo
                WHERE o.OperatorUser = ? AND c.CriminalNo = ?
                GROUP BY c.CriminalNo, c.Aadhaar, c.Name, c.Aliase, c.Gender, c.Address, o.OperatorUser, a.AdminUser;
            """;

            try(PreparedStatement stmt=conn.prepareStatement(query)) 
            {
                stmt.setString(1,operatorUser);
                stmt.setString(2,criminalId);
                ResultSet rs=stmt.executeQuery();

                if(rs.next()) 
                {
                    String criminalNo=rs.getString("CriminalNo");
                    String aadhaar=rs.getString("Aadhaar");
                    String name=rs.getString("Name");
                    String alias=rs.getString("Aliase");
                    String gender=rs.getString("Gender");
                    String address=rs.getString("Address");
                    String operator=rs.getString("OperatorUser");
                    String admin=rs.getString("AdminUser");
                    int numberOfCases=rs.getInt("NumberOfCases");

                    String message=String.format(
                            "Criminal ID: %s\nAadhaar: %s\nName: %s\nAlias: %s\nGender: %s\nAddress: %s\n" +
                            "Operator Handling: %s\nAdmin Handling: %s\nNumber of Cases: %d",
                            criminalNo, aadhaar, name, alias, gender, address, operator, admin, numberOfCases);

                    JOptionPane.showMessageDialog(this,message,"Criminal Record Details",JOptionPane.INFORMATION_MESSAGE);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"No access to the requested criminal record.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage());
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

        try(Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/criminal", "root", "Shiva2212#")) 
        {
            String verifyPasswordQuery="SELECT OperatorPass FROM operator WHERE OperatorUser = ?";
            try(PreparedStatement verifyStmt=conn.prepareStatement(verifyPasswordQuery)) 
            {
                verifyStmt.setString(1,operatorUser);
                ResultSet rs=verifyStmt.executeQuery();

                if(rs.next()) 
                {
                    String storedPassword=rs.getString("OperatorPass");
                    if(!storedPassword.equals(currentPassword)) 
                    {
                        JOptionPane.showMessageDialog(this,"Current password is incorrect.","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this,"Operator Username not found.", "Error",JOptionPane.ERROR_MESSAGE);
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
            try(PreparedStatement updateLoginStmt=conn.prepareStatement(updateLoginQuery)) 
            {
                updateLoginStmt.setString(1,newPassword);
                updateLoginStmt.setString(2,operatorUser);
                updateLoginStmt.executeUpdate();
            }

            String updateOperatorQuery="UPDATE operator SET OperatorPass = ? WHERE OperatorUser = ?";
            try(PreparedStatement updateOperatorStmt=conn.prepareStatement(updateOperatorQuery)) 
            {
                updateOperatorStmt.setString(1,newPassword);
                updateOperatorStmt.setString(2,operatorUser);
                updateOperatorStmt.executeUpdate();
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
