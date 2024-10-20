package main;

import console.Console;
import primechecker.PrimeChecker;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        PrimeChecker primeChecker = new PrimeChecker();
        primeChecker.init();

        String username = console.promptForUsername();
        primeChecker.setUsername(username);

        int numericSequence;
        while (true) {
            numericSequence = console.promptForNumericSequence();
            primeChecker.checkForPrimeNumbers(numericSequence);
            console.printNumbersToCheck(primeChecker.getNumbersToCheck());
            console.printPrimeNumbers(primeChecker.getPrimeNumbers());
        }
    }
}
