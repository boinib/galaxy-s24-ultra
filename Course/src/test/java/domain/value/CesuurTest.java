package domain.value;

import nl.hu.inno.hulp.domain.value.Cesuur;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CesuurTest {

    @ParameterizedTest
    @MethodSource("provideCesuurValues")
    @DisplayName("Test cesuur values")
    void testCesuur(double obtainedPoints, double maxPoints, boolean includeChanceScore, double expectedValue) {
        Cesuur cesuur = new Cesuur(obtainedPoints, maxPoints, includeChanceScore);
        assertEquals(expectedValue, cesuur.getValue(), 0.01);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCesuurValues")
    @DisplayName("Test invalid cesuur values")
    void testInvalidCesuur(double obtainedPoints, double maxPoints, boolean includeChanceScore) {
        assertThrows(IllegalArgumentException.class, () -> new Cesuur(obtainedPoints, maxPoints, includeChanceScore));
    }
    private static Stream<Arguments> provideCesuurValues() {
        return Stream.of(
                // Test zonder kansscore
                Arguments.of(10.0, 30.0, false, 4.0),
                Arguments.of(17.0, 42.0, false, 4.6),
                Arguments.of(25.0, 50.0, false, 5.5),
                Arguments.of(30.0, 60.0, false, 5.5),
                Arguments.of(36.0, 70.0, false, 5.6),
                Arguments.of(5.0, 25.0, false, 2.8),
                Arguments.of(14.0, 34.0, false, 4.7),
                Arguments.of(20.0, 42.0, false, 5.3),
                Arguments.of(50.0, 50.0, false, 10.0),
                Arguments.of(0.0, 10.0, false, 1.0),

                // Test met kansscore
                Arguments.of(12.0, 30.0, true, 3.4),
                Arguments.of(18.0, 42.0, true, 3.6),
                Arguments.of(28.0, 50.0, true, 4.4),
                Arguments.of(55.0, 60.0, true, 8.6),
                Arguments.of(40.0, 70.0, true, 4.5),
                Arguments.of(3.0, 25.0, true, 1.7),
                Arguments.of(16.0, 34.0, true, 3.9),
                Arguments.of(35.0, 42.0, true, 7.1),
                Arguments.of(50.0, 50.0, true, 10.0),
                Arguments.of(0.0, 10.0, true, 1.0)
        );
    }


    private static Stream<Arguments> provideInvalidCesuurValues() {
        return Stream.of(
                Arguments.of(-1.0, 10.0, true),
                Arguments.of(5.0, 0.0, true),
                Arguments.of(5.0, -10.0, false)
        );
    }
}
