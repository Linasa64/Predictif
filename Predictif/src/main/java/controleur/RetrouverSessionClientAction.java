/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class RetrouverSessionClientAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée retrouver session client (action)" + request);

        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("idSession");
        
        Service s = new Service();
        
        request.setAttribute("utilisateur", s.trouverClientParId(id));
        request.setAttribute("type", "client");
        
        return;
    }
}
