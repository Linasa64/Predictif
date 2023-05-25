/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

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
public class AuthentifierUtilisateurAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Entrée executer (action)" + request);
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        System.out.println("Login reçu par action : " + login + " / Password : " + password);
        
        Service service = new Service();

        Employe employe = service.authentifierEmployeParMail(login, password);
        
        System.out.println(employe);
       
       
        if(employe != null && login.equals(employe.getMail()) && password.equals(employe.getMotDePasse())){
            request.setAttribute("utilisateur", employe);
            HttpSession session = request.getSession(true);
            session.setAttribute("idSession", employe.getId());
            request.setAttribute("type", "employe");
        }
        else{
            Client client = service.authentifierClientParMail(login, password);
            if(client != null && login.equals(client.getMail()) && password.equals(client.getMotDePasse())){
                request.setAttribute("utilisateur", client);
                HttpSession session = request.getSession(true);
                session.setAttribute("idSession", client.getId());
                request.setAttribute("type", "client");
                System.out.println("Type client:" + request.getAttribute("type"));
            }
            else{
                request.setAttribute("utilisateur", null);
            }
        }
     
        return;
    }
}
