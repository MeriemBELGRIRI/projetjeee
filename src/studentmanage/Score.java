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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo T460s
 */
public class Score {
    public void insertUpdateDeleteStudent(char operation, Integer Sid,Integer Cid,Double score,String descp) {
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
                ps.setDouble(3, score);
                ps.setString(4, descp);
                
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Nouvelle note a ete ajoutee");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
         if (operation == 'u') {// u for update
        
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("UPDATE `score` SET `student_score`= ?,`description`= ? WHERE `course_id`=? AND `student_id`=?");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
                ps.setDouble(1, score);
                ps.setString(2, descp);
                ps.setInt(3, Cid);
                ps.setInt(4, Sid);
         
              
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, " Le cours a ete modifiee");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         
         
          if (operation == 'd') {// d for delete
        int YesOrNo = JOptionPane.showConfirmDialog(null,"Les notes liees au cours seront supprimer egalement","Supprimer",JOptionPane.OK_CANCEL_OPTION);
        if(YesOrNo ==0){
            try {
                // Utilisez con.prepareStatement pour préparer la déclaration
                ps = con.prepareStatement("DELETE FROM `score` WHERE `student_id`=? AND `course_id`=?");
                // Assurez-vous d'attribuer les valeurs aux paramètres de la déclaration
                ps.setInt(1, Sid);
                ps.setInt(2, Cid);
                // Exécutez la déclaration
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, " La note a ete Supprime");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            } 
                
            }
          }
      }  
    public void fillScoreJtable(JTable table){
        Connection con = Connectionbd.getConnection();
        PreparedStatement ps;
        try{
            ps=con.prepareStatement("SELECT * FROM `score`");
            ResultSet rs =ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){
            row=new Object[4];
            row[0]=rs.getInt(1);
            row[1]=rs.getInt(2);
            row[2]=rs.getDouble(3);
            row[3]=rs.getString(4);
            model.addRow(row);
            }
        } catch (SQLException ex) {
        Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
}
