package org.kodluyoruz.mybank.model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Branches")
public class Branches {

    @SequenceGenerator(name = "branch", sequenceName = "branch_seq",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "branch", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "branch_name", length = 100)
    private String branchName;

    /*
        @OneToMany(fetch = FetchType.LAZY, targetEntity = DrawingAccounts.class)
        @JoinColumn(name = "branch_id", referencedColumnName = "id")
        private List<DrawingAccounts> drawingAccounts;


            @OneToMany(fetch = FetchType.LAZY, targetEntity = SavingsAccount.class)
            @JoinColumn(name = "branches_id", referencedColumnName = "id")
            private List<SavingsAccount> savingsAccount;
        */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
/*
    public List<DrawingAccounts> getDrawingAccounts() {
        return drawingAccounts;
    }

    public void setDrawingAccounts(List<DrawingAccounts> drawingAccounts) {
        this.drawingAccounts = drawingAccounts;
    }

    public List<SavingsAccount> getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(List<SavingsAccount> savingsAccount) {
        this.savingsAccount = savingsAccount;
    }*/
}
