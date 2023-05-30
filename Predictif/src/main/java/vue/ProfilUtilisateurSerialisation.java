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
        if(request.getAttribute("utilisateur") instanceof Employe){
            Employe employe = (Employe)request.getAttribute("utilisateur");
            System.out.println("Test employé : " + employe);
            
            if(employe != null){
                container.addProperty("connexion", true);
                container.addProperty("type", (String) request.getAttribute("type"));
                JsonObject containerUser = new JsonObject();
                containerUser.addProperty("id", employe.getId());
                containerUser.addProperty("nom", employe.getNom());
                containerUser.addProperty("prenom", employe.getPrenom());
                containerUser.addProperty("mail", employe.getMail());
                containerUser.addProperty("genre", employe.getGenre());
                containerUser.addProperty("disponible", employe.isDisponible());
                if(!employe.isDisponible()){
                    containerUser.addProperty("denominationConsultationAVenir", employe.getConsultationEnCours().getMediumConsultation().getDenomination());
                }else{
                    containerUser.addProperty("denominationConsultationAVenir", "");
                }

                container.add("utilisateur", containerUser);
            }
        }
        else if(request.getAttribute("utilisateur") instanceof Client){
            Client client = (Client)request.getAttribute("utilisateur");
            System.out.println("Test client : " + client);
            
            if(client != null){
                container.addProperty("connexion", true);
                container.addProperty("type", (String) request.getAttribute("type"));
                System.out.println("Type :"+ (String) request.getAttribute("type"));
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
                

                container.add("utilisateur", containerUser);
            }
        }
        
        System.out.println(container);
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
 
    }
}
