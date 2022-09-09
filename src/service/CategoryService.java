/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Category;
import repository.CategoryRepository;

/**
 *
 * @author ADMIN
 */
public class CategoryService {
    private CategoryRepository cRepo;

    public CategoryService() {
        this.cRepo = new CategoryRepository();
    }
    
    public ArrayList<Category> getAll(){
        return this.cRepo.getAll();
    }
}
