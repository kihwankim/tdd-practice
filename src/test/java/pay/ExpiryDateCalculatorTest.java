package pay;

import org.junit.jupiter.api.Test;
import pay.dto.PayData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpiryDateCalculatorTest {
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
        LocalDate payDay = LocalDate.of(2021, 2, 1);
        LocalDate otherPayDay = LocalDate.of(2021, 12, 1);
        int payAmmount = 10_000;


        assertExpiryDate(PayData.builder()
                .billingDate(payDay)
                .payAmount(payAmmount)
                .build(), LocalDate.of(2021, 3, 1));
        assertExpiryDate(PayData.builder()
                .billingDate(otherPayDay)
                .payAmount(payAmmount)
                .build(), LocalDate.of(2022, 1, 1));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않는_경우() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(2021, 1, 31);
        int payAmmount = 10_000;

        assertExpiryDate(PayData.builder()
                .billingDate(payDay)
                .payAmount(payAmmount)
                .build(), LocalDate.of(2021, 2, 28));
    }

    @Test
    void 이_만원_납부하는_경우() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(2021, 1, 31);
        int payAmount = 20_000;

        assertExpiryDate(PayData.builder()
                .billingDate(payDay)
                .payAmount(payAmount)
                .build(), LocalDate.of(2021, 3, 31));
    }
}
