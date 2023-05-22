package dao;


import metier.modele.Employe;
import dao.JpaUtil;
import java.util.List;
import javax.persistence.TypedQuery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsaysana
 */
public class EmployeDao {
    
    public EmployeDao() {
        
    }
    
    public static void create(Employe e){
       JpaUtil.obtenirContextePersistance().persist(e);
    }
    
    public static void delete(Employe e){
       JpaUtil.obtenirContextePersistance().remove(e);
    }
    
    public static Employe findId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }
    
    public static Employe authMail(String mail, String password){
        String demande = "select c from Employe c where c.mail = :mail and c.motDePasse = :password";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Employe.class);
        query.setParameter("mail", mail);
        query.setParameter("password", password);
        return (Employe)query.getSingleResult();
    }
    
    public static Employe authId(Long id, String password){
        String demande = "select c from Employe c where c.id = :id and c.motDePasse = :password";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Employe.class);
        query.setParameter("id", id);
        query.setParameter("password", password);
        return (Employe)query.getSingleResult();
    }
    
    public static List<Employe> findAll(){
        String demande = "select e from Employe e order by e.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Employe.class);
        return query.getResultList();
    }
    
    public static Employe updateEmploye(Employe e){
        JpaUtil.obtenirContextePersistance().merge(e);
        return e;
    }
    
    public static Employe getEmployePourConsultation(String g){
        String demande = "select e from Employe e where e.disponible =:dispo and e.genre =:genre and SIZE(e.historiqueConsultationsEmploye) = (select MIN(SIZE(e2.historiqueConsultationsEmploye)) from Employe e2 where e2.disponible=:dispo) ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Employe.class);
        query.setParameter("genre", g);
        query.setParameter("dispo",true);
        
        Employe employe = null;
        
        try {
            employe=(Employe) query.getResultList().get(0);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return employe;
        
    }
    
    public static double getAverageConsultationByGender(String g){
        String demande = "select avg(size(e.historiqueConsultationsEmploye)) from Employe e where e.genre=:g ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(demande, Employe.class);
        query.setParameter("g", g);        
        return (double)query.getSingleResult();
    }
}
