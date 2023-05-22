/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author hsaysana
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Medium implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    protected String denomination;
    protected String genre;
    protected String presentation;
    
    @OneToMany
    private List<Consultation> HistoriqueConsultationMedium;

    protected Medium() {
    }
   
    
    public Medium(String denomination, String genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }

    public Medium(String denomination, String genre) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = "non specifie";
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getGenre() {
        return genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public Long getId() {
        return id;
    }

    public List<Consultation> getHistoriqueConsultationMedium() {
        return HistoriqueConsultationMedium;
    }

    public boolean addConsultationHistMedium( Consultation c){
        return HistoriqueConsultationMedium.add(c);
    }  
    

    @Override
    public String toString() {
        String message = "\nid=" + id + "\ndenomination=" + denomination + "\ngenre=" + genre + "\npresentation=" + presentation + "\n";
        return message;
    }
    
    
    
    
    
}
