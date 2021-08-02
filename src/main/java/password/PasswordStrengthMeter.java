package password;

public class PasswordStrengthMeter {
    private static final int MIN_LEN_OF_PASSWORD = 8;

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int metCounts = 0;
        if (s.length() >= MIN_LEN_OF_PASSWORD) metCounts++;
        if (isContainsNum(s)) metCounts++;
        if (isContainingUppercase(s)) metCounts++;

        if (metCounts == 1) return PasswordStrength.WEAK;
        if (metCounts == 2) return PasswordStrength.NORMAL;

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

    private boolean isContainingUppercase(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }

        return false;
    }
}
