package org.kodluyoruz.mybank.api;

import org.kodluyoruz.mybank.model.Expenses;
import org.kodluyoruz.mybank.servis.CartControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CartControlAPI {


    @Autowired
    private CartControlService cartControlService;


    @PostMapping("/createatmcard/{drawingAccountId}")
    @ResponseBody
    public String createATMCard(@PathVariable("drawingAccountId") int drawingAccountId) {

        String result = cartControlService.createATMCard(drawingAccountId);

        return result;


    }

    @PostMapping("/shoppingatmcard/{drawingid}/{cardid}/{purchaseamount}")
    @ResponseBody
    public String shoppingATMCard(@PathVariable("drawingid") int drawingid,
                                  @PathVariable("cardid") int cardid,
                                  @PathVariable("purchaseamount") Long purchaseamount) {


        String result = cartControlService.shoppingATMCard(drawingid, cardid, purchaseamount);

        return result;


    }

    @PostMapping("/createcreditcard/{customer_id}/{creditcartlimit}")
    @ResponseBody
    public String createCreditCard(@PathVariable("customer_id") Long customer_id,
                                   @PathVariable("creditcartlimit") Long creditcartlimit) {

        String result = cartControlService.createCreditCard(customer_id, creditcartlimit);

        return result;


    }

    @PostMapping("/shoppingcreditcard/{cardid}/{purchaseamount}")
    @ResponseBody
    public String shoppingATMCard(@PathVariable("cardid") Long cardid,
                                  @PathVariable("purchaseamount") Long purchaseamount) {


        String result = cartControlService.shoppingCreditCard(cardid, purchaseamount);

        return result;


    }


    @GetMapping("/getcreditcardstatement/{creditcardid}")
    public List<Expenses> creditCardStatement(@PathVariable Long creditcardid) {

        List<Expenses> expenses = cartControlService.creditCardStatement(creditcardid);

        return expenses;

    }

    @GetMapping("/getcreditcarddeptinquiry/{creditcardid}")
    public String creditCardDeptInquiry(@PathVariable Long creditcardid) {

        String result = cartControlService.creditCardDeptInquiry(creditcardid);

        return result;

    }


    @PostMapping("/creditcardpaydept/{iban}/{cardid}")
    @ResponseBody
    public String creditCartPayDept(@PathVariable("iban") Long iban,
                                    @PathVariable("cardid") Long cardid) {


        String result = cartControlService.creditCartPayDept(cardid, iban);

        return result;


    }


}
