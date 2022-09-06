/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Inventorylog {
    private int product_id;
    private String form_id;
    private int type_form;
    private Date created_date;
    private int quantity;

    public Inventorylog() {
    }

    public Inventorylog(int product_id, String form_id, int type_form, Date created_date, int quantity) {
        this.product_id = product_id;
        this.form_id = form_id;
        this.type_form = type_form;
        this.created_date = created_date;
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public int getType_form() {
        return type_form;
    }

    public void setType_form(int type_form) {
        this.type_form = type_form;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
