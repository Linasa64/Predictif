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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Astrologue;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Spirite;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class ListeStatistiquesSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entrée serialiser ListeStatistiquesSerialisation (serialisation)" + request + " / "+ response);
        JsonObject container = new JsonObject();

        
        JsonObject containerMediums = new JsonObject();
        Integer compteur = 0;
        
        List<Medium> top5 = (List<Medium>)request.getAttribute("top5");
        
        for(Medium m : top5){
            compteur++;
            JsonObject containerMedium = new JsonObject();
            containerMedium.addProperty("denomination", m.getDenomination());           
            containerMediums.add("medium"+compteur, containerMedium);
        }
        
        container.add("top5", containerMediums);
        
        container.addProperty("moyenneH", (Double) request.getAttribute("moyenneH"));
        container.addProperty("moyenneF", (Double) request.getAttribute("moyenneF"));

        
        
        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
