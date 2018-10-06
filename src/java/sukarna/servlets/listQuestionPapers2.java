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
import sukarna.models.questionPaper;
/**
 *
 * @author Tanveer
 */

@WebServlet(name = "listQuestionPapers2", urlPatterns = {"/listQuestionPapers2"})
public class listQuestionPapers2 extends HttpServlet {

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
            ArrayList<questionPaper> questionPapers = (ArrayList<questionPaper>) session.getAttribute("questionPaperNames");
            String transactionType = request.getParameter("option");
            System.out.println("in listQuestionPapers2----- option : "+transactionType);
            
            DataAccess db=new DataAccess();
            session.setAttribute("questionpaper", db.getQuestionPaperId(transactionType));
                        session.setAttribute("questionpaperid", Integer.parseInt(transactionType));

            session.setAttribute("qpIdforExam",Integer.parseInt(transactionType));
            
            ArrayList<question> questions=db.getQuestions(Integer.parseInt(transactionType));
            for(question trans : questions){
                System.out.println("in listQuestionPapers2----- question ids : "+trans.getQuestionId());}
            
        
            System.out.println("in listQuestionPapers2----- question amount : "+questions.size());

            int a[]=new int[questions.size()];
            int  i=0;
            for(question trans : questions){
                a[i]=trans.getQuestionId();
                i++;
            }
            ArrayList<question> questions1=db.getQuestions(a);
            for(question trans : questions1){
                System.out.println("In listquestionpapers2.java --- questiontype : "+trans.getQuestionType());
            }
            session.setAttribute("questionsOfQuestionPaper", questions1);

            String userType=(String)session.getAttribute("user");
                if(userType.equalsIgnoreCase("teacher")){
                     RequestDispatcher rd = request.getRequestDispatcher("showSelectedQuestionPaper.jsp");
                     rd.forward(request, response);                
                }
                else{
                     RequestDispatcher rd = request.getRequestDispatcher("exam.jsp");
                     rd.forward(request, response);   
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
