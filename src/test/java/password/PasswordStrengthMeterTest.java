package password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthMeterTest {
    @Test
    void meetsAllCriteria_Then_Strong() throws Exception {
        // given
        PasswordStrengthMeter meter = new PasswordStrengthMeter();

        // when
        PasswordStrength result = meter.meter("ab12!@AB");

        // then
        assertEquals(PasswordStrength.STRONG, result);
    }

}