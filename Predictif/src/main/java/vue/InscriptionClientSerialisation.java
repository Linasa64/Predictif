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
import metier.modele.Client;
import metier.modele.Employe;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class InscriptionClientSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entrée serialiser (serialisation)" + request + " / "+ response);
        JsonObject container = new JsonObject();
        Client client = (Client)request.getAttribute("utilisateur");
        System.out.println("Test client : " + client);
        
        if(client.getId() != null){
            container.addProperty("inscription", true);
        }
        else{
            container.addProperty("inscription", false);
        }
        
        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
