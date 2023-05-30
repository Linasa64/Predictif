/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class DemanderPredictionsAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée executer demander prédictions (action)" + request);

        Service service = new Service();
        
        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("idSession");

        List<String> predictions = service.obtenirAidePredictions(
                service.trouverEmployeParId(id),
                Integer.parseInt(request.getParameter("amour")),
                Integer.parseInt(request.getParameter("sante")),
                Integer.parseInt(request.getParameter("travail"))
        );

        System.out.println(predictions);

        request.setAttribute("predictions", predictions);

        return;
    }
}
