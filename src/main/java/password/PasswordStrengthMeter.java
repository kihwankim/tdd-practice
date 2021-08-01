package password;

public class PasswordStrengthMeter {
    private static final int MIN_LEN_OF_PASSWORD = 8;

    public PasswordStrength meter(String s) {
        if (s.length() < MIN_LEN_OF_PASSWORD) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }
}
