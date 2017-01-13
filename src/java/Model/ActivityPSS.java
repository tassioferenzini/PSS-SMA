/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import PROV.DM.Activity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author tassio
 */
@Entity
@Table(name = "Activity")
public class ActivityPSS extends Activity{

    @Column
    private String description;

    public ActivityPSS() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityPSS(String description, Date startTime, Date endTime, Integer idActivity) {
        super(idActivity);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.description = description;
    }

}
