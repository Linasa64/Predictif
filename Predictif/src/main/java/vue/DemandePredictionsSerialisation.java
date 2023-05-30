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
import metier.modele.Client;
import metier.modele.Employe;
import modele.TestUtilisateur;

/**
 *
 * @author lborg
 */
public class DemandePredictionsSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entrée serialiser demande prédictions (serialisation)" + request + " / "+ response);
        JsonObject container = new JsonObject();

        List<String> predictions = (List<String>)request.getAttribute("predictions");
        System.out.println("Liste prédictionnnns : " + predictions);
        container.addProperty("amour", predictions.get(0));
        container.addProperty("sante", predictions.get(1));
        container.addProperty("travail", predictions.get(2));

        
        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
