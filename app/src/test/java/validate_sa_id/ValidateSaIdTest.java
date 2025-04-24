package validate_sa_id;

import org.junit.jupiter.api.Test;

import validate_sa_id.ValidateSaId;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {

    @Test
    public void testValidIds() {
        assertTrue(ValidateSaId.isIdNumberValid("2001014800086")); // Valid
        assertTrue(ValidateSaId.isIdNumberValid("2909035800085")); // Valid
    }

    @Test
    public void testInvalidLength() {
        assertFalse(ValidateSaId.isIdNumberValid("12345"));
        assertFalse(ValidateSaId.isIdNumberValid("2001014800086999"));
    }

    @Test
    public void testInvalidCharacters() {
        assertFalse(ValidateSaId.isIdNumberValid("20010A4800086"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(ValidateSaId.isIdNumberValid("9913315000087")); // 31st month
    }

    @Test
    public void testInvalidGenderRange() {
        assertFalse(ValidateSaId.isIdNumberValid("2001019999982")); // Still valid numerically, but not real gender
    }

    @Test
    public void testInvalidCitizenship() {
        assertFalse(ValidateSaId.isIdNumberValid("2001014800286")); // 2 is invalid citizenship
    }

    @Test
    public void testInvalidChecksum() {
        assertFalse(ValidateSaId.isIdNumberValid("2001014800080")); // Bad checksum
    }

    @Test
public void testMyCustomId() {
    assertTrue(ValidateSaId.isIdNumberValid("0206285493089")); // own ID number
}

}
