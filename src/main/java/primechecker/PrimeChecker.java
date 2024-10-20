package primechecker;

import files.FileStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Collections;

public class PrimeChecker {
    private final static Logger logger = LogManager.getLogger(PrimeChecker.class);
    private String username;
    private final FileStore fileStore = new FileStore();
    private final ArrayList<Integer> numbersToCheck = new ArrayList<>();
    private final ArrayList<Integer> primeNumbers = new ArrayList<>();
    private final ArrayList<Integer> primeNumberCache = new ArrayList<>();

    public void init() {
        fileStore.createFile();
    }

    public void checkForPrimeNumbers(int numericSequence) {
        numbersToCheck.clear();
        primeNumbers.clear();
        generateNumbersToCheck(numericSequence);
        findPrimeNumbers();
    }

    private void generateNumbersToCheck(int numericSequence) {
        logger.debug("Generating numbers to check from sequence {} submitted by user {}", numericSequence, username);
        splitNumericSequence(numericSequence);
        generateNumbersFromSplitSequence();
        Collections.sort(numbersToCheck);
    }

    private void splitNumericSequence(int numericSequence) {
        if (numericSequence / 10 > 0) {
            splitNumericSequence(numericSequence / 10);
        }
        numbersToCheck.add(numericSequence % 10);
        logger.debug("Added {} to numberToCheck", numericSequence % 10);
    }

    private void generateNumbersFromSplitSequence() {
        int initialListSize = numbersToCheck.size();
        
        if (initialListSize < 2) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < initialListSize - 1; i++) {
            sb.append(numbersToCheck.get(i));
            for (int j = i + 1; j < initialListSize; j++) {
                sb.append(numbersToCheck.get(j));
                numbersToCheck.add(Integer.parseInt(sb.toString()));
                logger.debug("Added {} to numbersToCheck", sb.toString());
            }
            sb.setLength(0);
        }
    }

    private void findPrimeNumbers() {
        int valueToCheck;
        for (int i = 0; i < numbersToCheck.size(); i++) {
            valueToCheck = numbersToCheck.get(i);
            if (!primeNumberCache.isEmpty()) {
                //cache is populated, use it to attempt to disprove primality
                if (disprovePrimalityByTrialDivision(valueToCheck)) {
                    continue;
                }
            }
            if (isPrime(valueToCheck)) {
                storePrimeNumber(valueToCheck);
            }
        }
    }

    // Divides valueToCheck by prime numbers stored in cache - if it divides by any prime number between 2 and
    // the square root of valueToCheck then it is not a prime number.
    private boolean disprovePrimalityByTrialDivision(int valueToCheck) {
        int squareRoot = (int) Math.sqrt(valueToCheck);
        for (int i = 0; i < primeNumberCache.size(); i++) {
            if (primeNumberCache.get(i) > squareRoot) {
                break;
            }
            if (valueToCheck % primeNumberCache.get(i) == 0) {
                //is divisible by a prime number in cache - valueToCheck is not prime
                logger.debug("{} is not a prime number - disproved by trial division", valueToCheck);
                return true;
            }
        }
        //the primality of valueToCheck was not disproved by prime numbers stored in cache however it may still be prime
        return false;
    }

    private boolean isPrime(int valueToCheck) {
        //if valueToCheck is less than 2, or is an even number greater than 2, then it is not prime
        if (valueToCheck < 2 || (valueToCheck > 2 && valueToCheck % 2 == 0)) {
            return false;
        }
        for (int i = 2; i < (valueToCheck / 2); i++) {
            if (valueToCheck % i == 0) {
                //value is divisible by number other than 1 and itself - not prime
                logger.debug("{} is divisible by {} - not a prime number", valueToCheck, i);
                return false;
            }
        }
        logger.debug("{} is a prime number", valueToCheck);
        return true;
    }

    private void storePrimeNumber(int primeNumber) {
        primeNumbers.add(primeNumber);
        storePrimeNumberInCache(primeNumber);
        writePrimeNumberToFile(primeNumber);
    }

    private void storePrimeNumberInCache(int primeNumber) {
        if (primeNumberCache.isEmpty()) {
            logger.debug("Adding {} to empty cache", primeNumber);
            primeNumberCache.add(primeNumber);
        } else {
            //store prime number in cache in ascending order
            int index = Collections.binarySearch(primeNumberCache, primeNumber);
            if (index < 0) {
                index = -index - 1;
            } else {
                //prime number is already in cache - do not add it
                logger.debug("{} already exists in cache - not added", primeNumber);
                return;
            }
            logger.debug("Adding {} to cache at index {}", primeNumber, index);
            primeNumberCache.add(index, primeNumber);
        }
    }

    private void writePrimeNumberToFile(int primeNumber) {
        fileStore.writeToFile(primeNumber);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Integer> getNumbersToCheck() {
        return numbersToCheck;
    }

    public ArrayList<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

    public ArrayList<Integer> getPrimeNumberCache() {
        return primeNumberCache;
    }
}
