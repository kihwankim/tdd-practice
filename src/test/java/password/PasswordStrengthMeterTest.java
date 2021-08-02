package password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthMeterTest {

    PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @Test
    void meetsAllCriteria_Then_Strong() throws Exception {
        // given

        // when
        PasswordStrength result = meter.meter("ab12!@AB");

        // then
        assertEquals(PasswordStrength.STRONG, result);
    }

    @Test
    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 겨우")
    void meetsOtherCriteria_except_for_length_Then_Normal() throws Exception {
        // given
        String password1 = "ab12!@A";
        String password2 = "Ab12!c";

        // when
        PasswordStrength result1 = meter.meter(password1);
        PasswordStrength result2 = meter.meter(password2);

        // then
        assertEquals(PasswordStrength.NORMAL, result1);
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우")
    void meetsOtherCriteria_except_for_number_Then_Normal() throws Exception {
        // given
        String passwordExceptNumber = "ab!@ABqwe";

        // when
        PasswordStrength result = this.meter.meter(passwordExceptNumber);

        // then
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    @DisplayName("값이 없는 경우")
    void nullInput_Then_Invalid() throws Exception {
        // given
        String password = null;

        // when
        PasswordStrength result = this.meter.meter(password);

        // then
        assertEquals(PasswordStrength.INVALID, result);
    }

    @Test
    @DisplayName("공백 문자열")
    void emptyInput_Then_Invalid() throws Exception {
        // given
        String password = "";

        // when
        PasswordStrength result = this.meter.meter(password);

        // then
        assertEquals(PasswordStrength.INVALID, result);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하는 경우")
    void meetsOtherCriteria_except_for_Uppercase_Then_Nomal() throws Exception {
        // given
        String password = "ab12!@df";

        // when
        PasswordStrength result = this.meter.meter(password);

        // then
        assertEquals(PasswordStrength.NORMAL, result);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우")
    void meetOnlyLengthCriteria_Then_Weak() throws Exception {
        // given
        String password = "abdefghi";

        // when
        PasswordStrength result = this.meter.meter(password);

        // then
        assertEquals(PasswordStrength.WEAK, result);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족하는 경우")
    void meetsOnlyUpperCriteria_Then_Weak() throws Exception {
        // given
        String password = "ABZEF";

        // when
        PasswordStrength result = this.meter.meter(password);

        // then
        assertEquals(PasswordStrength.WEAK, result);
    }
}