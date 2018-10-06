/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sukarna.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sukarna.db.DataAccess;
import sukarna.models.question;

/**
 *
 * @author Tanveer
 */
@WebServlet(name = "questions", urlPatterns = {"/questions"})
public class questions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       
      
       String[] cartQuestions = request.getParameterValues("questions"); 
       int[] cartQuestionsId=new int[2*cartQuestions.length];
       
       for (int i = 0; i < cartQuestions.length; i++) {
            cartQuestionsId[i]=Integer.parseInt(cartQuestions[i]); 
            System.out.println("Here all the carted question Id : "+cartQuestionsId[i]);
            
        }
               HttpSession session = request.getSession();                       
               DataAccess dao = new DataAccess();
               ArrayList<question> questions = dao.getQuestions(cartQuestionsId);
               ArrayList<question> cartedQuestions = (ArrayList<question>) session.getAttribute("cartedQuestions");/////////////////////////////////////////////////////
               for(question trans : questions){/////////////////////////////////////////////////////////////////////////////////
                    cartedQuestions.add(trans);///////////////////////////////////////////////////////////////////////////
               }//////////////////////////////////////////////////////////////////////////////
              
               
               session.setAttribute("cartedQuestions", cartedQuestions);/////////////////////////////////////////
               session.setAttribute("cartQuestions", cartQuestions);
               session.setAttribute("cartQuestionsId", cartQuestionsId);
               
               RequestDispatcher rd = request.getRequestDispatcher("showQuestionPaper.jsp");
               rd.forward(request, response);

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
