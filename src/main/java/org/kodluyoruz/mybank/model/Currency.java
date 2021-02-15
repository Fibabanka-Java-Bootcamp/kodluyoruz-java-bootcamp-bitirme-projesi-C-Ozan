package org.kodluyoruz.mybank.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Currency")
public class Currency {


    @SequenceGenerator(name = "currency", sequenceName = "currency_seq",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "currency", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "currencyType", length = 100)
    private String currencyType;


    @OneToMany(fetch = FetchType.LAZY
    )
    private List<DrawingAccounts> drawingAccounts;

/*
    @OneToMany(fetch = FetchType.LAZY, targetEntity = SavingsAccount.class)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private List<SavingsAccount> savingsAccount;
*/

    @Override
    public String toString() {
        return currencyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public List<DrawingAccounts> getDrawingAccounts() {
        return drawingAccounts;
    }

    public void setDrawingAccounts(List<DrawingAccounts> drawingAccounts) {
        this.drawingAccounts = drawingAccounts;
    }
/*
    public List<SavingsAccount> getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(List<SavingsAccount> savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

 */
}
