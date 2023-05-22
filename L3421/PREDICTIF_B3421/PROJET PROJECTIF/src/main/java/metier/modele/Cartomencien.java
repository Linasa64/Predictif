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
public class Cartomencien extends Medium implements Serializable{

    public Cartomencien() {
    }

    public Cartomencien(String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
    }

    public Cartomencien(String denomination, String genre) {
        super(denomination, genre);
    }

    @Override
    public String toString() {
        return "\nCartomencien : " + super.toString();
    }
    
    
    
    
}
