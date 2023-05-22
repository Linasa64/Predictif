/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author hsaysana
 */

@Entity
public class Spirite extends Medium implements Serializable{
    
    protected String support;

    protected Spirite() {
        super();
    }

    public Spirite(String support, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.support = support;
    }

    public Spirite(String support, String denomination, String genre) {
        super(denomination, genre);
        this.support = support;
    }

    public String getSupport() {
        return support;
    }
    
    public void setSupport(String support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "\nSpirite : " + "\nsupport=" + support + super.toString();
    }
    
    

}
