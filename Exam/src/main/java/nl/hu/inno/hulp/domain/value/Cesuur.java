package nl.hu.inno.hulp.domain.value;

import jakarta.persistence.Column;

import java.util.Objects;

public final class Cesuur {

    @Column(name = "cesuur")
    private double value;

    private static final double MIN_GRADE = 1.0;
    private static final double MAX_GRADE = 10.0;
    private static final double PASSING_GRADE = 5.5;
    private static final double PERCENTAGE_THRESHOLD = 0.65;
    private static final double CHANCE_SCORE_FACTOR = 0.25;

    public Cesuur(double obtainedPoints, double maxPoints, boolean includeChanceScore) {

        if (obtainedPoints > maxPoints) {
            throw new IllegalArgumentException("Obtained points cannot exceed the maximum available points.");
        }

        if (maxPoints <= 0) {
            throw new IllegalArgumentException("Maximum points must be greater than 0.");
        }

        if (obtainedPoints < 0) {
            throw new IllegalArgumentException("Obtained points must be greater than or equal to 0.");
        }

        double cesuur;

        double chanceScore = calculateChanceScore(maxPoints);

        if (includeChanceScore) {
            double remainingPoints = maxPoints - chanceScore;
            cesuur = chanceScore + remainingPoints * PERCENTAGE_THRESHOLD;
        } else {
            cesuur = maxPoints * PERCENTAGE_THRESHOLD;
        }

        double calculatedValue = calculateGrade(obtainedPoints, cesuur, maxPoints, includeChanceScore);

        validateGradeRange(calculatedValue);

        this.value = roundToOneDecimal(calculatedValue);
    }

    private double calculateChanceScore(double maxPoints) {
        return maxPoints * CHANCE_SCORE_FACTOR;
    }

    private double calculateGrade(double obtainedPoints, double cesuur, double maxPoints, boolean includeChanceScore) {
        if (obtainedPoints <= 0) {
            return MIN_GRADE;
        }

        if (includeChanceScore) {
            if (obtainedPoints < cesuur) {
                return calculateLowScoreGrade(obtainedPoints, cesuur);
            } else {
                return calculateHighScoreGrade(obtainedPoints, cesuur, maxPoints);
            }
        } else {
            return (obtainedPoints / maxPoints) * 9 + 1;
        }
    }

    private double calculateLowScoreGrade(double obtainedPoints, double cesuur) {
        return 1 + (obtainedPoints / cesuur) * (PASSING_GRADE - MIN_GRADE);
    }

    private double calculateHighScoreGrade(double obtainedPoints, double cesuur, double maxPoints) {
        return PASSING_GRADE + ((obtainedPoints - cesuur) / (maxPoints - cesuur)) * (MAX_GRADE - PASSING_GRADE);
    }

    private double roundToOneDecimal(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private void validateGradeRange(double grade) {
        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new IllegalArgumentException("Cesuur must be between " + MIN_GRADE + " and " + MAX_GRADE);
        }
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cesuur cesuur = (Cesuur) o;
        return Double.compare(value, cesuur.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    protected Cesuur() {
    }
}
