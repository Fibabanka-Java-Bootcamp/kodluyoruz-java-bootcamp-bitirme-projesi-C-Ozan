package org.kodluyoruz.mybank.api;


import org.kodluyoruz.mybank.servis.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountsAPI {

    @Autowired
    AccountsService accountsService;

    @PostMapping("/createsavingsaccount/{customer_id}/{currency}")
    @ResponseBody
    public String createSavingAccount(@PathVariable int customer_id,
                                      @PathVariable String currency
    ) {

        String result = accountsService.createSavingAccounts(customer_id, currency);

        return result;

    }

    @RequestMapping(value = "/savingaccountsdelete/{accountsid}", method = {RequestMethod.DELETE, RequestMethod.GET})
    @ResponseBody
    public String deleteSavingAccount(@PathVariable("accountsid") int accountsid) {

        String result = accountsService.deleteSavingsAccountsById(accountsid);

        return result;
    }

    @PostMapping("/createdrawingsaccount/{customer_id}/{currency}")
    @ResponseBody
    public String createDrawingAccount(@PathVariable int customer_id,
                                       @PathVariable String currency
    ) {

        String result = accountsService.createDrawingAccounts(customer_id, currency);

        return result;

    }

    @RequestMapping(value = "/drawingaccountsdelete/{accountsid}", method = {RequestMethod.DELETE, RequestMethod.GET})
    @ResponseBody
    public String deleteDrawingAccount(@PathVariable("accountsid") int accountsid) {

        String result = accountsService.deleteDrawingAccountsById(accountsid);

        return result;
    }


}
