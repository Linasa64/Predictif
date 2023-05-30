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

/**
 *
 * @author lborg
 */
public class ConsultationEnCoursSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entrée serialiser consultation en cours (serialisation)" + request + " / " + response);
        JsonObject container = new JsonObject();

        Medium m = (Medium) request.getAttribute("mediumConsultation");
        JsonObject containerMedium = new JsonObject();
        containerMedium.addProperty("id", m.getId());
        containerMedium.addProperty("type", m.getClass().getSimpleName());
        containerMedium.addProperty("denomination", m.getDenomination());
        containerMedium.addProperty("genre", m.getGenre());
        containerMedium.addProperty("presentation", m.getPresentation());

        Client client = (Client) request.getAttribute("clientConsultation");
        JsonObject containerUser = new JsonObject();
        containerUser.addProperty("id", client.getId());
        containerUser.addProperty("nom", client.getNom());
        containerUser.addProperty("prenom", client.getPrenom());
        containerUser.addProperty("mail", client.getMail());
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(client.getDateNaissance());
        containerUser.addProperty("dateNaissance", strDate);
        containerUser.addProperty("adresse", client.getAdressePostale());
        containerUser.addProperty("telephone", client.getTelephone());
        containerUser.addProperty("couleur", client.getCouleur());
        containerUser.addProperty("animal", client.getAnimal());
        containerUser.addProperty("zodiaque", client.getSigneZodiaque());
        containerUser.addProperty("chinois", client.getSigneChinois());
        
        container.add("client", containerUser);
        container.add("medium", containerMedium);

        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
