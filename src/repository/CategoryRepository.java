/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class CategoryRepository {
    public void insert(Category c) {
        
    }
    
    public ArrayList<Category> getAll(){
        ArrayList<Category> lst = new ArrayList<>();
        try {
            Connection conn = DBContext.getConnection();
            String query = "select id, category_name from category";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) { 
                int id = rs.getInt("id");
                String category_name = rs.getString("category_name");
                
                Category c= new Category();
                c.setId(id);
                c.setCategory_name(category_name);
                
                lst.add(c);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
