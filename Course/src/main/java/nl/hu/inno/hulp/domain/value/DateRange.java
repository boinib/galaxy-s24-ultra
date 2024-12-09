package nl.hu.inno.hulp.domain.value;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.time.LocalDate;
import java.time.Month;

@UserDefinedType("date_range")
public final class DateRange {
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate startDate;
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate endDate;

    private String period;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date must not be null");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        if (startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("Start date must not be equal to end date");
        }

        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date must be in the future");
        }

        if (endDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("End date must be in the future");
        }

        this.startDate = startDate;
        this.endDate = endDate;

        this.period = determinePeriod(startDate);
    }

    private String determinePeriod(LocalDate startDate) {
        Month month = startDate.getMonth();
        int dayOfMonth = startDate.getDayOfMonth();

        if ((month == Month.SEPTEMBER && dayOfMonth >= 1) || (month == Month.OCTOBER && dayOfMonth <= 31)) {
            return "A";
        } else if ((month == Month.NOVEMBER && dayOfMonth >= 1) || (month == Month.DECEMBER && dayOfMonth <= 31)) {
            return "B";
        } else if ((month == Month.JANUARY && dayOfMonth >= 1) || (month == Month.FEBRUARY && dayOfMonth <= 28) || (month == Month.FEBRUARY && dayOfMonth <= 29)) {
            return "C";
        } else if ((month == Month.MARCH && dayOfMonth >= 1) || (month == Month.APRIL && dayOfMonth <= 30)) {
            return "D";
        } else if ((month == Month.MAY && dayOfMonth >= 1) || (month == Month.AUGUST && dayOfMonth <= 31)) {
            return "E";
        } else {
            throw new IllegalArgumentException("Invalid date range for school period");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DateRange dateRange = (DateRange) obj;
        return startDate.equals(dateRange.startDate) && endDate.equals(dateRange.endDate);
    }

    @Override
    public int hashCode() {
        return startDate.hashCode() + endDate.hashCode();
    }

    protected DateRange() {
    }
}