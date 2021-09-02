package pay;

import org.junit.jupiter.api.Test;
import pay.dto.PayData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpiryDateCalculatorTest {

    private static final int THIS_YEAR = LocalDate.now().getYear();

    ExpiryDateCalculator cal = new ExpiryDateCalculator();

    private void assertExpiryDate(PayData payData, LocalDate expectedDate) {

        // when
        LocalDate localDate = cal.calculateExpiryDate(payData);

        // then
        assertEquals(expectedDate, localDate);
    }


    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(THIS_YEAR, 2, 1);
        LocalDate otherPayDay = LocalDate.of(THIS_YEAR, 12, 1);
        int payAmmount = 10_000;


        assertExpiryDate(PayData.builder()
                .billingDate(payDay)
                .payAmount(payAmmount)
                .build(), LocalDate.of(THIS_YEAR, 3, 1));
        assertExpiryDate(PayData.builder()
                .billingDate(otherPayDay)
                .payAmount(payAmmount)
                .build(), LocalDate.of(THIS_YEAR + 1, 1, 1));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않는_경우() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(THIS_YEAR, 1, 31);
        int payAmmount = 10_000;

        assertExpiryDate(PayData.builder()
                .billingDate(payDay)
                .payAmount(payAmmount)
                .build(), LocalDate.of(THIS_YEAR, 2, 28));
    }

    @Test
    void 이_만원_납부하는_경우() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(THIS_YEAR, 1, 31);
        int payAmount = 20_000;

        assertExpiryDate(PayData.builder()
                .billingDate(payDay)
                .payAmount(payAmount)
                .build(), LocalDate.of(THIS_YEAR, 3, 31));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() throws Exception {
        // given
        PayData payDataFirstMonth = PayData.builder()
                .firstBillingDate(LocalDate.of(THIS_YEAR, 1, 31))
                .billingDate(LocalDate.of(THIS_YEAR, 2, 28))
                .payAmount(10_000)
                .build();

        PayData payDataSecondMonth = PayData.builder()
                .firstBillingDate(LocalDate.of(THIS_YEAR, 1, 30))
                .billingDate(LocalDate.of(THIS_YEAR, 2, 28))
                .payAmount(10_000)
                .build();

        PayData firstOneMonthLaterAndPayOneMore = PayData.builder()
                .firstBillingDate(LocalDate.of(THIS_YEAR, 5, 31))
                .billingDate(LocalDate.of(THIS_YEAR, 6, 30))
                .payAmount(10_000)
                .build();

        // when

        // then
        assertExpiryDate(payDataFirstMonth, LocalDate.of(THIS_YEAR, 3, 31));
        assertExpiryDate(payDataSecondMonth, LocalDate.of(THIS_YEAR, 3, 30));
        assertExpiryDate(firstOneMonthLaterAndPayOneMore, LocalDate.of(THIS_YEAR, 7, 31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() throws Exception {
        // given
        PayData payDateTwentyTho = PayData.builder()
                .billingDate(LocalDate.of(THIS_YEAR, 3, 1))
                .payAmount(20_000)
                .build();
        PayData payDateThirtyTho = PayData.builder()
                .billingDate(LocalDate.of(THIS_YEAR, 3, 1))
                .payAmount(30_000)
                .build();

        // when

        // then
        assertExpiryDate(payDateTwentyTho, LocalDate.of(THIS_YEAR, 5, 1));
        assertExpiryDate(payDateThirtyTho, LocalDate.of(THIS_YEAR, 6, 1));
    }

    @Test
    void 첫_납분일과_만료일_일자가_다를때_이만원_이상_납부() throws Exception {
        // given
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(THIS_YEAR, 1, 31))
                .billingDate(LocalDate.of(THIS_YEAR, 2, 28))
                .payAmount(20_000)
                .build();

        PayData payDataSecond = PayData.builder()
                .firstBillingDate(LocalDate.of(THIS_YEAR, 3, 31))
                .billingDate(LocalDate.of(THIS_YEAR, 4, 30))
                .payAmount(30_000)
                .build();

        PayData paydata40_000WonAndPayFeb = PayData.builder()
                .firstBillingDate(LocalDate.of(THIS_YEAR, 1, 31))
                .billingDate(LocalDate.of(THIS_YEAR, 2, 28))
                .payAmount(40_000)
                .build();

        // when

        // then
        assertExpiryDate(payData, LocalDate.of(THIS_YEAR, 4, 30));
        assertExpiryDate(payDataSecond, LocalDate.of(THIS_YEAR, 7, 31));
        assertExpiryDate(paydata40_000WonAndPayFeb, LocalDate.of(THIS_YEAR, 6, 30));
    }
}
