package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.ATMCard;
import org.kodluyoruz.mybank.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    @Query("FROM CreditCard WHERE id = ?1")
    public CreditCard findById2(Long id);

}
