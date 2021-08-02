package pay;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(LocalDate payDay, int payAmmount) {

        return payDay.plusMonths(1);
    }
}
