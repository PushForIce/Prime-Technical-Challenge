package validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
    private final static Logger logger = LogManager.getLogger(Validator.class);

    /**
     * Verifies that a username is valid - a username is considered valid if it contains only letters and/or numbers
     * with no whitespace or special characters
     * @param username The username to validate
     * @return True if the username is valid, otherwise false
     */
    public boolean isValidUsername(String username) {
        boolean isValid = username.matches("^[a-zA-Z0-9]+$");

        logger.debug("Username {} valid - {}", username, isValid);
        return isValid;
    }

    /**
     * Verifies a numeric sequence is valid - a numeric sequence is considered valid if it contains only numbers and no
     * letters, whitespace, or special characters. Additionally, the numeric sequence should be no more than 8 digits
     * in length to prevent excessive processing time
     * @param numericSequence The numeric sequence to validate
     * @return True if the numeric sequence is valid, otherwise false
     */
    public boolean isValidNumericSequence(String numericSequence) {
        if (numericSequence.length() > 8) {
            return false;
        }
        boolean isValid = numericSequence.matches("^[0-9]+$");

        logger.debug("Numeric Sequence {} valid - {}", numericSequence, isValid);
        return isValid;
    }

}
