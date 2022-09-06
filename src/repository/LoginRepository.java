/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Login;

/**
 *
 * @author ADMIN
 */
public class LoginRepository {
    public ArrayList<Login> getall(){
        ArrayList<Login> lst = new ArrayList<>();
        try {
            Connection conn = DBContext.getConnection();
            String query = "select * from account";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next() == true) {             
                String ten = rs.getString("username");
                String pass = rs.getString("password");
                int role = rs.getInt("role");
                
                Login user = new Login(ten, pass, role);
                lst.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lst;
    }

    
}
