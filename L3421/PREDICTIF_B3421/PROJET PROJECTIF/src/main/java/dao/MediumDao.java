/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Medium;
/**
 *
 * @author hsaysana
 */
public class MediumDao {

    public MediumDao() {
    }
    
    public static void create(Medium m){
       JpaUtil.obtenirContextePersistance().persist(m);
    }
    
    public static List<Medium> findAll(){
        String demande = "select a from Medium a";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Medium.class);
        return query.getResultList();
    }
    
    public static Medium updateMedium(Medium m){
        JpaUtil.obtenirContextePersistance().merge(m);
        return m;
    }
    
    public static Medium findByDenomination (String denomination){
        String demande = "select a from Medium a where a.denomination =:d";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Medium.class);
        query.setParameter("d", denomination);
        return (Medium)query.getSingleResult();
    }

    public static List<Medium> findTopFiveMedium (){
        String demande = "select a from Medium a order by size(a.HistoriqueConsultationMedium) DESC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Medium.class);

        return query.setMaxResults(5).getResultList();
    }
}
