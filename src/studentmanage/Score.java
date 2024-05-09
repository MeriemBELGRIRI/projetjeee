/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo T460s
 */
public class Score {
    public void insertUpdateDeleteStudent(char operation, Integer Sid,Integer Cid,String descp) {
        Connection con = Connectionbd.getConnection();
        PreparedStatement ps;
    
        //i for insert
        if (operation == 'i') {
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("INSERT INTO `score`(`student_id`, `course_id`, `student_score`, `description`) VALUES(?,?,?,?)");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
                ps.setInt(1, Sid);
                ps.setInt(2, Cid);
                ps.setString(3, descp);
                
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Nouvelle note a ete ajoutee");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
//         if (operation == 'u') {// u for update
//        
//            try {
//                // Utilisez con.prepareStatement pour préparer la déclaration
//                ps = con.prepareStatement("UPDATE `course` SET `label`= ?,`hours_number`= ? WHERE `id`=?");
//                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
//                ps.setString(1, Label);
//                ps.setInt(2, hours);
//                ps.setInt(3, id);
//         
//              
//                // Exécutez la déclaration
//                if (ps.executeUpdate() > 0) {
//                    JOptionPane.showMessageDialog(null, " Le cours a ete modifiee");
//                    
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//         
//         
//          if (operation == 'd') {// u for delete
//        
//            try {
//                // Utilisez con.prepareStatement pour préparer la déclaration
//                ps = con.prepareStatement("DELETE FROM `score` WHERE `id`=?");
//                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
//              
//                ps.setInt(1, id);
//              
//                // Exécutez la déclaration
//                if (ps.executeUpdate() > 0) {
//                    JOptionPane.showMessageDialog(null, " La note a ete Supprime");
//                    
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
//            } 
//                
//            }
      }  
}
