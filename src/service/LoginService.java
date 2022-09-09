/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Login;
import repository.LoginRepository;

/**
 *
 * @author ADMIN
 */
public class LoginService {
    private LoginRepository loginRepo;

    public LoginService() {
        this.loginRepo = new LoginRepository();
    }
    
    public ArrayList<Login> getall(){
        return this.loginRepo.getall();
    } 
    
    public int login(String username, String password){
        ArrayList<Login> lstUsers = loginRepo.getall();
        for (Login x : lstUsers) {
            if (x.getUsetname().equals(username) && x.getPassword().equals(password)) {
                if (x.getRole() == 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return -1; 
    }
}
