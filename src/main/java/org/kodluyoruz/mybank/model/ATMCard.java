package org.kodluyoruz.mybank.model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "ATMCard")
public class ATMCard {

    @SequenceGenerator(name = "ATMCard", sequenceName = "ATMCard_seq",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "ATMCard", strategy = GenerationType.SEQUENCE)
    private int id;


    @Column(name = "balance")
    private Long balance;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
