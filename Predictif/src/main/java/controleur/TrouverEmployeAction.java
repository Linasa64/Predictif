/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class TrouverEmployeAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée executer TrouverEmploye (action)" + request);
        
        Long id = Long.valueOf(request.getParameter("idEmploye"));
        
        Service service = new Service();

        Employe employe = service.trouverEmployeParId(id);
        
        System.out.println(employe);
       
        if(employe != null){
            request.setAttribute("utilisateur", employe);
        }
        else{
            request.setAttribute("utilisateur", null);
        }
     
        return;
    }
}
