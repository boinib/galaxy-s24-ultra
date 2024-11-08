package nl.hu.inno.hulp.domain;

import jakarta.persistence.Embeddable;
@Embeddable
public class TeacherConfirmation {
    private Long teacherID;
    private boolean confirmsExam;

    public TeacherConfirmation(Long teacherID, boolean confirmsExam) {
        this.teacherID = teacherID;
        this.confirmsExam = confirmsExam;
    }

    protected TeacherConfirmation() {

    }

    public Long getTeacherID() {
        return teacherID;
    }

    public boolean isConfirmsExam() {
        return confirmsExam;
    }

    public void setConfirmsExam(boolean confirmsExam) {
        this.confirmsExam = confirmsExam;
    }
}
