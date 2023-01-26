package crud;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class CRUD {
    public  static String displayTable(){
        //DQL
        
        String str="";
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siddb","root","");
            String query="select * from student";
            Statement stmt= con.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
                str+="    "+rs.getInt(1)+"    "+rs.getString(2)+"     "+rs.getInt(3)+"      "+rs.getInt(4)+"\n";
                str+="--------------------------------------------------------\n";
            }
            //
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return str;
    }
    public static void insert(String Query){
        //DML
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siddb","root","");
            String query=Query;
            PreparedStatement pstmt= con.prepareStatement(query);
            
            pstmt.executeUpdate();
            System.out.println("Row Inserted");
            //
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    public  static void delete(String sid){
        //DML
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siddb","root","");
            String query="delete from student where sid=?";
            PreparedStatement pstmt= con.prepareStatement(query);
            pstmt.setString(1,sid);            
            pstmt.executeUpdate();
            System.out.println("Row Deleted");
        //
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    public  static void deleteAll(String tablename){
        //DML
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siddb","root","");
            String query="delete from "+tablename;
            PreparedStatement pstmt= con.prepareStatement(query);           
            pstmt.executeUpdate();
            System.out.println("All Rows Deleted");
        //
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    //"UPDATE student SET sid=?,name=?,age=?,spi=? where sid="+condition
    public  static void updateTable(String Query){
        //DML
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siddb","root","");
            String query=Query;
            PreparedStatement pstmt= con.prepareStatement(query);
            
            pstmt.executeUpdate();
            System.out.println(" Row Updated");
        //
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    public  void  gui(){
        JFrame frame=new JFrame("QUERY MAKER");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(500,800);
        frame.setLocationRelativeTo(null);//Centers The Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel select=new JLabel("Select Query");
        select.setBounds(150,50,320,30);
        select.setFont(new Font("Monospace",Font.BOLD,28));
        frame.add(select);
        JButton selectfire=new JButton("FIRE");
        selectfire.setBounds(170,100,120,30);
        frame.add(selectfire);
        selectfire.addActionListener(new Table());
        
        JLabel insert=new JLabel("Insert Query");
        insert.setBounds(10,150,320,35);
        insert.setFont(new Font("Monospace",Font.BOLD,28));
        frame.add(insert);
        JTextField insertfield=new JTextField();
        insertfield.setBounds(10,200,290,30);
        insertfield.setFont(new Font("Monospace",Font.PLAIN,15));
        frame.add(insertfield);
        JButton insertbtn=new JButton("Fire");
        insertbtn.setBounds(320,200,80,30);
        frame.add(insertbtn);
        insertbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String str=insertfield.getText();
                insert(str);
                insertfield.setText("");
            }
        });
        
        JLabel update=new JLabel("Update Query");
        update.setBounds(10,250,320,35);
        update.setFont(new Font("Monospace",Font.BOLD,28));
        frame.add(update);
        JTextField updatefield=new JTextField();
        updatefield.setBounds(10,300,290,30);
        updatefield.setFont(new Font("Monospace",Font.PLAIN,15));
        frame.add(updatefield);
        JButton updatebtn=new JButton("Fire");
        updatebtn.setBounds(320,300,80,30);
        frame.add(updatebtn);
        updatebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String str=updatefield.getText();
                updateTable(str);
                updatefield.setText("");
            }
        });
        
        JLabel delete=new JLabel("Delete Row");
        delete.setBounds(10,350,320,35);
        delete.setFont(new Font("Monospace",Font.BOLD,28));
        frame.add(delete);
        JTextField deletefield=new JTextField();
        deletefield.setBounds(10,400,290,30);
        deletefield.setFont(new Font("Monospace",Font.PLAIN,15));
        frame.add(deletefield);
        JButton deletbtn=new JButton("Fire");
        deletbtn.setBounds(320,400,80,30);
        frame.add(deletbtn);
        deletbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String str=deletefield.getText();
                delete(str);
                deletefield.setText("");
            }
        });
        
        JLabel deleteall=new JLabel("Delete All Rows");
        deleteall.setBounds(140,590,320,30);
        deleteall.setFont(new Font("Monospace",Font.BOLD,28));
        frame.add(deleteall);
        JButton deleteallbtn=new JButton("EXECUTE");
        deleteallbtn.setBounds(180,650,120,30);
        frame.add(deleteallbtn);  
        deleteallbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                deleteAll("student");
                
            }
        });
    }
    class Table implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
        tablegui();
        }
    public static void tablegui(){
        JFrame frame=new JFrame("TABLE STUDENT");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);//Centers The Frame
      
        
        JLabel sid=new JLabel("S_ID");
        sid.setBounds(30,10,60,30);
        sid.setFont(new Font("Monospace",Font.BOLD,20));
        frame.add(sid);
        
        JLabel name=new JLabel("Name");
        name.setBounds(120,10,60,30);
        name.setFont(new Font("Monospace",Font.BOLD,20));
        frame.add(name);
        
        JLabel age=new JLabel("Age");
        age.setBounds(220,10,60,30);
        age.setFont(new Font("Monospace",Font.BOLD,20));
        frame.add(age);
        
        JLabel spi=new JLabel("Spi");
        spi.setBounds(300,10,60,30);
        spi.setFont(new Font("Monospace",Font.BOLD,20));
        frame.add(spi);
        
        JTextArea area=new JTextArea();
        area.setBounds(10,50,365,600);
        area.setFont(new Font("Monospace",Font.BOLD,20));
        frame.add(area);
        area.setEnabled(false);
        String str=displayTable();
        area.setText(str);
        
        
    }
 } 
    public static void main(String[] args) {
        try{            
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siddb","root","");
            CRUD obj=new CRUD();
            obj.gui();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

