package metier.modele;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

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
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    
    private String adressePostale;
        
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String telephone;

    private String signeZodiaque;
    private String signeChinois;
    private String couleur;
    private String animal;
    
    @OneToOne
    public Consultation consultationEnCours;
    
    @OneToMany // la supression d'un employé supprimera toutes ses consultations également
    private List<Consultation> historiqueConsultationsClient;
    
    public Client(String nom, String prenom, Date dateNaissance, String adressePostale, String mail, String motDePasse, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adressePostale = adressePostale;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }


    public Client() {
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public Consultation getConsultationEnCours() {
        return consultationEnCours;
    }

    public List<Consultation> getHistoriqueConsultationsClient() {
        return historiqueConsultationsClient;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setConsultationEnCours(Consultation consultationEnCours) {
        this.consultationEnCours = consultationEnCours;
    }

    public void setHistoriqueConsultationsClient(List<Consultation> historiqueConsultationsClient) {
        this.historiqueConsultationsClient = historiqueConsultationsClient;
    }
    
    public boolean addConsultationHistClient (Consultation c){
        return historiqueConsultationsClient.add(c);
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", adressePostale=" + adressePostale + ", mail=" + mail + ", motDePasse=" + motDePasse + ", telephone=" + telephone + '}';
    }
    
    
}
