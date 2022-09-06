/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviec;

import java.util.ArrayList;
import model.Product;
import repository.ProductRepository;


public class ProductServiec {
    private ProductRepository prepo;

    public ProductServiec() {
        this.prepo = new ProductRepository();
    }
    
    public void insert(Product p) {
        this.prepo.insert(p);
    }
    
    public void update(String product_id, Product p) {
        this.prepo.update(product_id, p);
    }
    public void updateQuatity(int product_id, Product p) {
        this.prepo.updateQuantity(product_id, p);
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
}
