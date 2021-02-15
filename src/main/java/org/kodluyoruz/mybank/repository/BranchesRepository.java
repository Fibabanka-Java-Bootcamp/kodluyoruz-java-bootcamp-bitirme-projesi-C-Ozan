package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchesRepository extends JpaRepository<Branches, Integer> {
}
