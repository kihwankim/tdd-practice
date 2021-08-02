package password;

public class PasswordStrengthMeter {
    private static final int MIN_LEN_OF_PASSWORD = 8;

    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        boolean isLengthEnough = s.length() >= MIN_LEN_OF_PASSWORD;
        boolean isContainsNum = isContainsNum(s);
        boolean isContainsUpp = isContainingUppercase(s);

        if (isLengthEnough && !isContainsNum && !isContainsUpp) return PasswordStrength.WEAK;
        if (!isLengthEnough && isContainsNum && !isContainsUpp) return PasswordStrength.WEAK;
        if (!isLengthEnough && !isContainsNum && isContainsUpp) return PasswordStrength.WEAK;

        if (!isLengthEnough) return PasswordStrength.NORMAL;
        if (!isContainsNum) return PasswordStrength.NORMAL;
        if (!isContainsUpp) return PasswordStrength.NORMAL;

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
