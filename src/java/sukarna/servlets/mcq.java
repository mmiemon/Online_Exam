/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sukarna.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sukarna.db.DataAccess;

/**
 *
 * @author Tanveer
 */
@WebServlet(name = "mcq", urlPatterns = {"/mcq"})
public class mcq extends HttpServlet {

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
        String chapter = (String) session.getAttribute("chapter");
        
        String answer=" ";
        String question = request.getParameter("question");
        String marks = request.getParameter("marks");
        String transactionType = request.getParameter("option");
        if(transactionType.equals("optionone")) {
                answer="1";
        }
        else if(transactionType.equals("optiontwo")) {
                answer="2";
        }
        else if(transactionType.equals("optionthree")) {
                answer="3";
        }
        else if(transactionType.equals("optionfour")) {
                answer="4";
        }
        
        String optionone = request.getParameter("optionone");
        String optiontwo = request.getParameter("optiontwo");
        String optionthree = request.getParameter("optionthree");
        String optionfour = request.getParameter("optionfour");


        DataAccess db = new DataAccess();
        int chapterId= db.getChapterId(chapter,db.getSubjectId(subject));
        
        int a=db.getQuestionID();
        db.createQuestion(a, question,Integer.parseInt(marks),chapterId);
        db.createMcq(a,optionone,optiontwo,optionthree,optionfour,answer);
        
        RequestDispatcher rd = request.getRequestDispatcher("mcq.jsp");
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
