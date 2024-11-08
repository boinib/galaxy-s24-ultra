package domain.value;

import nl.hu.inno.hulp.domain.value.DateRange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateRangeTest {

    @ParameterizedTest
    @MethodSource("provideValidDateRanges")
    @DisplayName("Test valid date ranges")
    void testValidDateRange(LocalDate startDate, LocalDate endDate) {
        assertDoesNotThrow(() -> new DateRange(startDate, endDate));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDateRanges")
    @DisplayName("Test invalid date ranges")
    void testInvalidDateRange(LocalDate startDate, LocalDate endDate) {
        assertThrows(IllegalArgumentException.class, () -> new DateRange(startDate, endDate));
    }

    private static Stream<Arguments> provideValidDateRanges() {
        return Stream.of(
                Arguments.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                Arguments.of(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20))
        );
    }

    private static Stream<Arguments> provideInvalidDateRanges() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 31)), // Start date in the past
                Arguments.of(LocalDate.now().plusDays(1), LocalDate.now().minusDays(1)), // End date before start date
                Arguments.of(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1)), // Start date equals end date
                Arguments.of(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)) // Start date in the past
        );
    }
}