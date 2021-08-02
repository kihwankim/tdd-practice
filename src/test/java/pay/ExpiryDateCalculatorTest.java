package pay;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpiryDateCalculatorTest {
    ExpiryDateCalculator cal = new ExpiryDateCalculator();

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(2021, 2, 1);
        LocalDate otherPayDay = LocalDate.of(2021, 12, 1);
        int payAmmount = 10_000;

        // when
        LocalDate expiryDate = cal.calculateExpiryDate(payDay, payAmmount);
        LocalDate otherExpireDate = cal.calculateExpiryDate(otherPayDay, payAmmount);

        // then
        assertEquals(LocalDate.of(2021, 3, 1), expiryDate);
        assertEquals(LocalDate.of(2022, 1, 1), otherExpireDate);
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않는_경우() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(2021, 1, 31);
        int payAmmount = 10_000;

        // when
        LocalDate expiryDate = cal.calculateExpiryDate(payDay, payAmmount);

        // then
        assertEquals(LocalDate.of(2021, 2, 28), expiryDate);
    }

    @Test
    void 이_만원_납부하는_경우() throws Exception {
        // given
        LocalDate payDay = LocalDate.of(2021, 1, 31);
        int payAmount = 20_000;

        // when
        LocalDate expiryDate = cal.calculateExpiryDate(payDay, payAmount);


        // then
        assertEquals(LocalDate.of(2021, 3, 31), expiryDate);
    }
}
