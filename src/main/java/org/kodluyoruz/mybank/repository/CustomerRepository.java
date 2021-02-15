package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Customers;
import org.kodluyoruz.mybank.model.DrawingAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

    @Query("FROM Customers WHERE id = ?1")
    public Customers findById2(int id);


}
