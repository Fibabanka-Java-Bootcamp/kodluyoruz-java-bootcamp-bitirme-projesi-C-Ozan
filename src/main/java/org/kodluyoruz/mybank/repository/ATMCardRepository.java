package org.kodluyoruz.mybank.repository;


import org.kodluyoruz.mybank.model.ATMCard;
import org.kodluyoruz.mybank.model.DrawingAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMCardRepository extends JpaRepository<ATMCard, Integer> {


    @Query("FROM ATMCard WHERE id = ?1")
    public ATMCard findById2(int id);

}
