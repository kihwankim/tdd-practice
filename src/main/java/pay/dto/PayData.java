package pay.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PayData {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payAmount;

    @Builder
    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }
}
