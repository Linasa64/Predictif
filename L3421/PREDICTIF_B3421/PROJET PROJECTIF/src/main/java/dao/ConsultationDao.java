package dao;


import dao.JpaUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Consultation;
import metier.modele.Medium;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsaysana
 */
public class ConsultationDao {

    public ConsultationDao() {
    }
    
    public static void create(Consultation c){
       JpaUtil.obtenirContextePersistance().persist(c);
    }
    
    public static void updateConsultation(Consultation c){
        JpaUtil.obtenirContextePersistance().merge(c);
    }
    
    public static List<Consultation> findConsultationRefused (){
        String demande = "select a from Consultation a where a.employeEnChargeConsultation is null";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Consultation.class);

        return query.getResultList();
    }
    

}
