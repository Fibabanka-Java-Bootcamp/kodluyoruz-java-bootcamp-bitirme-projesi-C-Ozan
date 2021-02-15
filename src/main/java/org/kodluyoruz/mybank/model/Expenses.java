package org.kodluyoruz.mybank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Expenses")
public class Expenses {

    @SequenceGenerator(name = "expenses", sequenceName = "expenses_seq",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "expenses", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "Description", length = 200)
    private String description;

    @Column(name = "Amount")
    private Long amount;


    @Column(name = "transaction_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date transactionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
