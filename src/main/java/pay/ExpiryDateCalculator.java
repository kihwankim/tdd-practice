package pay;

import pay.dto.PayData;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    private static final int ONE_MONTH_FEE = 10_000;
    private static final int ONE_YEAR_FEE = 10 * ONE_MONTH_FEE;

    public LocalDate calculateExpiryDate(PayData payData) {
        if (payData == null) {
            throw new IllegalArgumentException();
        }

        int addedMonths = payData.getPayAmount() == ONE_YEAR_FEE ? 12 : payData.getPayAmount() / ONE_MONTH_FEE;

        if (payData.getFirstBillingDate() != null) {
            return expireDateUsingFirstBillingDate(payData, addedMonths);
        }

        return payData.getBillingDate().plusMonths(addedMonths); // 첫 달이 언제인지 모르는 경우
    }

    private LocalDate expireDateUsingFirstBillingDate(PayData payData, int usingMonth) {
        // 31에 처음 납부 현재 2월 인 경우
        LocalDate canditateExp = payData.getBillingDate().plusMonths(usingMonth);

        if (payData.getFirstBillingDate().getDayOfMonth() != canditateExp.getDayOfMonth()) { // 달의 날짜 길이 비교
            int dayLenOfCandiMon = YearMonth.from(canditateExp).lengthOfMonth();
            if (dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                return canditateExp.withDayOfMonth(dayLenOfCandiMon); // 짧은 경우
            }

            return canditateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth()); // 긴경우
        }

        return canditateExp; // 일반 경우
    }
}
