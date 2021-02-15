package org.kodluyoruz.mybank.servis;

import org.kodluyoruz.mybank.model.Customers;
import org.kodluyoruz.mybank.model.DrawingAccounts;
import org.kodluyoruz.mybank.model.SavingsAccount;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.repository.DrawingAccountsRepository;
import org.kodluyoruz.mybank.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private DrawingAccountsRepository drawingAccountRepository;


    public Customers createCustomer(Customers customer) {

        return customerRepository.save(customer);
    }


    public Customers getCustomersById(int id) {

        Customers customer = customerRepository.findById(id).orElse(null);

        return customer;

    }

    public String deleteCustomersById(int id) {


        List<SavingsAccount> savingAccountList = savingsAccountRepository.findByCustomerId(id);
        List<DrawingAccounts> drawingAccountList = drawingAccountRepository.findByCustomerId(id);
        Optional<Customers> customersList = customerRepository.findById(id);

        if (customersList.isEmpty())
            return id + " " + "No'lu müşteri bulunamadı.";


        for (SavingsAccount savingsAccount : savingAccountList) {

            if (savingsAccount.getBalance().intValue() > 0) {

                return savingsAccount.getId() + " " + "No'lu Birikim Hesabınızda" + savingsAccount.getBalance()
                        + " "
                        + savingsAccount.getCurrency_type() +
                        " bulunmaktadır.";

            }

        }
        for (DrawingAccounts drawingAccount : drawingAccountList) {

            if (drawingAccount.getBalance().intValue() > 0) {

                return drawingAccount.getId() + " " + "No'lu Birikim Hesabınızda" + drawingAccount.getBalance()
                        + " "
                        + drawingAccount.getCurrency_type() +
                        " bulunmaktadır.";

            }

        }


        customerRepository.deleteById(id);


        return "customer removed" + id;

    }

    public Customers updateCustomerById(int id, Customers customer) {

        Customers tempCustomers = customerRepository.findById(id).orElse(null);


        try {
            if (!customer.getFirstName().equals(null)) {
                tempCustomers.setFirstName(customer.getFirstName());
            }
        } catch (Exception e) {

        }
        try {
            if (!customer.getLastName().equals(null)) {
                tempCustomers.setLastName(customer.getLastName());
            }
        } catch (Exception e) {

        }
        try {
            if (!customer.getIdentificationNumber().equals(null)) {
                tempCustomers.setIdentificationNumber(customer.getIdentificationNumber());
            }
        } catch (Exception e) {

        }
        try {
            if (!customer.getGSM().equals(null)) {
                tempCustomers.setGSM(customer.getGSM());
            }
        } catch (Exception e) {
            System.out.println("");
        }
        try {
            if (!customer.getEmail().equals(null)) {
                tempCustomers.setEmail(customer.getEmail());
            }
        } catch (Exception e) {
            System.out.println("");

        }

        return customerRepository.save(tempCustomers);
    }


}
