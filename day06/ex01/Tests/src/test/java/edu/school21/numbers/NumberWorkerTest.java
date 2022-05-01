package edu.school21.numbers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.core.LauncherFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class NumberWorkerTest {

    Launcher launcher = LauncherFactory.create();
    private final NumberWorker numberWorker = new NumberWorker();

    @DisplayName("isPrimeForPrimes")
    @ParameterizedTest
    @ValueSource(ints = {5, 29, 41})
    public void isPrimeForPrimes(int num) {
        assertTrue(numberWorker.isPrime(num));
    }

    @DisplayName("isPrimeForNotPrimes")
    @ParameterizedTest
    @ValueSource(ints = {8, 22, 100})
    public void isPrimeForNotPrimesTest(int num) {
        assertFalse(numberWorker.isPrime(num));
    }

    @DisplayName("isPrimeForIncorrectNumbersTest")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    public void isPrimeForIncorrectNumbersTest(int num) {
        assertThrows(NumberWorker.IllegalNumberException.class, () -> numberWorker.isPrime(num));
    }

    @DisplayName("DigitSumTest")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void digitSumTest(int num, int sum) {
        assertEquals(sum, numberWorker.digitsSum(num));
    }
}
