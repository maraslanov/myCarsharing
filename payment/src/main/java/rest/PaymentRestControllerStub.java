package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.PayerDTO;
import dto.PaymentResultDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestControllerStub {

    @PostMapping(path = "/paymentservice"
//            ,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody
    PaymentResultDTO doPayment (PayerDTO payerDTO
    ) throws JsonProcessingException {
        PaymentResultDTO result = new PaymentResultDTO();
        if (payerDTO == null) {
            result.setPaymentResult(false);
            return result;
        }
        result.setPaymentResult(Math.random() > 0.25);
        return result;
    }

}
