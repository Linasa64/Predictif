package vue;

import dao.JpaUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Consultation;
import metier.service.Service;
import outils.Saisie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hsaysana
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialisation
        try {
            JpaUtil.creerFabriquePersistance();
            testerinitialiserLesMediums();
            testerInitialiserEmployes();

            
            //client de test pour tester la validation de consultation d'un employé (jdupont@mail)
            Client c2 = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse("05/12/1990");
            c2 = new Client("PASCAL", "Alice", date, "20 rue de lyon", "alice.pascal@free.fr", "123", "06 88 77 44 55");
            Service.inscrireClient(c2);
            testerDemanderConsultation(c2);
            

        } catch (Exception ex) {

            System.out.println("erreur dans l'initialisation");
            ex.printStackTrace();
        }

        int choix;
        String type = null;
        boolean auth = false;
        Client clientSession = null;
        Employe employeSession = null;

        OUTER:
        OUTER_1:
        OUTER_2:
        OUTER_4:
        while (true) {
            if (!auth) {
                choix = Saisie.lireInteger("1. Inscription\n2. S'authentifier par mail\n3. Quitter");
                switch (choix) {
                    case 1:
                        testerInscrireClient();
                        break;
                    case 2:
                        OUTER_3:
                        while (true) {
                            choix = Saisie.lireInteger("1. Client\n2. Employé");
                            switch (choix) {
                                case 1:
                                    type = "Client";
                                    break OUTER_3;
                                case 2:
                                    type = "Employe";
                                    break OUTER_3;
                                default:
                                    System.out.println("Choix invalide\n");
                                    break;
                            }
                        }
                        String unMail = Saisie.lireChaine("Email: ");
                        String unMdp = Saisie.lireChaine("Mot de passe: ");
                        if ("Client".equals(type)) {
                            clientSession = testerAuthentifierClientParMail(unMail, unMdp);
                            if (clientSession != null) {
                                auth = true;
                                System.out.println("Client authentifié avec succès!");
                            } else {
                                System.out.println("Identifiants incorrects!");
                            }
                        } else if ("Employe".equals(type)) {
                            employeSession = testerAuthentifierEmployeParMail(unMail, unMdp);
                            if (employeSession != null) {
                                auth = true;
                                System.out.println("Employe authentifié avec succès!");
                            } else {
                                System.out.println("Identifiants incorrects!");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Au revoir!");
                        break OUTER_4;
                    default:
                        break;
                }
            } else if (auth && ("Client".equals(type))) {
                choix = Saisie.lireInteger("1. Demander consultation\n2. Consulter mon profil astral\n3. Voir mon historique de consultations\n4. Quitter\n5. Se déconnecter");
                switch (choix) {
                    case 1:
                        testerDemanderConsultation(clientSession);
                        break;
                    case 2:
                        testerListerProfilAstral(clientSession);
                        break;
                    case 3:
                        testerListerHistorique(clientSession);
                        break;
                    case 4:
                        break OUTER_2;
                    case 5:
                        auth = false;
                        clientSession = null;
                        type = null;
                        break;
                    default:
                        System.out.println("Choix invalide!");
                        break;
                }
            } else if (auth && ("Employe".equals(type))) {
                if (!employeSession.isDisponible()) {
                    System.out.println("Bonjour, vous avez une nouvelle demande de consultation : \n");
                    System.out.println(employeSession.getConsultationEnCours());
                    choix = Saisie.lireInteger("1. Consulter son profil astral\n2. Consulter son historique de consultations\n3. Je suis prêt(e)\n4. Voir les statistiques de l'agence\n5. Quitter\n6. Se déconnecter");
                    switch (choix) {
                        case 1:
                            testerListerProfilAstral((employeSession.getConsultationEnCours()).getClientDeLaConsultation());
                            break;
                        case 2:
                            testerListerHistorique((employeSession.getConsultationEnCours()).getClientDeLaConsultation());
                            break;
                        case 3:
                            testerValiderConsultation(employeSession);
                            OUTER_5:
                            while (true) {
                                choix = Saisie.lireInteger("Vous avez validé la consultation. Le client vous contactera bientôt.\n1. Obtenir de l'aide\n2. Terminer la consultation");
                                switch (choix) {
                                    case 1:
                                        testerObtenirAidePredictions(employeSession);
                                        break;
                                    case 2:
                                        testerTerminerConsultation(employeSession);
                                        System.out.println("Consultation terminée !");
                                        break OUTER_5;
                                    default:
                                        System.out.println("Choix invalide !");
                                        break;
                                }
                            }

                            break;

                        case 4:
                            testerVoirStats();
                            break;
                        case 5:
                            break OUTER;
                        case 6:
                            auth = false;
                            employeSession = null;
                            type = null;
                            break;
                        default:
                            System.out.println("Choix invalide!");
                            break;
                    }
                } else {
                    System.out.println("Bonjour, vous n'avez pas de nouvelle demande de consultation.\n");
                    choix = Saisie.lireInteger("1. Voir les statistiques de l'agence \n2. Consulter mon historique de consultation\n3. Quitter\n4. Se déconnecter");
                    switch (choix) {
                        case 1:
                            testerVoirStats();
                            break;
                        case 2:
                            testerObternirHistoriqueConsultationEmploye(employeSession);
                            break;
                        case 3:
                            break OUTER_1;
                        case 4:
                            auth = false;
                            employeSession = null;
                            type = null;
                            break;
                        default:
                            System.out.println("Choix invalide!");
                            break;
                    }
                }
            }
        }

        JpaUtil.fermerFabriquePersistance();
    }

    public static void initialiserClient() {

    }

    public static void testerInitialiserEmployes() {
        Service.initialiserEmployes();
    }

    public static Employe testerTrouverEmployeParId(int id) {
        Long i = Long.valueOf(id);
        Employe e = Service.trouverEmployeParId(i);
        return e;
    }

    public static void testerListerEmployes() {
        List<Employe> liste = Service.listerEmployes();

        for (Employe e : liste) {
            System.out.println(e);
        }
    }

    public static void testerListerProfilAstral(Client client) {
        List<String> profilAstral = Service.obtenirProfilAstral(client);

        for (String s : profilAstral) {
            System.out.println(s);
        }
    }

    public static void testerListerHistorique(Client client) {
        List<Consultation> consult = Service.obternirHistoriqueConsultationClient(client);
        for (Consultation c : consult) {
            System.out.println(c);
        }
    }

    public static void testerObternirHistoriqueConsultationEmploye(Employe e) {
        List<Consultation> consult = Service.obternirHistoriqueConsultationEmploye(e);
        for (Consultation c : consult) {
            System.out.println(c);
        }
    }

    public static void testerInscrireClient() {

        String unNom = Saisie.lireChaine("Veuillez saisir son nom");
        String unPrenom = Saisie.lireChaine("Veuillez saisir son prenom");
        String uneDateNaissance = Saisie.lireChaine("Veuillez saisir sa date de naissance DD/MM/YYYY");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date uneDate = dateFormat.parse(uneDateNaissance);

            String uneAdressePostale = Saisie.lireChaine("Veuillez saisir son adresse postale");
            String unMail = Saisie.lireChaine("Veuillez saisir son mail");
            String unMdp = Saisie.lireChaine("Veuillez saisir son mot de passe");

            String unTelephone = Saisie.lireChaine("Veuillez saisir son numéro de téléphone");

            Client unClient = new Client(unNom, unPrenom, uneDate, uneAdressePostale, unMail, unMdp, unTelephone);
            Service.inscrireClient(unClient);
        } catch (ParseException ex) {
            System.out.println("erreur dans le formatage de la date");
        }
    }

    public static void testerTrouverClientParId(Long id) {
        System.out.println(Service.trouverClientParId(id));
    }

    public static Client testerAuthentifierClientParMail(String mail, String mdp) {
        Client unClient = Service.authentifierClientParMail(mail, mdp);
        return unClient;
    }

    public static Employe testerAuthentifierEmployeParMail(String mail, String mdp) {
        Employe unEmploye = Service.authentifierEmployeParMail(mail, mdp);
        return unEmploye;
    }

    public static void testerAuthentifierClientParId(Long id, String mdp) {
        System.out.println(Service.authentifierClientParId(id, mdp));
    }

    public static void testerinitialiserLesMediums() {
        Service.initialiserLesMediums();
    }

    public static List<Medium> testerObtenirListeDesMediums() {
        List<Medium> liste = Service.obtenirListeDesMediums();
        return liste;
    }

    public static void testerTrouverMediumParDenomination(String denomination) {
        System.out.println(Service.trouverMediumParDenomination(denomination));
    }

    public static void testerDemanderConsultation(Client client) {
        List<Medium> listeMediums = testerObtenirListeDesMediums();
        String denomMedium;
        Medium mediumChoisi;

        for (Medium e : listeMediums) {
            System.out.println(e);
        }
        denomMedium = Saisie.lireChaine("Veuillez saisir la dénomination du medium choisi: ");
        mediumChoisi = Service.trouverMediumParDenomination(denomMedium);
        if (Service.demanderConsultation(mediumChoisi, client)) {
            System.out.println("Consultation demandée!");
        } else {
            System.out.println("Erreur dans la ddemande de consultation!");
        }
    }

    public static void testerValiderConsultation(Employe e) {
        Service.validerConsultation(e);
    }

    public static void testerObtenirAidePredictions(Employe e) {
        int amour, sante, travail;
        amour = Saisie.lireInteger("Veuillez donner une note entre 1 et 4 pour l'amour : ");
        sante = Saisie.lireInteger("Veuillez donner une note entre 1 et 4 pour la santé : ");
        travail = Saisie.lireInteger("Veuillez donner une note entre 1 et 4 pour le travail : ");

        List<String> aide = Service.obtenirAidePredictions(e, amour, sante, travail);
        for (String a : aide) {
            System.out.println(a);
        }
    }

    public static void testerTerminerConsultation(Employe e) {
        String commentaire;
        commentaire = Saisie.lireChaine("Veuillez saisir un commentaire");
        Service.terminerConsultation(e, commentaire);
    }

    //stats
    public static void testerObtenirTopCinqMedium() {
        System.out.println(Service.obtenirTopCinqMedium());
    }

    public static void testerObtenirConsultationsRefusees() {
        System.out.println(Service.obtenirConsultationsRefusees());
    }

    public static void testerObtenirMoyenneParGenre(String g) {
        System.out.println(Service.obtenirMoyenneParGenre(g));
    }

    public static void testerVoirStats() {
        System.out.println("Voici les stats de Predicti'IF:\n");
        System.out.println("Top 5 des mediums : ");
        testerObtenirTopCinqMedium();
        System.out.println("\nLes consultations refusées : ");
        testerObtenirConsultationsRefusees();
        System.out.println("\nMoyenne de consultations par homme : ");
        testerObtenirMoyenneParGenre("H");
        System.out.println("\nMoyenne de consultations par femme : ");
        testerObtenirMoyenneParGenre("F");
    }

    /*public static void testerAssociationEmployeConsultation(){
        Long id= Long.parseLong("4");
        Employe e = Service.trouverEmployeParId(id);
    
        Client c2 = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse("05/29/1950");
            c2 = new Client("PASCAL", "Alice", date, "6 rue 666", "66@gmail.com", "123", "06 88 77 44 55");
            testerInscrireClient(c2);
        } catch (Exception ex) {
            System.out.println("erreur");
        }
        
        testerinitialiserLesMediums();
        testerListerLesMediums();
        

        Consultation consult = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            consult = new Consultation(date);
            testerDemanderConsultation(consult);
        } catch (Exception ex) {
            System.out.println("erreur");
        }
        
        if (e!=null){
            e.setConsultationEnCours(consult);
            consult.setEmployeEnChargeConsultation(e);
            e.addConsultationHistEmploye(consult);
            Service.updateUneConsultation(consult);
            Service.updateUnEmploye(e);
        }
        
        
    }*/
 /*
        int choix;

        while (true) {
            choix = Saisie.lireInteger("1. Inscription\n2. Auth Mail\n3. Auth Id\n6.Quitter");
            if (choix == 1) {
                
                String unNom = Saisie.lireChaine("Veuillez saisir son nom");
                String unPrenom = Saisie.lireChaine("Veuillez saisir son prenom");
                String uneDateNaissance = Saisie.lireChaine("Veuillez saisir sa date de naissance DD/MM/YYYY");
                
                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date uneDate = dateFormat.parse(uneDateNaissance);
                    
                    String uneAdressePostale = Saisie.lireChaine("Veuillez saisir son adresse postale");
                    String unMail = Saisie.lireChaine("Veuillez saisir son mail");
                    String unMdp = Saisie.lireChaine("Veuillez saisir son mot de passe");
                
                    Client unClient = new Client(unNom, unPrenom, uneDate, uneAdressePostale, unMail, unMdp);
                    testerInscriptionClient(unClient);
                }
                
                catch(ParseException ex){
                    System.out.println("erreur dans le formatage de la date");
                }
                 
     */
}
