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
public class Astrologue extends Medium implements Serializable {
    
    private String formation;
    private String promotion;
    
     public Astrologue() {
    }
     
    public Astrologue(String formation, String promotion) {
        this.formation = formation;
        this.promotion = promotion;
    }

    public Astrologue(String formation, String promotion, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    public Astrologue(String formation, String promotion, String denomination, String genre) {
        super(denomination, genre);
        this.formation = formation;
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "\nAstrologue :" + "\nformation=" + formation + "\npromotion=" + promotion + super.toString();
    }

   
    
    
    
    
    
}
