package nl.hu.inno.hulp.domain.examLocation;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExamLocation {
    @Id
    private long examLocationID;
    @Embedded
    private String location;

    public ExamLocation() {}

    public long getExamLocationID() {return examLocationID;}

    public String getLocation() {return location;}
}
