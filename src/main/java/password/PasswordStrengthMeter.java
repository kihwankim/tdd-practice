package password;

public class PasswordStrengthMeter {
    private static final int MIN_LEN_OF_PASSWORD = 8;

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        if (s.length() < MIN_LEN_OF_PASSWORD) {
            return PasswordStrength.NORMAL;
        }

        if (!isContainsNum(s)) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private boolean isContainsNum(String s) {
        for (char ch : s.toCharArray()) {
            if ('0' <= ch && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
