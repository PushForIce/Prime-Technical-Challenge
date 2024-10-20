package console;

import files.FileStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import validators.Validator;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    private final static Logger logger = LogManager.getLogger(Console.class);
    private final Scanner input = new Scanner(System.in);
    private final Validator validator = new Validator();
    private final String EXIT_COMMAND = "exit";

    /**
     * Prompts the user to input a valid username until either a valid username is input or the exit command is called
     * to terminate the application.
     * A username is considered valid if it contains only letters and/or numbers - whitespace and special characters
     * are considered invalid.
     * @return A valid username input by the user
     */
    public String promptForUsername() {
        String username;
        while (true) {
            System.out.println("Enter username or 'exit' to end application: ");
            username = input.nextLine();
            checkForExitCommand(username);
            if (validator.isValidUsername(username)) {
                return username;
            } else {
                System.out.println("Invalid username - should not contain special characters or spaces");
            }
        }
    }

    /**
     * Prompts the user to input a valid numeric sequence until either a valid sequence is input or the exit command is
     * called to terminate the application.
     * A numeric sequence is considered valid if it contains only numbers - whitespace, letters, and special characters
     * are considered invalid.
     * @return A valid numeric sequence input by the user
     */
    public int promptForNumericSequence() {
        String numericSequence;
        while (true) {
            System.out.println("Enter numeric sequence (numbers only with no spaces) or 'exit' to end application: ");
            numericSequence = input.nextLine();
            checkForExitCommand(numericSequence);
            if (validator.isValidNumericSequence(numericSequence)) {
                return Integer.parseInt(numericSequence);
            } else {
                System.out.println("Invalid numeric sequence - should not contain whitespace characters or letters.");
            }
        }
    }

    //checks if a user input was the command to close the application - if it was then close all resources
    //i.e. scanner, writers, before closing the application
    private void checkForExitCommand(String command) {
        if (command.trim().equalsIgnoreCase(EXIT_COMMAND)) {
            logger.info("Received exit command - closing application");
            input.close();
            FileStore.closeWriter();
            System.exit(0);
        }
    }

    public void printNumbersToCheck(ArrayList<Integer> numbersToCheck) {
        System.out.print("Number sequence: ");
        for (int i = 0; i < numbersToCheck.size(); i++) {
            System.out.print(numbersToCheck.get(i) + " ");
        }
    }

    public void printPrimeNumbers(ArrayList<Integer> primeNumbers) {
        System.out.print("\nPrime Numbers in sequence: ");
        for (int i = 0; i < primeNumbers.size(); i++) {
            System.out.print(primeNumbers.get(i) + " ");
        }
        System.out.println();
    }
}
