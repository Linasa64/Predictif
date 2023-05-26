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
import metier.modele.Medium;
import modele.TestUtilisateur;
import java.util.List;
import metier.modele.Astrologue;
import metier.modele.Spirite;

/**
 *
 * @author lborg
 */
public class ListeMediumsSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entrée serialiser liste médiums (serialisation)" + request + " / "+ response);
        JsonObject container = new JsonObject();
        List<Medium> listeMediums = (List<Medium>)request.getAttribute("listeMediums");
        System.out.println("Test liste mediums : " + listeMediums);
        Integer compteur = 0;
        
        for(Medium m : listeMediums){
            compteur++;
            JsonObject containerMedium = new JsonObject();
            containerMedium.addProperty("type", m.getClass().getSimpleName());
            containerMedium.addProperty("denomination", m.getDenomination());
            containerMedium.addProperty("genre", m.getGenre());
            containerMedium.addProperty("presentation", m.getPresentation());
            
            if(m instanceof Spirite){
                Spirite s = (Spirite) m;
                containerMedium.addProperty("support", s.getSupport());
            }
            else if(m instanceof Astrologue){
                Astrologue a = (Astrologue) m;
                containerMedium.addProperty("formation", a.getFormation());
                containerMedium.addProperty("promotion", a.getPromotion());
            }
            
            container.add("medium"+compteur, containerMedium);
        }

        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
