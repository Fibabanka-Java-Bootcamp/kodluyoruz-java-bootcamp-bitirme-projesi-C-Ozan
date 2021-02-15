package org.kodluyoruz.mybank.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Customers")
public class Customers {


    @SequenceGenerator(name = "customer", sequenceName = "customer_seq",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "customer", strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(name = "identification_no", length = 50)
    private String identificationNumber;
    @Column(name = "gsm", length = 50)
    private String GSM;
    @Column(name = "email", length = 100)
    private String email;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = SavingsAccount.class)

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<SavingsAccount> savingsAccount;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = DrawingAccounts.class)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<DrawingAccounts> drawingAccounts;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = CreditCard.class)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<CreditCard> CreditCard;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getGSM() {
        return GSM;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /*
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }


    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }*/

    public List<SavingsAccount> getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(List<SavingsAccount> savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public List<DrawingAccounts> getDrawingAccounts() {
        return drawingAccounts;
    }

    public void setDrawingAccounts(List<DrawingAccounts> drawingAccounts) {
        this.drawingAccounts = drawingAccounts;
    }

    public List<org.kodluyoruz.mybank.model.CreditCard> getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(List<org.kodluyoruz.mybank.model.CreditCard> creditCard) {
        CreditCard = creditCard;
    }
}
