/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Inventorylog;
import repository.InventoryLogRepository;

/**
 *
 * @author ADMIN
 */
public class InventoryLogService {
    private InventoryLogRepository inRepo;

    public InventoryLogService() {
        this.inRepo = new InventoryLogRepository();
    }
    
    public void insert(Inventorylog in) {
        this.inRepo.insert(in);
    }
    
    public void update(String formID, Inventorylog in) {
        this.inRepo.update(formID, in);
    }
    
    public void delete(String formID) {
        this.inRepo.delete(formID);
    }
    
    public ArrayList<Inventorylog> getAll() {
        return this.inRepo.getAll();
    }
    
    public Inventorylog getById(String formID) {
        return this.inRepo.getById(formID);
    }
}
