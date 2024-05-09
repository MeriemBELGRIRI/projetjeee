
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo T460s
 */
public class Course{

public void insertUpdateDeleteStudent(char operation, Integer id, String Label,Integer hours) {
        Connection con = Connectionbd.getConnection();
        PreparedStatement ps;
    
        //i for insert
        if (operation == 'i') {
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("INSERT INTO `course`( `label`, `hours_number`) VALUES (?,?)");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
                ps.setString(1, Label);
                ps.setInt(2, hours);
                
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Nouveau Cours a ete ajoutee");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
         if (operation == 'u') {// u for update
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("UPDATE `course` SET `label`= ?,`hours_number`= ? WHERE `id`=?");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
                ps.setString(1, Label);
                ps.setInt(2, hours);
                ps.setInt(3, id);
               
         
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, " Le cours a ete modifiee");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         
         
          if (operation == 'd') {// u for delete
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("DELETE FROM `course` WHERE `id`=?");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
              
                ps.setInt(1, id);
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, " Le cours a ete Supprime");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            } 
                
            }
        }         
    public boolean isCourseExist(String courseName){
        boolean isExist =false;
        Connection con = Connectionbd.getConnection();
        PreparedStatement ps;
        try{
            ps=con.prepareStatement("SELECT * FROM `course` WHERE `label`=?");
            ps.setString(1,courseName);
            ResultSet rs;
            rs = ps.executeQuery();
            if(rs.next()){
                isExist=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }
    public int getCourseId(String courseLabel){
        int courseId=0;
         Connection con = Connectionbd.getConnection();
        PreparedStatement ps;
        try{
            ps=con.prepareStatement("SELECT * FROM `course` WHERE `label`=?");
            ps.setString(1,courseLabel);
            ResultSet rs;
            rs = ps.executeQuery();
            if(rs.next()){
                courseId=rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseId;
    
}
    public void fillCourseCombo(JComboBox combo){
          Connection con = Connectionbd.getConnection();
         PreparedStatement ps;
         try{
         ps=con.prepareStatement("SELECT * FROM `course`");
         ResultSet rs=ps.executeQuery();
         
         
         while(rs.next()){
            combo.addItem(rs.getString(2));
         }
         }catch(SQLException ex){
         Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
         }}
//    
//      
    
    public void fillCourseJtable(JTable table){
         Connection con=Connectionbd.getConnection();
         PreparedStatement ps;
         try{
         ps=con.prepareStatement("SELECT * FROM course");
        ResultSet rs=ps.executeQuery();
         DefaultTableModel model=(DefaultTableModel) table.getModel();
         Object[] row;
         
         while(rs.next()){
             row=new Object[3];
         row[0] = rs.getInt(1);
          row[1] = rs.getString(2);
          row[2] = rs.getInt(3);
          
          model.addRow(row);
         }
         
         
         }catch(SQLException ex){
         
         Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
    
    
    
    }
