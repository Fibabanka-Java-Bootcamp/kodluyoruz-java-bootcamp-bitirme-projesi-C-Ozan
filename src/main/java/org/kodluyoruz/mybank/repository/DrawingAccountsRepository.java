package org.kodluyoruz.mybank.repository;


import org.kodluyoruz.mybank.model.DrawingAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrawingAccountsRepository extends JpaRepository<DrawingAccounts, Integer> {

    @Query("FROM DrawingAccounts WHERE customer_id = ?1")
    public List<DrawingAccounts> findByCustomerId(int id);

    @Query("FROM DrawingAccounts WHERE iban = ?1")
    public DrawingAccounts findByIBAN(Long iban);

    @Query("FROM DrawingAccounts WHERE id = ?1")
    public DrawingAccounts findById2(int id);


}
