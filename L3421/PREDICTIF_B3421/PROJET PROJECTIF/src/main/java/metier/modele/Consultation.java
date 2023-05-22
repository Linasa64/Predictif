/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
/**
 *
 * @author hsaysana
 */
@Entity
public class Consultation implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date date;
    protected String etat; // en attentte / refuser / valider / terminer
    protected String commentaire;
    
    @OneToOne
    private Employe employeEnChargeConsultation;
    
    @OneToOne
    private Client clientDeLaConsultation;
    
    @ManyToOne
    private Medium mediumConsultation;

    public Consultation() {
    }

    public Consultation(Date date) {
        this.date = date;
        this.etat = "en attente";
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public String getEtat() {
        return etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Long getId() {
        return id;
    }

    public Employe getEmployeEnChargeConsultation() {
        return employeEnChargeConsultation;
    }

    public Medium getMediumConsultation() {
        return mediumConsultation;
    }

    public void setEmployeEnChargeConsultation(Employe employeEnChargeConsultation) {
        this.employeEnChargeConsultation = employeEnChargeConsultation;
    }

    public void setMediumConsultation(Medium mediumEnChargeConsultation) {
        this.mediumConsultation = mediumEnChargeConsultation;
    }

    public Client getClientDeLaConsultation() {
        return clientDeLaConsultation;
    }

    public void setClientDeLaConsultation(Client clientDeLaConsultation) {
        this.clientDeLaConsultation = clientDeLaConsultation;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", date=" + date + ", etat=" + etat + ", commentaire=" + commentaire + ", employeEnChargeConsultation=" + employeEnChargeConsultation + ", clientDeLaConsultation=" + clientDeLaConsultation + ", mediumConsultation=" + mediumConsultation + '}';
    }
    
    
    
}
