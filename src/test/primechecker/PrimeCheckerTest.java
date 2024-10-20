package primechecker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrimeCheckerTest {
    private final PrimeChecker primeChecker = new PrimeChecker();
    private final static int NUMERIC_SEQUENCE = 12345;

    @BeforeAll
    public void beforeAll() {
        primeChecker.init();
    }

    @Test
    public void testNumbersToCheckAreCalculatedCorrectly() {
        primeChecker.checkForPrimeNumbers(NUMERIC_SEQUENCE);

        List<Integer> expectedNumbersToCheck
                = Arrays.asList(1, 2, 3, 4, 5, 12, 23, 34, 45, 123, 234, 345, 1234, 2345, 12345);
        assertEquals(expectedNumbersToCheck.size(), primeChecker.getNumbersToCheck().size());
        assertEquals(expectedNumbersToCheck, primeChecker.getNumbersToCheck());
    }

    @Test
    public void testPrimeNumbersAreCalculatedCorrectly() {
        primeChecker.checkForPrimeNumbers(NUMERIC_SEQUENCE);

        List<Integer> expectedPrimeNumbers = Arrays.asList(2, 3, 5, 23);
        assertEquals(expectedPrimeNumbers.size(), primeChecker.getPrimeNumbers().size());
        assertEquals(expectedPrimeNumbers, primeChecker.getPrimeNumbers());
    }

    @Test
    public void testPrimeNumberCacheIsPopulated() {
        primeChecker.checkForPrimeNumbers(NUMERIC_SEQUENCE);

        List<Integer> expectedPrimeNumberCache = Arrays.asList(2, 3, 5, 23);
        assertEquals(expectedPrimeNumberCache.size(), primeChecker.getPrimeNumberCache().size());
        assertEquals(expectedPrimeNumberCache, primeChecker.getPrimeNumberCache());

        primeChecker.checkForPrimeNumbers(54321);

        expectedPrimeNumberCache = Arrays.asList(2, 3, 5, 23, 43);
        assertEquals(expectedPrimeNumberCache.size(), primeChecker.getPrimeNumberCache().size());
        assertEquals(expectedPrimeNumberCache, primeChecker.getPrimeNumberCache());
    }
}