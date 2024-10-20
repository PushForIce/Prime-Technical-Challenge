package validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private final Validator validator = new Validator();

    @ParameterizedTest
    @ValueSource(strings = {"inval%idUsername", "invalid username", "", " "})
    public void testInvalidUsernameIsRejected(String input) {
        assertFalse(validator.isValidUsername(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"validUsername", "username123", "123456789", "a"})
    public void testValidUsernameIsAccepted(String input) {
        assertTrue(validator.isValidUsername(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234 567", "1a2345", "", " ", "@2345", "1234567890"})
    public void testInvalidNumericSequenceIsRejected(String input) {
        assertFalse(validator.isValidNumericSequence(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"23456", "1", "00000", "12345678"})
    public void testValidNumericSequenceIsAccepted(String input) {
        assertTrue(validator.isValidNumericSequence(input));
    }
}