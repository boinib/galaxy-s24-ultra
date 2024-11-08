package nl.hu.inno.hulp.domain.exceptions;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException(String message) {
        super(message);
    }
}
