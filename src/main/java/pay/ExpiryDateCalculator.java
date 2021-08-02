package pay;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    private static final int ONE_MONTH_FEE = 10_000;

    public LocalDate calculateExpiryDate(LocalDate payDay, int payAmmount) {

        return payDay.plusMonths(payAmmount / ONE_MONTH_FEE);
    }
}
