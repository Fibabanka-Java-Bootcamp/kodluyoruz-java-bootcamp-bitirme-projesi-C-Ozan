package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Customers;
import org.kodluyoruz.mybank.model.DrawingAccounts;
import org.kodluyoruz.mybank.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {

    @Query("FROM Expenses WHERE  credit_card_id = ?1")
    public List<Expenses> findByCreditCardId(Long credit_card_id);

    @Query("delete from Expenses WHERE credit_card_id = ?1")
    public List<Expenses> deleteExpensesByCredit_card_id(@Param("credit_card_id") Long credit_card_id);


}
