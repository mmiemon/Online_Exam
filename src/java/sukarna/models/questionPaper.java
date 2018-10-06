/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sukarna.models;

/**
 *
 * @author Tanveer
 */
public class questionPaper {
    private int QUESTION_PAPER_ID;
    private int TEACHER_ID;
    private int SUBJECT_ID;
    private String Name;

   

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getQUESTION_PAPER_ID() {
        return QUESTION_PAPER_ID;
    }

    public void setQUESTION_PAPER_ID(int QUESTION_PAPER_ID) {
        this.QUESTION_PAPER_ID = QUESTION_PAPER_ID;
    }

    public int getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(int TEACHER_ID) {
        this.TEACHER_ID = TEACHER_ID;
    }

    public int getSUBJECT_ID() {
        return SUBJECT_ID;
    }

    public void setSUBJECT_ID(int SUBJECT_ID) {
        this.SUBJECT_ID = SUBJECT_ID;
    }
}
