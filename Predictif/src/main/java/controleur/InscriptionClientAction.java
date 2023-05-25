/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class InscriptionClientAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée inscription (action)" + request);
        
        System.out.println(request.getParameter("date"));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(request.getParameter("date"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            String mail = request.getParameter("mail");
            String mdp = request.getParameter("mdp");
            System.out.println("Prénom reçu par action : " + prenom);
            Service service = new Service();
            Client client = new Client(nom, prenom, date, adresse, mail, mdp, telephone);
            service.inscrireClient(client);
            System.out.println(client);
            
            request.setAttribute("utilisateur", client);
        } catch (Exception ex) {
            System.out.println("erreur dans l'inscription");
            ex.printStackTrace();
        }
     
        return;
    }
}
