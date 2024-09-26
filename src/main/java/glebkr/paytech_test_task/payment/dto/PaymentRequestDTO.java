package glebkr.paytech_test_task.payment.dto;

import glebkr.paytech_test_task.payment.model.Customer;
import glebkr.paytech_test_task.payment.model.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private Double amount;
    private String currency;
    private PaymentTypeEnum paymentType;
    private Customer customer;
}
