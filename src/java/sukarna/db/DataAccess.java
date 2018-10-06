/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sukarna.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sukarna.models.question;
import sukarna.models.questionPaper;

/**
 *
 * @author samsung
 */
public class DataAccess 
{
    String dbURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    String username = "johny";
    String password = "1234";

    Connection conn = null;
    public DataAccess()
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if(conn!=null) System.out.println("Connection successfully established.");
            else System.out.println("Could not establish connection");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public List<String> getEmployeeNames()
    {
        List<String> employeeNames = new ArrayList<String>();
        String selectStatement = "select first_name, last_name from employees";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                String name = first + " " + last;
                employeeNames.add(name);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return employeeNames;
    }
    
    public ArrayList<String> getSubjects()
    {
        ArrayList<String> subjectNames = new ArrayList<String>();
        String selectStatement = "select SUBJECT_NAME from SUBJECT";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String subject = rs.getString("SUBJECT_NAME");
                subjectNames.add(subject);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return subjectNames;
    }
    public ArrayList<String> getChapters()
    {
        ArrayList<String> chapterNames = new ArrayList<String>();
        String selectStatement = "select chapter_NAME from chapter";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String chapter = rs.getString("chapter_NAME");
                chapterNames.add(chapter);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return chapterNames;
    }
    
     public ArrayList<String> getChapters(int subjectId)
    {
        ArrayList<String> chapterNames = new ArrayList<String>();
        String selectStatement = "select chapter_NAME from chapter where subject_id = ?";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String chapter = rs.getString("chapter_NAME");
                chapterNames.add(chapter);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return chapterNames;
    }
     
     public ArrayList<questionPaper> getQuestionPapers(int subjectId)
    {
                        System.out.println("In getQuestionPaper(subjectId) ----- subjectId : "+subjectId);

        ArrayList<questionPaper> questionPapers = new ArrayList<questionPaper>();
        String selectStatement = "select question_paper_id,TEACHER_ID,qpname from questionpaper where subject_id = ?";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {   System.out.println("\nIn getQuestionPaper(subjectId) ----- QUESTION_PAPER_ID : "+rs.getInt("question_paper_id"));
                
                questionPaper questionpaper1=new questionPaper();
                questionpaper1.setName(rs.getString("qpname"));
                questionpaper1.setQUESTION_PAPER_ID(rs.getInt("question_paper_id"));
                questionpaper1.setTEACHER_ID(rs.getInt("TEACHER_ID"));
                questionpaper1.setSUBJECT_ID(subjectId);
                questionPapers.add(questionpaper1);
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return questionPapers;
    }
     
    public ArrayList<questionPaper> getExams(int subjectId)
    {
                        System.out.println("In getQuestionPaper(subjectId) ----- subjectId : "+subjectId);

        ArrayList<questionPaper> questionPapers = new ArrayList<questionPaper>();
        String selectStatement = "select question_paper_id,TEACHER_ID,qpname from questionpaper where subject_id = ? and question_paper_id in (select question_paper_id from exam)";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1, subjectId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {   System.out.println("\nIn getQuestionPaper(subjectId) ----- QUESTION_PAPER_ID : "+rs.getInt("question_paper_id"));
                
                questionPaper questionpaper1=new questionPaper();
                questionpaper1.setName(rs.getString("qpname"));
                questionpaper1.setQUESTION_PAPER_ID(rs.getInt("question_paper_id"));
                questionpaper1.setTEACHER_ID(rs.getInt("TEACHER_ID"));
                questionpaper1.setSUBJECT_ID(subjectId);
                questionPapers.add(questionpaper1);
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return questionPapers;
    }
    public ArrayList<question> getQuestions(int questionPaperId)
    {
        ArrayList<question> questions = new ArrayList<question>();
        String selectStatement = "select question_ID from made_of where question_paper_id = ?";
        try
        {    
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1, questionPaperId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                question question1=new question();
                question1.setQuestionId(rs.getInt("question_ID"));
                questions.add(question1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return questions;
    }
    
     
     public ArrayList<question> getQuestions(int chapterId,String questionType)
    {
        ArrayList<question> questions = new ArrayList<question>();
        String selectStatement="";
        System.out.println(chapterId+"  in getQuestion in dataaccess  "+questionType);
        if(questionType.equalsIgnoreCase("mcq")){
                    selectStatement = "select question_id,question_text,marks from question where chapter_Id = ?";
                    try
                    {    
                        PreparedStatement stmt = conn.prepareStatement(selectStatement);
                        stmt.setInt(1, chapterId);
                        ResultSet rs = stmt.executeQuery();
                        while(rs.next())
                        {
                            question question1=new question();
                          
                            String selectStatement1 = "select OPTION_ONE,OPTION_TWO,OPTION_THREE,OPTION_FOUR,ANSWER from mcq where question_Id = ?";
                            try
                            {    
                                PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
                                stmt1.setInt(1, rs.getInt("question_id"));
                                ResultSet rs1 = stmt1.executeQuery();
                                while(rs1.next())
                                    {
                                        question1.setOptionOne(rs1.getString("OPTION_ONE"));
                                        question1.setOptionFour(rs1.getString("OPTION_FOUR"));
                                        question1.setOptionTwo(rs1.getString("OPTION_TWO"));
                                        question1.setOptionThree(rs1.getString("OPTION_THREE"));
                                        question1.setAnnswer(rs1.getString("ANSWER"));
                                        
                                        question1.setQuestionType(questionType);
                                        question1.setQuestionId(rs.getInt("question_Id"));
                                        question1.setQuestionText(rs.getString("question_text"));
                                        question1.setMarks(rs.getInt("marks"));
                                        questions.add(question1);
                                    }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
        }
        else if(questionType.equalsIgnoreCase("fillgaps")){
               
                    selectStatement = "select question_id,question_text,marks from question where chapter_Id = ?";
                    try
                    {    
                        PreparedStatement stmt = conn.prepareStatement(selectStatement);
                        stmt.setInt(1, chapterId);
                        ResultSet rs = stmt.executeQuery();
                        while(rs.next())
                        {
                            question question1=new question();
                          
                            String selectStatement1 = "select ANSWER from fillgaps where question_Id = ?";
                            try
                            {    
                                PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
                                stmt1.setInt(1, rs.getInt("question_id"));
                                ResultSet rs1 = stmt1.executeQuery();
                                while(rs1.next())
                                    {
                                        
                                        question1.setAnnswer(rs1.getString("ANSWER"));
                                        
                                        question1.setQuestionType(questionType);
                                        question1.setQuestionId(rs.getInt("question_Id"));
                                        question1.setQuestionText(rs.getString("question_text"));
                                        question1.setMarks(rs.getInt("marks"));
                                        questions.add(question1);
                                    }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
             
        }
        else if(questionType.equalsIgnoreCase("truefalse")){
                selectStatement = "select question_id,question_text,marks from question where chapter_Id = ?";
                    try
                    {    
                        PreparedStatement stmt = conn.prepareStatement(selectStatement);
                        stmt.setInt(1, chapterId);
                        ResultSet rs = stmt.executeQuery();
                        while(rs.next())
                        {
                            question question1=new question();
                          
                            String selectStatement1 = "select ANSWER from truefalse where question_Id = ?";
                            try
                            {    
                                PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
                                stmt1.setInt(1, rs.getInt("question_id"));
                                ResultSet rs1 = stmt1.executeQuery();
                                while(rs1.next())
                                    {
                                        
                                        question1.setAnnswer(rs1.getString("ANSWER"));
                                        
                                        question1.setQuestionType(questionType);
                                        question1.setQuestionId(rs.getInt("question_Id"));
                                        question1.setQuestionText(rs.getString("question_text"));
                                        question1.setMarks(rs.getInt("marks"));
                                        questions.add(question1);
                                    }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
        }
        return questions;
    }
     
     
     
    public int createTeacherAccount(String firstName, String lastName, String emailid, String username, 
            String password)
    {
        try
        {
            String insertCommand = "insert into teacher values(TEACHER_SEQUENCE.nextval,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, emailid);
            stmt.setString(4, password);
            stmt.setString(5, username);
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }    
    }
    
    public int createStudentAccount(String firstName, String lastName, String emailid, String username, 
            String password)
    {
        try
        {
            String insertCommand = "insert into student values(student_SEQUENCE.nextval,?,?,?,?,?)";//insert into student  values(0,'Kutub','Uddin','kuddin3783@gmail.com','123','kuddin');

            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, emailid);
            stmt.setString(4, password);
            stmt.setString(5, username);
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }    
    }
    
    public int createSubject(String subjectName,String className)
    {
        try
        {
            String insertCommand = "insert into subject values(subject_SEQUENCE.nextval,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            //stmt.setInt(1, subjectId);
            int i=getClassId(className);
            stmt.setInt(1,i);//hardcoded need to change
            stmt.setString(2, subjectName);
            int count = stmt.executeUpdate();//System.out.println(count);
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }    
    }
    
    public int createQuestionPaper(int teacherId,int subjectId,String questionpapername)
    {
        int a;
        try
        {   
            System.out.println("in createQuestionPaper ---- teacherId "+teacherId+"subjectId"+subjectId+"questionpapername :"+questionpapername);
            //String insertCommand = "insert into question values(question_sequence.nextval,?,?)";
            String insertCommand = "insert into questionpaper values(QUESTIONPAPER_SEQUENCE.nextval,?,?,?)";
            
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, teacherId);//changed it in db
            stmt.setInt(2, subjectId);
            stmt.setString(3, questionpapername);

            
            int count = stmt.executeUpdate();
            
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }  
        
    }
    
    public int createMadeOf( int questionId,int questionPaperId)
    {
        try
        {   
            
            //String insertCommand = "insert into question values(question_sequence.nextval,?,?)";
            String insertCommand = "insert into made_of values(MADEOF_SEQUENCE.nextval,?,?)";

            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, questionId);//changed it in db
            stmt.setInt(2, questionPaperId);

            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
    
    public int createFillGaps(int questionID, String answer)
    {
        try
        {
            String insertCommand = "insert into fillgaps values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, questionID);
            stmt.setString(2, answer);
            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
    
     public int createTrueFalse(int questionID, String answer)
    {
        try
        {
            String insertCommand = "insert into truefalse values(?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, questionID);
            stmt.setString(2, answer);
            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
     
     public int createMcq(int questionID,String optionOne,String optionTwo,String optionThree,String optionFour ,String answer)
    {
        try
        {
            String insertCommand = "insert into mcq values(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, questionID);
            stmt.setString(2, optionOne);
            stmt.setString(3, optionTwo);
            stmt.setString(4, optionThree);
            stmt.setString(5, optionFour);
            stmt.setString(6, answer);
            
            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
    
     public int createQuestion(int questionID, String question,int marks,int chapterId)
    {
        try
        {   System.out.println(question+questionID+marks);
            
            //String insertCommand = "insert into question values(question_sequence.nextval,?,?)";
            String insertCommand = "insert into question values(?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(2, question);//changed it in db
            stmt.setInt(1, questionID);//changed it in db
            stmt.setInt(3, marks);
            stmt.setInt(4, chapterId);

            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }  
    }
     
     
     public int createEvaluation(int madeOfId,int studentId,String answer,int marks)
    {
        try
        {   //System.out.println(question+questionID+marks);
            
            //String insertCommand = "insert into question values(question_sequence.nextval,?,?)";
            String insertCommand = "insert into evaluations values(EVALUATIONS_SEQUENCE.nextval,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, madeOfId);
            stmt.setInt(2, studentId);
            stmt.setString(3, answer);
            stmt.setInt(4, marks);

            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }  
    }
     
     public int createExam(int totalMarks,int duration,int questionPaperId,String examName)
    {
        try
        {   
            String insertCommand = "insert into exam values(EXAM_SEQUENCE.nextval,?,sysdate,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setInt(1, totalMarks);
            stmt.setInt(2, duration);
            stmt.setInt(3, questionPaperId);
            stmt.setString(4, examName);

            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }  
    }
     
     
    public int getQuestionPaperId(){
            int id=0;
            try
        {
            String selectStatement = "SELECT QUESTIONPAPER_SEQUENCE.currval Current_Value FROM dual";
            PreparedStatement stmt2 = conn.prepareStatement(selectStatement);
            ResultSet rs = stmt2.executeQuery();
            while(rs.next())
            {
             id = (int)rs.getInt("Current_value");
            }
            }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        } 
            return id;
    
    }
    
    public String getQuestionPaperId(String qpName){
            String name="";
            try
        {
            String selectStatement = "SELECT qpname from questionpaper where QUESTION_PAPER_id = ?";
            PreparedStatement stmt2 = conn.prepareStatement(selectStatement);
            stmt2.setString(1, qpName);
            ResultSet rs = stmt2.executeQuery();
            while(rs.next())
            {
             name = rs.getString("qpname");
            }
            }
        catch(Exception e)
        {
            e.printStackTrace();
            return "null";
        } 
            return name;
    
    }
    
    public int getClassId(String className){
            int id=0;
            try
        {
            String selectStatement = "SELECT class_Id FROM classes where class_name = ?";
            PreparedStatement stmt2 = conn.prepareStatement(selectStatement);
            stmt2.setString(1, className);
            ResultSet rs = stmt2.executeQuery();
            while(rs.next())
            {
             id = rs.getInt("class_Id");
            }
            }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        } 
            return id;
    
    }
     
     public int createChapter( String chapterName,int subjectId)
    {
        try
        {   
            
            //String insertCommand = "insert into question values(question_sequence.nextval,?,?)";
            String insertCommand = "insert into CHAPTER values(subject_SEQUENCE.nextval,?,?)";

            PreparedStatement stmt = conn.prepareStatement(insertCommand);
            stmt.setString(1, chapterName);//changed it in db
            stmt.setInt(2, subjectId);

            
            int count = stmt.executeUpdate();
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        
        
        
    }
    
   
    public boolean existTeacher(String username,String password)
    {
        try
        {
            String query = "select * from teacher where username = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
      public boolean existStudent(String username,String password)
    {
        try
        {
            String query = "select * from student where username = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                return true;
            }
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
        
    /**
     *
     * @return
     */
    public int getQuestionID(){
        try{
        String query1="select * from question";
        PreparedStatement stmt1=conn.prepareStatement(query1);
        
        ResultSet rs1=stmt1.executeQuery();
        int count1=0;
        while(rs1.next())
            ++count1;
        return count1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        }
    public int getSubjectId(String subjectName){
        int subjectId=0;
        try{            //String query = "select * from teacher where username = ? and password = ?";

        String query1="select SUBJECT_ID from subject where SUBJECT_NAME = ?";
        PreparedStatement stmt1=conn.prepareStatement(query1);
        stmt1.setString(1, subjectName);

        ResultSet rs1=stmt1.executeQuery();
        while(rs1.next()){subjectId = rs1.getInt("SUBJECT_ID");}System.out.println("Hahahahaha"+subjectName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return subjectId;
        }
    public int getChapterId(String chapterName,int subjectId){
         int chapterId=0;
        try{            //String query = "select * from teacher where username = ? and password = ?";
           
        String query1="select chapter_ID from chapter where chapter_NAME = ? and SUBJECT_ID = ?";
        PreparedStatement stmt1=conn.prepareStatement(query1);
        stmt1.setString(1, chapterName);
        stmt1.setInt(2, subjectId);

        ResultSet rs1=stmt1.executeQuery();
        while(rs1.next()){chapterId = rs1.getInt("chapter_ID");}System.out.println("Hahahahaha in get chapterId : "+chapterId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return chapterId;
        }
    
    public int getTeacherId(String username){
         int teacherId=0;
        try{            //String query = "select * from teacher where username = ? and password = ?";
           
        String query1="select teacher_ID from teacher where username = ?";
        PreparedStatement stmt1=conn.prepareStatement(query1);
        stmt1.setString(1, username);
        
        ResultSet rs1=stmt1.executeQuery();
        while(rs1.next()){teacherId = rs1.getInt("teacher_ID");}//System.out.println("Hahahahaha in get chapterId : "+chapterId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return teacherId;
        }
    
    public int getStudentId(String username){
         int teacherId=0;
        try{            //String query = "select * from teacher where username = ? and password = ?";
           
        String query1="select student_id from student where username = ?";
        PreparedStatement stmt1=conn.prepareStatement(query1);
        stmt1.setString(1, username);
        
        ResultSet rs1=stmt1.executeQuery();
        while(rs1.next()){teacherId = rs1.getInt("student_id");}//System.out.println("Hahahahaha in get chapterId : "+chapterId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return teacherId;
        }
    public int getMadeofId(int questionId,int questionPaperId){
         int madeOfId=0;
        try{            //String query = "select * from teacher where username = ? and password = ?";
           
        String query1="select made_of_id from made_of where question_id = ? and question_paper_id = ?";
        PreparedStatement stmt1=conn.prepareStatement(query1);
        stmt1.setInt(1, questionId);
        stmt1.setInt(2, questionPaperId);
        
        ResultSet rs1=stmt1.executeQuery();
        while(rs1.next()){
            madeOfId = rs1.getInt("made_of_id");}//System.out.println("Hahahahaha in get chapterId : "+chapterId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return madeOfId;
        }
   
      /* public int getQuestionID(){
        try{
        String query1="SELECT last_number\n" +"  FROM all_sequences\n" +" WHERE sequence_owner = 'johny'\n" +"   AND sequence_name = 'teacher_sequence';";
        PreparedStatement stmt1=conn.prepareStatement(query1);
        
        ResultSet rs1=stmt1.executeQuery();
        int count1= rs1.getInt("last_number");
        
        return count1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        } */
   



    
/*
//Call function sample:
    
    String sql = "{ ? = call FUNCT_PERSON(?,?) }";
    CallableStatement statement = connection.prepareCall(sql);
    statement.setString(2,username);
    statement.setString(3,password);
    statement.registerOutParameter(1, java.sql.Types.INTEGER);  

    statement.execute();   
    //this is the main line
    long id = statement.getLong(1);
    if (id > 0) {
        //proceed to another page
    } else {
        //Go back to the login page
    }*/
    
 /*
    String sql = "{ call PROC_PERSON(?,?,?) }";
    CallableStatement statement = connection.prepareCall(sql);
    statement.setString(2,username);
    statement.setString(3,password);
    statement.registerOutParameter(1, java.sql.Types.INTEGER);  

    statement.execute();   
    //this is the main line
    long id = statement.getLong(1);
    if (id > 0) {
        //proceed to another page
    } else {
        //Go back to the login page
    }*/ 

    
 
    
public ArrayList<question> getQuestions(int[] cartQuestionsId)
    {
        ArrayList<question> questions = new ArrayList<question>();
        int questionId=-999;
        for (int i = 0; i < cartQuestionsId.length; i++) {
                    questionId=cartQuestionsId[i];
                    String selectStatement = "select question_id,question_text,marks from question where question_id = ?";
                    try
                    {    
                        PreparedStatement stmt = conn.prepareStatement(selectStatement);
                        stmt.setInt(1, questionId);
                        ResultSet rs = stmt.executeQuery();
                        while(rs.next())
                        {
                            
                            //if mcq
                            
                          
                            String selectStatement1 = "select OPTION_ONE,OPTION_TWO,OPTION_THREE,OPTION_FOUR,ANSWER from mcq where question_Id = ?";
                            try
                            {    
                                PreparedStatement stmt1 = conn.prepareStatement(selectStatement1);
                                stmt1.setInt(1, questionId);
                                ResultSet rs1 = stmt1.executeQuery();
                                while(rs1.next())
                                    {
                                        question question1=new question();
                                        question1.setOptionOne(rs1.getString("OPTION_ONE"));
                                        question1.setOptionFour(rs1.getString("OPTION_FOUR"));
                                        question1.setOptionTwo(rs1.getString("OPTION_TWO"));
                                        question1.setOptionThree(rs1.getString("OPTION_THREE"));
                                        question1.setAnnswer(rs1.getString("ANSWER"));
                                        
                                        question1.setQuestionType("mcq");
                                        question1.setQuestionId(rs.getInt("question_Id"));
                                        question1.setQuestionText(rs.getString("question_text"));
                                        question1.setMarks(rs.getInt("marks"));
                                        questions.add(question1);
                                    }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                       
                        
                        //if fillgaps
                        
                        
                          
                            String selectStatement2 = "select ANSWER from fillgaps where question_Id = ?";
                            try
                            {    
                                PreparedStatement stmt2 = conn.prepareStatement(selectStatement2);
                                stmt2.setInt(1, questionId);
                                ResultSet rs2 = stmt2.executeQuery();
                                while(rs2.next())
                                    {
                                        question question1=new question();
                                        question1.setAnnswer(rs2.getString("ANSWER"));
                                        
                                        question1.setQuestionType("fillgaps");
                                        question1.setQuestionId(rs.getInt("question_Id"));
                                        question1.setQuestionText(rs.getString("question_text"));
                                        question1.setMarks(rs.getInt("marks"));
                                        questions.add(question1);
                                    }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            
                        //if truefalse
                        
                          
                            String selectStatement3 = "select ANSWER from truefalse where question_Id = ?";
                            try
                            {    
                                PreparedStatement stmt3 = conn.prepareStatement(selectStatement3);
                                stmt3.setInt(1, questionId);
                                ResultSet rs3 = stmt3.executeQuery();
                                while(rs3.next())
                                    {
                                        question question1=new question();
                                        question1.setAnnswer(rs3.getString("ANSWER"));
                                        
                                        question1.setQuestionType("truefalse");
                                        question1.setQuestionId(rs.getInt("question_Id"));
                                        question1.setQuestionText(rs.getString("question_text"));
                                        question1.setMarks(rs.getInt("marks"));
                                        questions.add(question1);
                                    }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        
                    }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    
       
        }
        
        return questions;
    }    
    
    
    
    
}