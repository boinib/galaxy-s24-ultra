package nl.hu.inno.hulp.domain.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public final class Grade {

    @Column(name="cesuur")
    private double value;

    public Grade(double value) {
        if (value < 1 || value > 10) {
            throw new IllegalArgumentException("Grade must be between 1 and 10");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    protected Grade() {}
}
