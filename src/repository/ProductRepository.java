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
    
    public void updateQuantity(Integer product_id, Integer quantity, Integer oldquantity) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "update product set quantity -= ? where id = ? \n" + "update product set quantity += ? where id = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, oldquantity);
            ps.setInt(2, product_id);
            ps.setInt(3, quantity);
            ps.setInt(4, product_id);
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addQuantity(Integer product_id, Integer quantity) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "update product set quantity += ? where id = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2,product_id);
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void minusQuantity(Integer product_id, Integer quantity) {
        try {
            Connection conn = DBContext.getConnection();
            String query = "update product set quantity -= ? where id = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, quantity);
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
    
    public Integer getID(String product_id) {
        int id = -1;
        try {
            Connection conn = DBContext.getConnection();
            String query = "select id from product where product_code like ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, product_id);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                id = rs.getInt("id");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public ArrayList<Product> getAllName(String product_name) {
        ArrayList<Product> lst = new ArrayList<>();
        try {
            Connection conn = DBContext.getConnection();
            String query = "select product_code, product_name, current_price,\n" +
                            "p.category_id as id, c.category_name as category_name, quantity \n" +
                            "from product as p left join category as c on p.category_id = c.id where product_name like '%'+N'" + product_name + "' +'%'";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {                
                String product_code = rs.getString("product_code");
                product_name = rs.getString("product_name");
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
}
