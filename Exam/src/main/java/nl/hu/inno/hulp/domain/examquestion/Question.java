package nl.hu.inno.hulp.domain.examquestion;

import jakarta.persistence.*;
@Entity(name="questions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="question_type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class Question {

    @Id
    @GeneratedValue()
    private Long id;

    public Long getId() {
        return id;
    }
}
