package controleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vue.ConsultationEnCoursSerialisation;
import vue.DemandeConsultationSerialisation;
import vue.DemandePredictionsSerialisation;
import vue.InscriptionClientSerialisation;
import vue.ListeMediumsSerialisation;
import vue.ProfilUtilisateurSerialisation;
import vue.ValidationConsultationSerialisation;

 

/**
 *
 * @author lborg
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.creerFabriquePersistance();
    }
    
    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy();
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[TEST] Appel de l’ActionServlet");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ActionServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
//            
//            String pattern = "yyyy-MM-dd HH:mm:ss";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//
//            String date = simpleDateFormat.format(new Date());
//            out.println("<p>" + date + "</p>");

            
            
            String param = request.getParameter("todo");
            System.out.println(param);
            switch (param) {
                case "connecter" :
                    System.out.println("Test1");
                    AuthentifierUtilisateurAction a1 = new AuthentifierUtilisateurAction();
                    a1.executer(request);
                    System.out.println("Test2");
                    ProfilUtilisateurSerialisation s1 = new ProfilUtilisateurSerialisation();
                    System.out.println("Test3");
                    s1.serialiser(request, response);
                    System.out.println("Test4");
                    break;
                    
                case "employeConnecte" :
                    RetrouverSessionEmployeAction a2 = new RetrouverSessionEmployeAction();
                    a2.executer(request);
                    ProfilUtilisateurSerialisation s2 = new ProfilUtilisateurSerialisation();
                    s2.serialiser(request, response);
                    break;
                    
                case "clientConnecte" :
                    System.out.println("Test type client request avant: " + request.getAttribute("type"));
                    RetrouverSessionClientAction a3 = new RetrouverSessionClientAction();
                    a3.executer(request);
                    ProfilUtilisateurSerialisation s3 = new ProfilUtilisateurSerialisation();
                    System.out.println("Test type client request apres: " + request.getAttribute("type"));
                    s3.serialiser(request, response);
                    break;
                    
                case "profilEmploye" :
                    TrouverEmployeAction a4 = new TrouverEmployeAction();
                    a4.executer(request);
                    ProfilUtilisateurSerialisation s4 = new ProfilUtilisateurSerialisation();
                    s4.serialiser(request, response);
                    break;
                    
                case "inscriptionClient" :
                    System.out.println("Bonjour de inscription");
                    InscriptionClientAction a5 = new InscriptionClientAction();
                    a5.executer(request);
                    System.out.println("Fin de inscription");
                    InscriptionClientSerialisation s5 = new InscriptionClientSerialisation();
                    s5.serialiser(request, response);
                    break;
                    
                case "listeMediums" :
                    System.out.println("Test liste médiums");
                    ListerMediumsAction a6 = new ListerMediumsAction();
                    a6.executer(request);
                    ListeMediumsSerialisation s6 = new ListeMediumsSerialisation();
                    s6.serialiser(request, response);
                    break;
                    
                case "demandeConsultation" :
                    System.out.println("Test demande consultation");
                    DemandeConsultationAction a7 = new DemandeConsultationAction();
                    a7.executer(request);
                    DemandeConsultationSerialisation s7 = new DemandeConsultationSerialisation();
                    s7.serialiser(request, response);
                    break;
                case "consultationEnCours" :
                    System.out.println("Test cours consultation");
                    TrouverConsultationAction a8 = new TrouverConsultationAction();
                    a8.executer(request);
                    ConsultationEnCoursSerialisation s8 = new ConsultationEnCoursSerialisation();
                    s8.serialiser(request, response);
                    break;
                case "demandePredictions" :
                    DemanderPredictionsAction a9 = new DemanderPredictionsAction();
                    a9.executer(request);
                    DemandePredictionsSerialisation s9 = new DemandePredictionsSerialisation();
                    s9.serialiser(request, response);
                    break;
                case "validationConsultation" :
                    ValiderConsultationAction a10 = new ValiderConsultationAction();
                    a10.executer(request);
                    ValidationConsultationSerialisation s10 = new ValidationConsultationSerialisation();
                    s10.serialiser(request, response);
                    break;
                default :
                    System.out.println("Paramètre erroné");
                    
            }

//            out.println("</body>");
//            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
