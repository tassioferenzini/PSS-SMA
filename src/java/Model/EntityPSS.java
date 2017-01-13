package Model;

import PROV.DM.Entity;
import javax.persistence.Column;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tassio
 */
@javax.persistence.Entity
@Table(name = "Entity")
public class EntityPSS extends Entity{

    @Column
    private String title;
    @Column
    private int price;

    public EntityPSS() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
