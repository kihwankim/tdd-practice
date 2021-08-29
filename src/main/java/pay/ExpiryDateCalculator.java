package pay;

import pay.dto.PayData;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    private static final int ONE_MONTH_FEE = 10_000;

    public LocalDate calculateExpiryDate(PayData payData) {
        if (payData == null) {
            throw new IllegalArgumentException();
        }

        int usingMonth = payData.getPayAmount() / ONE_MONTH_FEE;

        if (payData.getFirstBillingDate() != null) {

            LocalDate canditateExp = payData.getBillingDate().plusMonths(usingMonth);

            if (payData.getFirstBillingDate().getDayOfMonth() != canditateExp.getDayOfMonth()) {
                return canditateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }

        return payData.getBillingDate().plusMonths(usingMonth);
    }
}
