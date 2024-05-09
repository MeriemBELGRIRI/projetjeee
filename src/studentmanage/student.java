package studentmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class student {
    public void insertUpdateDeleteStudent(char operation, Integer id, String fname, String lname, String sex, String phone, String adress) {
        Connection con = Connectionbd.getConnection();
        PreparedStatement ps = null;
    
        //i for insert
        if (operation == 'i') {
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("INSERT INTO student(first_name, last_name, sex, phone, adress) VALUES(?,?,?,?,?)");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);
                ps.setString(4, phone);
                ps.setString(5, adress);
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Nouveau Eleve a ete ajoutee");
                }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (ps != null) {
                        ps.close(); // Fermez la déclaration
                    }
                    if (con != null) {
                        con.close(); // Fermez la connexion
                    }
                } catch (SQLException e) {
                    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
         if (operation == 'u') {// u for update
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("UPDATE `student` SET `first_name`= ?,`last_name`= ?,`sex`=?,`phone`=?,`adress`=? WHERE `id`=?");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);
                ps.setString(4, phone);
                ps.setString(5, adress);
                ps.setInt(6, id);
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, " L'Eleve a ete modifiee");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (ps != null) {
                        ps.close(); // Fermez la déclaration
                    }
                    if (con != null) {
                        con.close(); // Fermez la connexion
                    }
                } catch (SQLException e) {
                    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
         
         
          if (operation == 'd') {// u for delete
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("DELETE FROM `student` WHERE `id`=?");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
              
                ps.setInt(1, id);
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, " L'Eleve a ete Supprimee");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (ps != null) {
                        ps.close(); // Fermez la déclaration
                    }
                    if (con != null) {
                        con.close(); // Fermez la connexion
                    }
                } catch (SQLException e) {
                    Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
         
        
    }
    
    public void fillStudentJtable(JTable table,String valueToSearch){
         Connection con=Connectionbd.getConnection();
         PreparedStatement ps;
         try{
         ps=con.prepareStatement("SELECT * FROM `student` WHERE CONCAT ('first_name','last_name','phone','address')LIKE ?");
         ps.setString(1,"%"+valueToSearch+"%");
         ResultSet rs=ps.executeQuery();
         DefaultTableModel model=(DefaultTableModel) table.getModel();
         Object[] row;
         
         while(rs.next()){
             row=new Object[6];
         row[0] = rs.getInt(1);
          row[1] = rs.getString(2);
          row[2] = rs.getString(3);
          row[3] = rs.getString(4);
          row[4] = rs.getString(5);
          row[5] = rs.getString(6);
          model.addRow(row);
         }
         
         
         }catch(SQLException ex){
         
         Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
}