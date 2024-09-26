package glebkr.paytech_test_task.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import glebkr.paytech_test_task.payment.model.Customer;
import glebkr.paytech_test_task.payment.dto.PaymentRequestDTO;
import glebkr.paytech_test_task.payment.dto.PaymentResponseDTO;
import glebkr.paytech_test_task.payment.model.PaymentTypeEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private static final String API_URL = "https://engine-sandbox.pay.tech/api/v1/payments";
    private static final String BEARER_TOKEN = "cAmmvalAikARkB81fgxgMtnMbEdNbuWa";

    private final ObjectMapper objectMapper;

    public String initiatePayment(double amount) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        headers.set("Content-Type", "application/json");

        Customer customer = Customer.builder().referenceId("123123").citizenshipCountryCode("AU").firstName("Ryan")
                .lastName("Gosling").build();
        PaymentRequestDTO requestDto = PaymentRequestDTO.builder().paymentType(PaymentTypeEnum.DEPOSIT).amount(amount)
                .currency("EUR").customer(customer).build();

        String requestBody = objectMapper.writeValueAsString(requestDto);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<PaymentResponseDTO> response = restTemplate.exchange(API_URL, HttpMethod.POST, request, PaymentResponseDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Objects.requireNonNull(response.getBody()).getResult().getRedirectUrl();
        } else {
            throw new Exception("Payment failed with status: " + response.getStatusCode());
        }
    }

}
