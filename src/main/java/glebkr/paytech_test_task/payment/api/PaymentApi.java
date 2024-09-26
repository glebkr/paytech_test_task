package glebkr.paytech_test_task.payment.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import glebkr.paytech_test_task.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class PaymentApi {

    private final PaymentService paymentService;

    @GetMapping("/")
    public String getPayments() {
        return "paymentForm";
    }

    @PostMapping("/pay")
    public Object pay(@RequestParam("amount") double amount) {
        try {
            String redirectUrl = paymentService.initiatePayment(amount);
            return new RedirectView(redirectUrl);
        } catch (Exception ex) {
            return "paymentErrorPage";
        }
    }

}
