package org.kodluyoruz.mybank.model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "savings_account")
public class SavingsAccount {


    @SequenceGenerator(name = "savingAccount", sequenceName = "savingAccount_seq",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "savingAccount", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "IBAN")
    private Long IBAN;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date accountOpeningDate;

    @Column(name = "balance")
    private Long balance;


    @Column(name = "currency_type")
    private String currency_type;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date depositDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date withdrawalDate;

    @Column(name = "interest_rate")
    private Long interest_rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(Date accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }


    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public Long getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(Long interest_rate) {
        this.interest_rate = interest_rate;
    }

    public Long getIBAN() {
        return IBAN;
    }

    public void setIBAN(Long IBAN) {
        this.IBAN = IBAN;
    }
}
