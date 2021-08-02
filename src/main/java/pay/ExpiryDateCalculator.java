package pay;

import pay.dto.PayData;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    private static final int ONE_MONTH_FEE = 10_000;

    public LocalDate calculateExpiryDate(PayData payData) {

        LocalDate copy = LocalDate.of(payData.getBillingDate().getYear(), payData.getBillingDate().getMonth(), payData.getBillingDate().getDayOfMonth());

        return copy.plusMonths(payData.getPayAmount() / ONE_MONTH_FEE);
    }
}
