package password;

import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 겨우")
    void meetsOtherCriteria_except_for_length_Then_Normal() throws Exception {
        // given
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        String password1 = "ab12!@A";
        String password2 = "Ab12!c";

        // when
        PasswordStrength result1 = meter.meter(password1);
        PasswordStrength result2 = meter.meter(password2);

        // then
        assertEquals(PasswordStrength.NORMAL, result1);
        assertEquals(PasswordStrength.NORMAL, result2);
    }
}