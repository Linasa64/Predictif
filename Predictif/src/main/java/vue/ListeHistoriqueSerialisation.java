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
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import modele.TestUtilisateur;
import java.util.List;
import metier.modele.Astrologue;
import metier.modele.Consultation;
import metier.modele.Spirite;

/**
 *
 * @author lborg
 */
public class ListeHistoriqueSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entrée serialiser liste historique (serialisation)" + request + " / " + response);
        JsonObject container = new JsonObject();
        List<Consultation> listeConsultations = (List<Consultation>) request.getAttribute("listeConsultations");
        System.out.println("Test liste historique : " + listeConsultations);
        Integer compteur = 0;
        for (Consultation c : listeConsultations) {
            compteur++;
            JsonObject containerConsultation = new JsonObject();
            containerConsultation.addProperty("id", c.getId());
            containerConsultation.addProperty("medium", c.getMediumConsultation().getDenomination());
            containerConsultation.addProperty("typeMedium", c.getMediumConsultation().getClass().getSimpleName());
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String strDate = formatter.format(c.getDate());
            containerConsultation.addProperty("date", strDate);
            
            containerConsultation.addProperty("commentaire", c.getCommentaire());

            container.add("consultation"+compteur, containerConsultation);
        }

        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
