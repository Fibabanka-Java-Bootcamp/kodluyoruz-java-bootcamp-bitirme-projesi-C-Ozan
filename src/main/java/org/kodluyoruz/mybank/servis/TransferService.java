package org.kodluyoruz.mybank.servis;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.kodluyoruz.mybank.model.DrawingAccounts;
import org.kodluyoruz.mybank.model.SavingsAccount;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.repository.DrawingAccountsRepository;
import org.kodluyoruz.mybank.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    public Double changeCurrency(String currency, Long money) {

        String urlData = "";
        int changedMoney = 0;

        try {

            String myUrl = "https://api.exchangeratesapi.io/latest?base=TRY";

            Document doc = Jsoup.connect(myUrl).ignoreContentType(true).get();

            System.out.println(doc);

            Element element = doc.select("body").first();

            urlData = element.text().toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] list = urlData.split(",");


        if (currency.equals("USD")) {

            Double exchangeRate = Double.parseDouble(list[26].substring(7, 15));

            return money * (1 / exchangeRate);
        }

        if (currency.equals("EUR")) {

            Double exchangeRate = Double.parseDouble(list[18].substring(7, 15));

            return money * (1 / exchangeRate);

        }


        return 1.0;
    }


    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DrawingAccountsRepository drawingAccountRepository;


    public String moneyTransfer(int customer_id, Long senderIBAN, Long receiverIBAN, Long money) {

        boolean sameCustomer = isSameCustomerAccount(customer_id, senderIBAN, receiverIBAN);
        SavingsAccount senderAccountSaving = null;
        DrawingAccounts senderAccountDrawing = null;

        SavingsAccount receiverAccountSaving = null;
        DrawingAccounts receiverAccountDrawing = null;

        if (sameCustomer) {


            if (senderIBAN % 2 == 0) {

                senderAccountSaving = savingsAccountRepository.findByIBAN(senderIBAN);

            } else {
                senderAccountDrawing = drawingAccountRepository.findByIBAN(senderIBAN);
            }

            if (receiverIBAN % 2 == 0) {
                receiverAccountSaving = savingsAccountRepository.findByIBAN(receiverIBAN);
            } else {
                receiverAccountDrawing = drawingAccountRepository.findByIBAN(receiverIBAN);
            }

            if (senderAccountSaving != null) {

                if (receiverAccountSaving != null) {

                    if (senderAccountSaving.getCurrency_type().equals(receiverAccountSaving.getCurrency_type())) {

                        senderAccountSaving.setBalance(senderAccountSaving.getBalance() - money);
                        receiverAccountSaving.setBalance(receiverAccountSaving.getBalance() + money);

                        savingsAccountRepository.saveAndFlush(senderAccountSaving);
                        savingsAccountRepository.saveAndFlush(receiverAccountSaving);

                        return senderAccountSaving.getIBAN() + "numaralı hesabınızdan" + senderAccountSaving.getIBAN() + "numaralı hesabnıza transfer işlemi gerçekleştirildi.";
                    } else {
                        //KUR HESABI YAPILARAK TRANSFER GERÇEKLEŞTİRİLİR.

                        senderAccountSaving.setBalance(senderAccountSaving.getBalance() - money);
                        receiverAccountSaving.setBalance(receiverAccountSaving.getBalance() + changeCurrency(senderAccountSaving.getCurrency_type(), money).longValue());

                        savingsAccountRepository.saveAndFlush(senderAccountSaving);
                        savingsAccountRepository.saveAndFlush(receiverAccountSaving);

                        return senderAccountSaving.getIBAN() + "numaralı hesabınızdan" + receiverAccountSaving.getIBAN() + "numaralı hesabnıza transfer işlemi gerçekleştirildi.";
                    }

                }

            } else {

                if (receiverAccountSaving != null) {

                    if (senderAccountDrawing.getCurrency_type().equals(receiverAccountSaving.getCurrency_type())) {

                        senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                        receiverAccountSaving.setBalance(receiverAccountSaving.getBalance() + money);

                        drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                        savingsAccountRepository.saveAndFlush(receiverAccountSaving);

                        return senderAccountDrawing.getIBAN() + "numaralı hesabınızdan" + receiverAccountSaving.getIBAN() + "numaralı hesaba transfer işlemi gerçekleştirildi.";

                    } else {
                        //KUR HESABI YAPILARAK TRANSFER GERÇEKLEŞTİRİLİR.

                        senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                        receiverAccountSaving.setBalance(receiverAccountSaving.getBalance() + changeCurrency(senderAccountDrawing.getCurrency_type(), money).longValue());

                        drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                        savingsAccountRepository.saveAndFlush(receiverAccountSaving);

                        return senderAccountDrawing.getIBAN() + "numaralı hesabınızdan" + receiverAccountSaving.getIBAN() + "numaralı hesaba transfer işlemi gerçekleştirildi.";
                    }


                } else {

                    if (senderAccountDrawing.getCurrency_type().equals(receiverAccountDrawing.getCurrency_type())) {

                        senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                        receiverAccountDrawing.setBalance(receiverAccountDrawing.getBalance() + money);

                        drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                        drawingAccountRepository.saveAndFlush(receiverAccountDrawing);


                        return senderAccountDrawing.getIBAN() + "numaralı hesabınızdan" + senderAccountSaving.getIBAN() + "numaralı hesabatransfer işlemi gerçekleştirildi.";

                    } else {
                        //KUR HESABI YAPILARAK TRANSFER GERÇEKLEŞTİRİLİR.
                        senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                        receiverAccountDrawing.setBalance(receiverAccountDrawing.getBalance() + changeCurrency(senderAccountDrawing.getCurrency_type(), money).longValue());

                        drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                        drawingAccountRepository.saveAndFlush(receiverAccountDrawing);


                        return senderAccountDrawing.getIBAN() + "numaralı hesabınızdan" + receiverAccountDrawing.getIBAN() + "numaralı hesaba transfer işlemi gerçekleştirildi.";

                    }

                }

            }


        } else if (senderIBAN % 2 == 0) {
            return "Birikim hesabı kendi hesaplarınız dışındaki hesaplara " +
                    "para transfer işlemi için kullanılamaz.";

        } else {

            senderAccountDrawing = drawingAccountRepository.findByIBAN(senderIBAN);

            if (receiverIBAN % 2 == 0) {

                receiverAccountSaving = savingsAccountRepository.findByIBAN(receiverIBAN);

                if (senderAccountDrawing.getCurrency_type().equals(receiverAccountSaving.getCurrency_type())) {

                    senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                    receiverAccountSaving.setBalance(receiverAccountSaving.getBalance() + money);

                    drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                    savingsAccountRepository.saveAndFlush(receiverAccountSaving);

                    return senderAccountDrawing.getIBAN() + "no'lu hesabınızdan" + receiverAccountSaving.getIBAN() +
                            "no'lu hesaba" + money + " " + receiverAccountSaving.getCurrency_type() + "transfer gerçekleştirilmiştir.";


                } else {
                    //Burada alıcıya göre para birimi değişimi yapılacak.Para dönüşüm yap ve transferi gerçekleştir.

                    senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                    receiverAccountSaving.setBalance(receiverAccountSaving.getBalance() + changeCurrency(senderAccountDrawing.getCurrency_type(), money).longValue());

                    drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                    savingsAccountRepository.saveAndFlush(receiverAccountSaving);

                    return senderAccountDrawing.getIBAN() + "no'lu hesabınızdan" + receiverAccountSaving.getIBAN() +
                            "no'lu hesaba" + money + " " + receiverAccountSaving.getCurrency_type() + "transfer gerçekleştirilmiştir.";

                }


            } else {
                receiverAccountDrawing = drawingAccountRepository.findByIBAN(receiverIBAN);

                if (senderAccountDrawing.getCurrency_type().equals(receiverAccountDrawing.getCurrency_type())) {

                    senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                    receiverAccountDrawing.setBalance(receiverAccountDrawing.getBalance() + money);

                    drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                    drawingAccountRepository.saveAndFlush(receiverAccountDrawing);

                    return senderAccountDrawing.getIBAN() + "no'lu hesabınızdan" + receiverAccountDrawing.getIBAN() +
                            "no'lu hesaba" + money + " TL transfer gerçekleştirilmiştir.";


                } else {
                    //Burada alıcıya göre para birimi değişimi yapılacak.para dönüşümü yap ve transferi gerçekleştir.

                    senderAccountDrawing.setBalance(senderAccountDrawing.getBalance() - money);
                    receiverAccountDrawing.setBalance(receiverAccountDrawing.getBalance() + changeCurrency(senderAccountDrawing.getCurrency_type(), money).longValue());

                    drawingAccountRepository.saveAndFlush(senderAccountDrawing);
                    drawingAccountRepository.saveAndFlush(receiverAccountDrawing);

                    return senderAccountDrawing.getIBAN() + "no'lu hesabınızdan" + receiverAccountDrawing.getIBAN() +
                            "no'lu hesaba" + money + " " + receiverAccountDrawing.getCurrency_type() + "transfer gerçekleştirilmiştir.";


                }


            }


        }


        return "";
    }

    public boolean isSameCustomerAccount(int customer_id, Long senderIBAN, Long receiverIBAN) {

        List<SavingsAccount> savingsAccountList = savingsAccountRepository.findByCustomerId(customer_id);
        List<DrawingAccounts> drawingAccountsList = drawingAccountRepository.findByCustomerId(customer_id);

        int savingAccountFlag = 0;
        int drawingAccountFlag = 0;
        int tempSenderIBAN = senderIBAN.intValue();
        int tempReceiverIBAN = receiverIBAN.intValue();

        try {
            for (SavingsAccount savingsAccount : savingsAccountList) {

                int getIBAN = savingsAccount.getIBAN().intValue();

                if (getIBAN == tempSenderIBAN) {

                    savingAccountFlag = 1;

                }

                if (savingsAccount.getIBAN().intValue() == tempReceiverIBAN) {

                    savingAccountFlag = 1;

                }

            }

            for (DrawingAccounts drawingAccounts : drawingAccountsList) {

                int getIBAN = drawingAccounts.getIBAN().intValue();

                if (getIBAN == tempSenderIBAN) {
                    drawingAccountFlag = 1;

                }


                if (drawingAccounts.getIBAN().intValue() == tempReceiverIBAN) {
                    drawingAccountFlag = 1;

                }

            }


            if (savingAccountFlag == 1 && drawingAccountFlag == 1)
                return true;
        } catch (Exception e) {

        }


        return false;
    }


}
