/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Client;



/**
 *
 * @author hsaysana
 */
public class ClientDao {
    public static void create(Client c){
       JpaUtil.obtenirContextePersistance().persist(c);
    }
    
    public static void delete(Client c){
       JpaUtil.obtenirContextePersistance().remove(c);
    }
    
    public static Client findId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    
    public static List<Client> findAll(){
        String demande = "select c from Client c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Client.class);
        return query.getResultList();
    }
    
    public static Client authMail(String mail, String password){
        String demande = "select c from Client c where c.mail = :mail and c.motDePasse = :password";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Client.class);
        query.setParameter("mail", mail);
        query.setParameter("password", password);
        return (Client)query.getSingleResult();
    }
    
    public static Client authId(Long id, String password){
        String demande = "select c from Client c where c.id = :id and c.motDePasse = :password";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Client.class);
        query.setParameter("id", id);
        query.setParameter("password", password);
        return (Client)query.getSingleResult();
    }
    
    public static Client updateClient(Client c){
        JpaUtil.obtenirContextePersistance().merge(c);
        return c;
    }
}
