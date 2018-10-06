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
@WebServlet(name = "questionPaper2", urlPatterns = {"/questionPaper2"})
public class questionPaper2 extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        
        String subject = (String) session.getAttribute("subject");
        String questionType = (String) session.getAttribute("questionType");
        int subjectId = (int) session.getAttribute("subjectid");

        String chapter = request.getParameter("chapter");  

        System.out.println("hahahahaha in questionPaper2 2\n"); 
        System.out.println("chapter : "+chapter+" subject : "+ subject);
        
        
        //session.setAttribute("chapter", chapter);
        //session.setAttribute("subject", subject);

        DataAccess dao = new DataAccess();
        int chapterId=dao.getChapterId(chapter,subjectId);
        System.out.println("in question paper 2 is chapter id?? "+chapterId);
        ArrayList<question> questions = dao.getQuestions(chapterId,questionType);
        if(questions.size()!=0){for(question trans: questions){
                System.out.println("in question paper 2 "+trans.getQuestionText());}}
        else{System.out.println("in question paper 2 question loading error");}
        
        session.setAttribute("questions", questions);

        RequestDispatcher rd = request.getRequestDispatcher("questions.jsp");
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
