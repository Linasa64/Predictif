/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class DemandeConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée executer demander consultation (action)" + request);

        Service service = new Service();

        Medium mChoisi = service.trouverMediumParDenomination(request.getParameter("denominationMedium"));

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("idSession");
        
        Client client = service.trouverClientParId(id);

        System.out.println(mChoisi);

        boolean reussite = service.demanderConsultation(mChoisi, client);

        request.setAttribute("reussite", reussite);

        return;
    }
}
