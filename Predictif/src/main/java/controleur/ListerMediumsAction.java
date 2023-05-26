/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class ListerMediumsAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée executer ListerMediums (action)" + request);
                
        Service service = new Service();

        List<Medium> listeMediums = service.obtenirListeDesMediums();
        
        System.out.println(listeMediums);
       
        request.setAttribute("listeMediums", listeMediums);
     
        return;
    }
}
