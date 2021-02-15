package org.kodluyoruz.mybank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "credit_card")
public class CreditCard {

    @SequenceGenerator(name = "crediCart", sequenceName = "crediCart_seq",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "crediCart", strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "credi_cart_limit")
    private Long crediCartLimit;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private List<Expenses> expenses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCrediCartLimit() {
        return crediCartLimit;
    }

    public void setCrediCartLimit(Long crediCartLimit) {
        this.crediCartLimit = crediCartLimit;
    }

    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expenses> expenses) {
        this.expenses = expenses;
    }
}
