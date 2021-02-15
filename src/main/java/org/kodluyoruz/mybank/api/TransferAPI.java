package org.kodluyoruz.mybank.api;


import org.kodluyoruz.mybank.servis.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransferAPI {


    @Autowired
    TransferService transferService;

    @PostMapping("/moneytransfer/{customer_id}/{senderIBAN}/{receiverIBAN}/{money}")
    @ResponseBody
    public String moneyTransfer(@PathVariable int customer_id, @PathVariable Long senderIBAN,
                                @PathVariable Long receiverIBAN,
                                @PathVariable Long money) {


        String result = transferService.moneyTransfer(customer_id, senderIBAN, receiverIBAN, money);

        return result;
    }


}
