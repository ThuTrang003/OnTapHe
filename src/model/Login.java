/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Login {
    private String usetname;
    private String password;
    private int role;

    public Login() {
    }

    public Login(String usetname, String password, int role) {
        this.usetname = usetname;
        this.password = password;
        this.role = role;
    }

    public String getUsetname() {
        return usetname;
    }

    public void setUsetname(String usetname) {
        this.usetname = usetname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    
}
