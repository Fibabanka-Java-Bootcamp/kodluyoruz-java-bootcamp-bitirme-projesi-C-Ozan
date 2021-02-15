package org.kodluyoruz.mybank.api;


import org.kodluyoruz.mybank.model.Customers;
import org.kodluyoruz.mybank.servis.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CustomerAPI {


    @Autowired
    private CustomerService customerService;

    @PostMapping("/createcustomer")
    public Customers createCustomer(@RequestBody Customers customer) {

        return customerService.createCustomer(customer);


    }

    @RequestMapping(value = "/customerdelete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    @ResponseBody
    public String deleteCustomer(@PathVariable("id") int id) {

        String result = customerService.deleteCustomersById(id);

        return result;
    }


    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.GET}, value = "/customerupdate/{id}")
    public void updateCustomer(@RequestBody Customers customer, @PathVariable int id) {

        customerService.updateCustomerById(id, customer);

    }


    @GetMapping("/getcustomer/{id}")
    public Customers getCustomers(@PathVariable int id) {

        return customerService.getCustomersById(id);

    }


}
