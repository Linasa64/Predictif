package metier.modele;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsaysana
 */
@Entity
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    private String telephonePro;
    private String mail;
    private String motDePasse;
    private String genre;
    private boolean disponible;
    
    @OneToOne
    private Consultation consultationEnCours;
    
    @OneToMany
    private List<Consultation> historiqueConsultationsEmploye;
   
    public Employe() {
    }

    public Employe(String nom, String prenom, String telephonePro, String login, String motDePasse, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephonePro = telephonePro;
        this.mail = login;
        this.motDePasse = motDePasse;
        this.genre = genre;
        disponible=true;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephonePro() {
        return telephonePro;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephonePro(String telephonePro) {
        this.telephonePro = telephonePro;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public boolean addConsultationHistEmploye( Consultation c){
        return historiqueConsultationsEmploye.add(c);
    }

    public void setConsultationEnCours(Consultation consultationEnCours) {
        this.consultationEnCours = consultationEnCours;
    }

    public Consultation getConsultationEnCours() {
        return consultationEnCours;
    }

    public void setHistoriqueConsultationsEmploye(List<Consultation> historiqueConsultationsEmploye) {
        this.historiqueConsultationsEmploye = historiqueConsultationsEmploye;
    }

    public List<Consultation> getHistoriqueConsultationsEmploye() {
        return historiqueConsultationsEmploye;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephonePro=" + telephonePro + ", login=" + mail + ", motDePasse=" + motDePasse + "genre="+genre+'}';
    }
   
    
    
    
}
