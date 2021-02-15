package org.kodluyoruz.mybank.servis;


import org.kodluyoruz.mybank.model.*;
import org.kodluyoruz.mybank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class AccountsService {


    @Autowired
    public SavingsAccountRepository savingsAccountRepository;

    @Autowired
    public DrawingAccountsRepository drawingAccountsRepository;
    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public CurrencyRepository currencyRepository;
    @Autowired
    public BranchesRepository branchesRepository;


    public String createSavingAccounts(int customer_id, String currency) {

        try {

            Optional<Customers> customer = customerRepository.findById(customer_id);

            SavingsAccount savingsAccount = new SavingsAccount();

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();

            long IBAN;
            IBAN = (long) (Math.random() * 111111111) * 2;


            savingsAccount.setAccountOpeningDate(date);
            savingsAccount.setDepositDate(date);
            savingsAccount.setBalance(0L);
            savingsAccount.setWithdrawalDate(date);
            savingsAccount.setCurrency_type(currency);
            savingsAccount.setIBAN(IBAN);

            customer.get().getSavingsAccount().add(savingsAccount);

            customerRepository.save(customer.get());

        } catch (Exception e) {


        }


        return "İşlem Başarılı.Birikim Hesabınız açıldı.";

    }


    public String deleteSavingsAccountsById(int accountsid) {

        try {

            SavingsAccount savingsAccount = savingsAccountRepository.findById(accountsid);

            if (savingsAccount == null)
                return "Hesap Bulunamadı.";

            if (savingsAccount.getBalance().intValue() > 0)
                return "Hesabınızda para bulunmaktadır.Birikim Hesabı silme işlemi gerçekleştirilemedi.";


            savingsAccountRepository.deleteById(accountsid);


        } catch (Exception e) {

        }
        return "Hesap Silme İşlemi Gerçekleştirildi.";

    }

    public String createDrawingAccounts(int customer_id, String currency) {
        try {

            Optional<Customers> customer = customerRepository.findById(customer_id);

            DrawingAccounts drawingAccount = new DrawingAccounts();

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();

            long IBAN;
            IBAN = (long) (Math.random() * 111111111) * 2 + 1;


            drawingAccount.setAccountOpeningDate(date);
            drawingAccount.setDepositDate(date);
            drawingAccount.setBalance(0L);
            drawingAccount.setWithdrawalDate(date);
            drawingAccount.setCurrency_type(currency);
            drawingAccount.setIBAN(IBAN);


            customer.get().getDrawingAccounts().add(drawingAccount);

            customerRepository.save(customer.get());

        } catch (Exception e) {


        }


        return "İşlem Başarılı.Vadesiz Mevduat Hesabınız açıldı.";
    }

    public String deleteDrawingAccountsById(int accountsid) {

        try {

            Optional<DrawingAccounts> drawingAccounts = drawingAccountsRepository.findById(accountsid);

            if (drawingAccounts == null)
                return "Hesap Bulunamadı.";

            if (drawingAccounts.get().getBalance().intValue() > 0)
                return "Hesabınızda para bulunmaktadır.Vadesiz Mevduat Hesabı silme işlemi gerçekleştirilemedi.";


            drawingAccountsRepository.deleteById(accountsid);


        } catch (Exception e) {

        }
        return "Hesap Silme İşlemi Gerçekleştirildi.";

    }
}
