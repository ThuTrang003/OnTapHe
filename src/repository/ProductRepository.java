package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Category;

public class ProductRepository {
    public void insert(Product p) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "insert into product(product_code, product_name, current_price, category_id, quantity) values (?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getProduct_id());
            ps.setString(2, p.getProduct_name());
            ps.setBigDecimal(3, p.getCurrent_price());
            int idCategory = -1;
            if (p.getCategory_id() != null) {
                idCategory = p.getCategory_id().getId();
            }
            ps.setInt(4, idCategory);
            ps.setInt(5, p.getQuantity());
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(String product_id, Product p) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "update product set product_name = ?, current_price = ?, category_id = ?, quantity = ? where product_code = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(5, p.getProduct_id());
            ps.setString(1, p.getProduct_name());
            ps.setBigDecimal(2, p.getCurrent_price());
            int idCategory = -1;
            if (p.getCategory_id() != null) {
                idCategory = p.getCategory_id().getId();
            }
            ps.setInt(3, idCategory);
            ps.setInt(4, p.getQuantity());
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateQuantity(int product_id, Product p) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "update product set quantity = ? where id = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, p.getQuantity());
            ps.setInt(2,product_id);
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(String product_id) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "delete from product where product_code = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, product_id);
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Product> getAll() {
        ArrayList<Product> lst = new ArrayList<>();
        try {
            Connection conn = DBContext.getConnection();
            String query = "select product_code, product_name, current_price,\n" +
                            "p.category_id as id, c.category_name as category_name, quantity \n" +
                            "from product as p left join category as c on p.category_id = c.id";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                String product_code = rs.getString("product_code");
                String product_name = rs.getString("product_name");
                BigDecimal current_price = rs.getBigDecimal("current_price");
                int quantity = rs.getInt("quantity");
                
                int id = rs.getInt("id");
                String category_name = rs.getString("category_name");
                
                Category c= new Category();
                c.setId(id);
                c.setCategory_name(category_name);
                
                
                Product p = new Product(c, product_code, product_name, current_price, quantity);
                lst.add(p);
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Product getByID(String product_id) {
        Product p = new Product();
        try {
            Connection conn = DBContext.getConnection();
            String query = "select product_code, product_name, current_price,\n" +
                            "p.category_id as id, c.category_name as category_name, quantity \n" +
                            "from product as p left join category as c on p.category_id = c.id where product_code like ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, product_id);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                String product_code = rs.getString("product_code");
                String product_name = rs.getString("product_name");
                BigDecimal current_price = rs.getBigDecimal("current_price");
                int quantity = rs.getInt("quantity");
                
                int id = rs.getInt("id");
                String category_name = rs.getString("category_name");
                
                Category c= new Category();
                c.setId(id);
                c.setCategory_name(category_name);
                
                p.setCategory_id(c);
                p.setCurrent_price(current_price);
                p.setProduct_id(product_id);
                p.setProduct_name(product_name);
                p.setQuantity(quantity);
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
