/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Employe;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class ProfilUtilisateurSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entrée serialiser (serialisation)" + request + " / "+ response);
        JsonObject container = new JsonObject();
        Employe employe = (Employe)request.getAttribute("utilisateur");
        System.out.println("Test employé : " + employe);
        
        if(employe != null){
            container.addProperty("connexion", true);
            JsonObject containerUser = new JsonObject();
            containerUser.addProperty("id", employe.getId());
            containerUser.addProperty("nom", employe.getNom());
            containerUser.addProperty("prenom", employe.getPrenom());
            containerUser.addProperty("mail", employe.getMail());

            container.add("utilisateur", containerUser);
        }
        
        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
 
    }
}
