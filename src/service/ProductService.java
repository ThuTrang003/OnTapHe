/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Product;
import repository.ProductRepository;


public class ProductService {
    private ProductRepository prepo;

    public ProductService() {
        this.prepo = new ProductRepository();
    }
    
    public void insert(Product p) {
        this.prepo.insert(p);
    }
    
    public void update(String product_id, Product p) {
        this.prepo.update(product_id, p);
    }
    
    public void updateQuatity(Integer product_id, Integer quantity, Integer oldquantity) {
        this.prepo.updateQuantity(product_id, quantity, oldquantity);
    }
    
    public void addQuatity(Integer product_id, Integer quantity) {
        this.prepo.addQuantity(product_id, quantity);
    }
    
    public void minusQuatity(Integer product_id, Integer quantity) {
        this.prepo.minusQuantity(product_id, quantity);
    }
    
    public void delete(String product_id) {
        this.prepo.delete(product_id);
    }
    
    public ArrayList<Product> getAll() {
        return this.prepo.getAll();
    }
    
    public Product getByID(String product_id) {
        return this.prepo.getByID(product_id);
    }
    
    public Integer getID(String product_id) {
        return this.prepo.getID(product_id);
    }
    
    public ArrayList<Product> getAllName(String product_name) {
        return this.prepo.getAllName(product_name);
    }
}
