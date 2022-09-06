/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviec;

import java.util.ArrayList;
import model.Category;
import repository.CategoryRepository;

/**
 *
 * @author ADMIN
 */
public class CategoryServiec {
    private CategoryRepository cRepo;

    public CategoryServiec() {
        this.cRepo = new CategoryRepository();
    }
    
    public ArrayList<Category> getAll(){
        return this.cRepo.getAll();
    }
}
