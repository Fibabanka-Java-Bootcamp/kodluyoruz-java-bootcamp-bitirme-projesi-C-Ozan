package org.kodluyoruz.mybank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "drawing_accounts")
public class DrawingAccounts {


    @SequenceGenerator(name = "drawingAccount", sequenceName = "drawingAccount_seq",
            allocationSize = 1)
    @GeneratedValue(generator = "drawingAccount", strategy = GenerationType.SEQUENCE)
    @Id
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


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "drawingAccount_id", referencedColumnName = "id")
    private List<ATMCard> atmCard;

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


    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public Long getIBAN() {
        return IBAN;
    }

    public void setIBAN(Long IBAN) {
        this.IBAN = IBAN;
    }

    public List<ATMCard> getAtmCard() {
        return atmCard;
    }

    public void setAtmCard(List<ATMCard> atmCard) {
        this.atmCard = atmCard;
    }
}
