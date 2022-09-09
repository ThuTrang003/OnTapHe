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
import java.util.Date;
import model.Category;
import model.Inventorylog;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class InventoryLogRepository {
    
    public void insert(Inventorylog in) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "insert into inventory_log(form_id, product_id, type_form, created_date, quantity) values (?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, in.getForm_id());
            ps.setInt(2, in.getProduct_id());
            ps.setInt(3, in.getType_form());
            java.sql.Date date = new java.sql.Date(in.getCreated_date().getTime());
            ps.setDate(4, date);
            ps.setInt(5, in.getQuantity());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(String formID, Inventorylog in) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "update inventory_log set product_id = ?, type_form = ?, created_date = ?, quantity = ? where form_id = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(5, formID);
            ps.setInt(1, in.getProduct_id());
            ps.setInt(2, in.getType_form());
            java.sql.Date date = new java.sql.Date(in.getCreated_date().getTime());
            ps.setDate(3, date);
            ps.setInt(4, in.getQuantity());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(String formID) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "delete from inventory_log where form_id = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, formID);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Inventorylog> getAll() {
        ArrayList<Inventorylog> lst = new ArrayList<>();
        try {
            Connection conn = DBContext.getConnection();
            String query = "select form_id, product_id, type_form, created_date, quantity from inventory_log";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                String form_id = rs.getString("form_id");
                int product_id = rs.getInt("product_id");
                Date created_date = rs.getDate("created_date");
                int quantity = rs.getInt("quantity");
                int type_form = rs.getInt("type_form");
                
                Inventorylog in = new Inventorylog(product_id, form_id, type_form, created_date, quantity);
                lst.add(in);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Inventorylog getById(String formID) {
        Inventorylog in = new Inventorylog();
        try {
            Connection conn = DBContext.getConnection();
            String query = "select form_id, product_id, type_form, created_date, quantity from inventory_log where form_id like ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, formID);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                String form_id = rs.getString("form_id");
                int product_id = rs.getInt("product_id");
                Date created_date = rs.getDate("created_date");
                int quantity = rs.getInt("quantity");
                int type_form = rs.getInt("type_form");
                
                in.setCreated_date(created_date);
                in.setForm_id(form_id);
                in.setProduct_id(product_id);
                in.setQuantity(quantity);
                in.setType_form(type_form);
            }
            return in;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
