package org.kodluyoruz.mybank.servis;


import org.kodluyoruz.mybank.model.*;
import org.kodluyoruz.mybank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class CartControlService {


    @Autowired
    ExpensesRepository expensesRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    DrawingAccountsRepository drawingAccountsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ATMCardRepository ATMCardRepository;


    public String createATMCard(int drawingAccountId) {

        DrawingAccounts drawingAccounts = drawingAccountsRepository.findById2(drawingAccountId);
        ATMCard atmCard = new ATMCard();
        atmCard.setBalance(drawingAccounts.getBalance());


        drawingAccounts.getAtmCard().add(atmCard);

        drawingAccountsRepository.saveAndFlush(drawingAccounts);

        return "Banka kartı hesaba tanımlanmıştır.";

    }


    public String shoppingATMCard(int drawingAccountId, int cardId, Long purchaseAmount) {

        ATMCard atmCard = ATMCardRepository.findById2(cardId);
        DrawingAccounts drawingAccounts = drawingAccountsRepository.findById2(drawingAccountId);

        if (atmCard.getBalance() < purchaseAmount) {
            return "Hesapta yeterli miktarda para bulunmamaktadır.";
        }

        atmCard.setBalance(atmCard.getBalance() - purchaseAmount);
        drawingAccounts.setBalance(drawingAccounts.getBalance() - purchaseAmount);


        drawingAccountsRepository.saveAndFlush(drawingAccounts);
        ATMCardRepository.saveAndFlush(atmCard);


        return "Ödeme işlemi gerçekleştirildi.";

    }


    public String createCreditCard(Long customer_id, Long card_limit) {


        Customers customer = customerRepository.findById2(customer_id.intValue());
        CreditCard creditCard = new CreditCard();

        creditCard.setCrediCartLimit(card_limit);
        customer.getCreditCard().add(creditCard);


        customerRepository.saveAndFlush(customer);


        return "Kredi kart müşteriye tanımlandı.";
    }

    public String shoppingCreditCard(Long cardid, Long purchaseamount) {

        CreditCard creditCard = creditCardRepository.findById2(cardid);
        Expenses expenses = new Expenses();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

        expenses.setAmount(purchaseamount);
        expenses.setTransactionDate(date);

        creditCard.getExpenses().add(expenses);

        creditCardRepository.saveAndFlush(creditCard);


        return "İşlem başarılı.";

    }

    public List<Expenses> creditCardStatement(Long creditcardid) {

        List<Expenses> expenses = expensesRepository.findByCreditCardId(creditcardid);

        return expenses;
    }


    public String creditCardDeptInquiry(Long creditcardid) {

        List<Expenses> expenses = expensesRepository.findByCreditCardId(creditcardid);

        int sumDept = 0;

        for (Expenses exp : expenses) {

            sumDept += exp.getAmount();

        }

        return "Kartınıza ait " + sumDept + "TL borç bulunmaktadır.";

    }

    public String creditCartPayDept(Long creditcardid, Long iban) {

        CreditCard creditCard = creditCardRepository.findById2(creditcardid);
        DrawingAccounts drawingAccounts = drawingAccountsRepository.findByIBAN(iban);
        List<Expenses> expenses = expensesRepository.findByCreditCardId(creditcardid);


        int sumDept = 0;

        for (Expenses exp : creditCard.getExpenses()) {

            sumDept += exp.getAmount();

        }


        if (sumDept >= drawingAccounts.getBalance()) {

            return "Hesapta yeterli miktarda para bulunmamaktadır.";
        }


        expenses.clear();
        drawingAccounts.setBalance(drawingAccounts.getBalance() - sumDept);
        creditCard.setExpenses(expenses);
        expensesRepository.deleteAll(expenses);
        drawingAccountsRepository.saveAndFlush(drawingAccounts);


        return "Kart borcunuz ödenmiştir.";
    }
}
