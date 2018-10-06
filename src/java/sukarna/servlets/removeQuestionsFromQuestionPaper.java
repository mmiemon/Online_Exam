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
@WebServlet(name = "removeQuestionsFromQuestionPaper", urlPatterns = {"/removeQuestionsFromQuestionPaper"})
public class removeQuestionsFromQuestionPaper extends HttpServlet {

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
        
        
       String[] deleteQuestions = request.getParameterValues("r"); 
       int[] deleteQuestionsId=new int[deleteQuestions.length];
       for(int i=0;i<deleteQuestions.length;i++){
           deleteQuestionsId[i]=Integer.parseInt(deleteQuestions[i]);
       }
      
               HttpSession session = request.getSession();  
               ArrayList<question> cartedQuestions = (ArrayList<question>) session.getAttribute("cartedQuestions");/////////////////////////////////////////////////////
               /*for(int j = 0; j < cartedQuestions.size(); j++)
                    for(int i=0;i<deleteQuestions.length;i++){
                        question obj = cartedQuestions.get(j);
                         if(obj.getQuestionId()==deleteQuestionsId[i]){
                            //found, delete.
                            cartedQuestions.remove(j);
                            System.out.println("koko"+deleteQuestionsId[i]);
                            break;
                    }

                }*/
                //for(question trans: cartedQuestions){
                        //            System.out.println("in removequestionsfromquestionpaper ----- quesions : "+trans.getQuestionText());
                          //  }
                 
               session.setAttribute("deleteQuestionsId", deleteQuestionsId);/////////////////////////////////////////
               session.setAttribute("removeDeleted", "yes");
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
