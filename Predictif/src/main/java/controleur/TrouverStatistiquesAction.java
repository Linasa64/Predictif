/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class TrouverStatistiquesAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée trouver TrouverStatistiquesAction (action)" + request);
        
        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("idSession");
        
        Service s = new Service();
        
        Employe e = s.trouverEmployeParId(id);
        
        List<Medium> top5 = s.obtenirTopCinqMedium();
        
        Double moyenneH = s.obtenirMoyenneParGenre("H");
        Double moyenneF = s.obtenirMoyenneParGenre("F");
        
        request.setAttribute("top5", top5);
        request.setAttribute("moyenneH", moyenneH);
        request.setAttribute("moyenneF", moyenneF);
       
    return;
    }
}
