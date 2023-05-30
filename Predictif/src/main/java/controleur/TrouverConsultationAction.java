/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class TrouverConsultationAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée trouver consultation (action)" + request);
        
        

        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("idSession");
        
        Service s = new Service();
        
        Employe e = s.trouverEmployeParId(id);
        Consultation c = e.getConsultationEnCours();
        System.out.println(c);
        s.validerConsultation(e);
        
        request.setAttribute("consultation", c);
        request.setAttribute("clientConsultation", c.getClientDeLaConsultation());
        request.setAttribute("mediumConsultation", c.getMediumConsultation());
        
        return;
    }
}
