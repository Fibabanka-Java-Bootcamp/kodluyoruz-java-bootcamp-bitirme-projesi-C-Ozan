package org.kodluyoruz.mybank.repository;


import org.kodluyoruz.mybank.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {

    @Query("FROM SavingsAccount WHERE customer_id = ?1")
    public List<SavingsAccount> findByCustomerId(int id);

    @Query("FROM SavingsAccount WHERE id = ?1")
    public SavingsAccount findById(int id);

    @Query("FROM SavingsAccount WHERE IBAN = ?1")
    public SavingsAccount findByIBAN(Long iban);


}
