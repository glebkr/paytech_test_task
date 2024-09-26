package glebkr.paytech_test_task.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String referenceId;
    private String citizenshipCountryCode;
    private String firstName;
    private String lastName;
}
