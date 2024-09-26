package glebkr.paytech_test_task.payment.dto;

import glebkr.paytech_test_task.payment.model.PaymentResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private PaymentResult result;
}
