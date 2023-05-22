/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.MediumDao;
import outils.Message;
import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JpaUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Spirite;
import metier.modele.Astrologue;
import metier.modele.Cartomencien;
import metier.modele.Consultation;
import metier.modele.Medium;
import outils.AstroNetApi;

/**
 *
 * @author hsaysana
 */
public class Service {

    public Service() {
    }

    public static void inscrireClient(Client c) {
        System.out.println("Creation du contexte de persistence...");

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();

            AstroNetApi astroApi = new AstroNetApi();

            List<String> profilAstralClient = astroApi.getProfil(c.getPrenom(), c.getDateNaissance());
            c.setSigneZodiaque(profilAstralClient.get(0));
            c.setSigneChinois(profilAstralClient.get(1));
            c.setCouleur(profilAstralClient.get(2));
            c.setAnimal(profilAstralClient.get(3));

            ClientDao.create(c);

            JpaUtil.validerTransaction();
            System.out.println("Inscription réussie!");

            String objet = "Mail de confirmation";
            String corps = "Bonjour, ce mail est pour confirmer votre inscription.";
            Message.envoyerMail("admin@dasi.fr", c.getMail(), objet, corps);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Inscription échouée!");

            String objet = "Mail d'infirmation";
            String corps = "Bonjour, il y a une erreur dans l'inscription";
            Message.envoyerMail("admin@dasi.fr", c.getMail(), objet, corps);
        }

        JpaUtil.fermerContextePersistance();
    }

    public static Client trouverClientParId(Long id) {

        Client c = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            c = ClientDao.findId(id);
            JpaUtil.validerTransaction();
            System.out.println("Client trouvé!");
            JpaUtil.fermerContextePersistance();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Client non trouvé");
            JpaUtil.fermerContextePersistance();

        }

        return c;
    }

    public static Client authentifierClientParMail(String mail, String mdp) {
        Client c = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            c = ClientDao.authMail(mail, mdp);
            JpaUtil.validerTransaction();
            System.out.println("Authentification par mail succès!");
            JpaUtil.fermerContextePersistance();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Authentification par mail échec!");
            JpaUtil.fermerContextePersistance();

        }

        return c;
    }

    public static Employe authentifierEmployeParMail(String mail, String mdp) {
        Employe c = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            c = EmployeDao.authMail(mail, mdp);
            JpaUtil.validerTransaction();
            System.out.println("Authentification par mail succès!");
            JpaUtil.fermerContextePersistance();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Authentification par mail échec!");
            JpaUtil.fermerContextePersistance();

        }

        return c;
    }
    

    public static Client authentifierClientParId(Long id, String mdp) {
        Client c = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            c = ClientDao.authId(id, mdp);
            JpaUtil.validerTransaction();
            System.out.println("Authentification par id succès!");
            JpaUtil.fermerContextePersistance();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Authentification par id échec!");
            JpaUtil.fermerContextePersistance();

        }

        return c;
    }
    
    public static Employe authentifierEmployeParId(Long id, String mdp) {
        Employe e = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            e = EmployeDao.authId(id, mdp);
            JpaUtil.validerTransaction();
            System.out.println("Authentification par id succès!");
            JpaUtil.fermerContextePersistance();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Authentification par id échec!");
            JpaUtil.fermerContextePersistance();

        }

        return e;
    }

    public static void initialiserEmployes() {
        Employe e1 = new Employe("FAVRO", "Samuel", "0642049305", "sfavro@mail", "motDePasse", "H");

        Employe e2 = new Employe("AGA", "Max", "1010101010", "maga@mail", "motDePasse", "H");

        Employe e3 = new Employe("Dupont", "Jeanne", "0632546352", "jdupont@mail", "motDePasse", "F");

        System.out.println("Creation du contexte de persistence...");

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            EmployeDao.create(e1);
            EmployeDao.create(e2);
            EmployeDao.create(e3);
            JpaUtil.validerTransaction();
            System.out.println("Employés bien insérés");
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Employés non insérés");
        }

        JpaUtil.fermerContextePersistance();
    }

    public static Employe trouverEmployeParId(Long id) {

        Employe e = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            e = EmployeDao.findId(id);
            JpaUtil.validerTransaction();
            System.out.println("Employé trouvé!");
            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Employé non trouvé");
            JpaUtil.fermerContextePersistance();

        }

        return e;
    }

    public static List<Employe> listerEmployes() {

        List<Employe> e = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            e = EmployeDao.findAll();
            JpaUtil.validerTransaction();
            System.out.println("Tous les employés trouvés!");
            JpaUtil.fermerContextePersistance();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            JpaUtil.fermerContextePersistance();
        }

        return e;
    }

    public static void initialiserLesMediums() {
        Spirite s1 = new Spirite("Mar de café, boule de cristal, oreille de lapin", "Professeur Tran", "H", "votre avenir est devant vous: regardons-le ensemble");

        Spirite s2 = new Spirite("Boule de cristal", "Gwenaelle", "F", "Specialiste des grandes conversations au-delà de TOUTES les frontières");

        Cartomencien c1 = new Cartomencien("Endora", "F", "Mes cartes répondront à toutes vos questions personnelles.");

        Cartomencien c2 = new Cartomencien("Mme Irma", "F", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");

        Astrologue a1 = new Astrologue("Ecole Normale Supérieur d'Astrologie (ENS-Astro)", "2006", "Serena", "F", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé");

        Astrologue a2 = new Astrologue("Institut des Nouveaux Savoirs Astrologiques", "2010", "Mr M", "H", "Avenir, avenir, que nous réserves-tu? N'attendez plus, demandez à me consulter !");

        System.out.println("Creation du contexte de persistence...");

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            MediumDao.create(s1);
            MediumDao.create(s2);
            MediumDao.create(c1);
            MediumDao.create(c2);
            MediumDao.create(a1);
            MediumDao.create(a2);
            JpaUtil.validerTransaction();
            System.out.println("Mediums ajoutes!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Mediums NON ajoute!");

        }

        JpaUtil.fermerContextePersistance();
    }

    public static List<Medium> obtenirListeDesMediums() {
        List<Medium> listeMedium = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            listeMedium = MediumDao.findAll();
            JpaUtil.validerTransaction();
            System.out.println("Tous les mediums trouvés!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erreur dans la recherche des mediums!");
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();
        return listeMedium;
    }

    public static Medium trouverMediumParDenomination(String denomination) {
        Medium medium = null;
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            medium = MediumDao.findByDenomination(denomination);
            JpaUtil.validerTransaction();
            System.out.println("Tous les mediums trouvés!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erreur dans la recherche des mediums!");
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();
        return medium;
    }

    public static boolean demanderConsultation(Medium medium, Client client) {

        // création de la consultation
        boolean valide = false;
        Consultation consult = null;
        if (client.getConsultationEnCours() == null) {

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                consult = new Consultation(date);
            } catch (Exception ex) {
                System.out.println("erreur");
            }

            String objet = null;
            String corps = null;

            JpaUtil.creerContextePersistance();

            try {
                JpaUtil.ouvrirTransaction();
                Employe e = EmployeDao.getEmployePourConsultation(medium.getGenre());
                ConsultationDao.create(consult);

                consult.setMediumConsultation(medium);
                consult.setClientDeLaConsultation(client);
                medium.addConsultationHistMedium(consult);
                client.setConsultationEnCours(consult);
                client.addConsultationHistClient(consult);

                if (e != null) {
                    e.setConsultationEnCours(consult);
                    consult.setEmployeEnChargeConsultation(e);
                    e.addConsultationHistEmploye(consult);
                    e.setDisponible(false);
                    EmployeDao.updateEmploye(e);
                    objet = "Confirmation de demande de consultation";
                    corps = "Bonjour, votre demande a bien été prise en compte. Vous serez contacté d'ici peu par le medium.";
                    Message.envoyerNotification(e.getTelephonePro(), "Bonjour " + e.getPrenom() + " Consultation requise pour " + client.getPrenom() + " " + client.getNom() + ". Médium à incarder : " + medium.getDenomination());
                    ClientDao.updateClient(client); // on update le client ici, car si la consultation est refusée, le client n'a pas de consultation en cours

                } else {
                    consult.setEtat("refusée");
                    objet = "Echec de demande de consultation";
                    corps = "Bonjour, ce medium n'est pas disponible pour le moment. Veuillez ressayer dans une dizaine de minute.";
                }
                ConsultationDao.updateConsultation(consult);
                MediumDao.updateMedium(medium);

                JpaUtil.validerTransaction();
                valide = true;
                System.out.println("Consultation demandée!");

            } catch (Exception ex) {
                ex.printStackTrace();
                JpaUtil.annulerTransaction();
                System.out.println("Consultation échouée!");
            }

            Message.envoyerMail("contact", client.getMail(), objet, corps);
            JpaUtil.fermerContextePersistance();
        } else {
            System.out.println("Une consultation est déjà en cours!");
        }
        return valide;
    }

    public static List<String> obtenirProfilAstral(Client client) {
        List<String> profilAstral = new ArrayList<String>();
        profilAstral.add(client.getSigneChinois());
        profilAstral.add(client.getSigneZodiaque());
        profilAstral.add(client.getAnimal());
        profilAstral.add(client.getCouleur());
        return profilAstral;
    }

    public static Consultation obternirConsultationCourante(Employe e) {
        Consultation consult = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consult = e.getConsultationEnCours();

            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }

        JpaUtil.fermerContextePersistance();
        return consult;
    }
    
    public static List<Consultation> obternirHistoriqueConsultationEmploye(Employe e) {
        List<Consultation> consult = e.getHistoriqueConsultationsEmploye();
        return consult;
    }
    
    public static List<Consultation> obternirHistoriqueConsultationClient(Client c) {
        List<Consultation> consult = c.getHistoriqueConsultationsClient();
        return consult;
    }

    public static boolean validerConsultation(Employe e) {
        boolean ok = false;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Consultation consult = e.getConsultationEnCours();
            Client c = consult.getClientDeLaConsultation();
            consult.setEtat("validée");

            SimpleDateFormat formatDate = new SimpleDateFormat("dd/mm/yyyy à HH:mm");
            String strDate = formatDate.format(consult.getDate());

            Message.envoyerNotification(c.getTelephone(), "Bonjour " + c.getPrenom() + " j'ai bien reçu votre demande de consultation du " + strDate + ". Vous pouvez dès à présent me contacter au " + e.getTelephonePro() + ". A tout de suite ! Médiumiquement vôtre, " + consult.getMediumConsultation().getDenomination());
            ConsultationDao.updateConsultation(consult);
            ok = true;
            JpaUtil.validerTransaction();
            System.out.println("Validation de la consultation!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }

        JpaUtil.fermerContextePersistance();
        return ok;
    }

    public static boolean terminerConsultation(Employe e, String commentaire) {
        boolean ok = false;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Consultation consult = e.getConsultationEnCours();
            Client c = consult.getClientDeLaConsultation();
            consult.setEtat("terminée");
            consult.setCommentaire(commentaire);
            c.setConsultationEnCours(null);
            e.setConsultationEnCours(null);
            e.setDisponible(true);
            EmployeDao.updateEmploye(e);
            ClientDao.updateClient(c);
            ConsultationDao.updateConsultation(consult);
            ok = true;
            JpaUtil.validerTransaction();
            System.out.println("Consultation terminée!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();
        return ok;
    }

    public static List<String> obtenirAidePredictions(Employe e, int amour, int sante, int travail) {
        List<String> aide = null;
        if (e.getConsultationEnCours() != null) {
            try {
                AstroNetApi astroApi = new AstroNetApi();
                Consultation consult = e.getConsultationEnCours();
                Client c = consult.getClientDeLaConsultation();
                aide = astroApi.getPredictions(c.getCouleur(), c.getAnimal(), amour, sante, travail);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return aide;
    }

    public static List<Consultation> obtenirConsultationsRefusees() {
        List<Consultation> consultationsRefusees = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            consultationsRefusees = ConsultationDao.findConsultationRefused();
            JpaUtil.validerTransaction();
            System.out.println("Consultations refusées trouvées");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Consultations refusées non trouvées");
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();
        return consultationsRefusees;
    }

    public static List<Medium> obtenirTopCinqMedium() {
        List<Medium> topCinqMedium = null;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            topCinqMedium = MediumDao.findTopFiveMedium();
            JpaUtil.validerTransaction();
            System.out.println("Top 5 médium trouvé");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Top 5 médium non trouvé");
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();
        return topCinqMedium;
    }

    public static double obtenirMoyenneParGenre(String g) {
        double moyenne = 0.0;

        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            moyenne = EmployeDao.getAverageConsultationByGender(g);
            JpaUtil.validerTransaction();
            System.out.println("Moyenne trouvée");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Moyenne non trouvée");
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();
        return moyenne;
    }

}
