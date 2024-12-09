package nl.hu.inno.hulp.domain.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;


@UserDefinedType("grade")
public final class Grade {

    private Double value;

    public Grade(Double value) {
        if (value < 1 || value > 10) {
            throw new IllegalArgumentException("Grade must be between 1 and 10");
        }
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    protected Grade() {}
}
