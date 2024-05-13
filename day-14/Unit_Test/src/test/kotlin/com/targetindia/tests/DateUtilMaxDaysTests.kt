package com.targetindia.tests

import com.targetindia.utils.exceptions.InvalidMonthException
import com.targetindia.utils.exceptions.InvalidYearException
import com.targetindia.utils.maxDays
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class DateUtilMaxDaysTests {

    companion object {
        @JvmStatic
        fun invalidMonthInputs(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(13),
                Arguments.of(0),
                Arguments.of(-45),
                Arguments.of(-19),
                Arguments.of(5888),
            )
        }

        @JvmStatic
        fun invalidYearInputs(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0),
                Arguments.of(-4),
                Arguments.of(-19),
            )
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1992, 1996, 2000, 2004, 2020, 2024])
    @DisplayName("Maximum days for leap year february")
    fun testGetMaxDaysForFebruaryInLeapYear(year: Int) {
        val actual = maxDays(2, year)
        Assertions.assertEquals(29, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [2021, 2022, 2023, 2025, 2026, 2027])
    @DisplayName("Maximum days for non leap year february")
    fun testGetMaxDaysForFebruaryInNonLeapYear(year: Int) {
        val actual = maxDays(2, year)
        Assertions.assertEquals(28, actual)
    }

    @ParameterizedTest
    @CsvFileSource(files = ["test-inputs.csv"])
    @DisplayName("Maximum days for shorter months")
    fun testGetMaxDaysForShorterMonths(month: Int, year: Int) {
        val actual = maxDays(month, year)
        Assertions.assertEquals(30, actual)
    }

    @ParameterizedTest
    @CsvSource(value = ["1, 2023", "1, 2024", "5, 1999", "3, 2000", "8, 1990"])
    @DisplayName("Maximum days for longer months")
    fun testGetMaxDaysForLongerMonths(month: Int, year: Int) {
        val actual = maxDays(month, year)
        Assertions.assertEquals(31, actual)
    }

    @ParameterizedTest
    @MethodSource(value = ["invalidMonthInputs"])
    @DisplayName("Invalid month supplied")
    fun testInvalidMonth(month: Int) {
        try {
            maxDays(month, 1234)
            Assertions.fail("Expected Invalid month exception but didn't get one.");
        } catch (e : InvalidMonthException) {

        }
    }

    @ParameterizedTest
    @MethodSource(value = ["invalidYearInputs"])
    @DisplayName("Invalid year supplied")
    fun testInvalidYear(year: Int) {
        try {
            maxDays(5, year)
            Assertions.fail("Expected Invalid year exception but didn't get one.");
        } catch (e : InvalidYearException) {

        }
    }
}