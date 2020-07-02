package com.pe.edu.upc.petcare.repository;

import com.pe.edu.upc.petcare.model.Account;
import com.pe.edu.upc.petcare.model.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    //
    Page<Account> findByRolId(Long rolId, Pageable pageable);
    Optional<Account> findByIdAndRolId(Long accountId, Long rolId);

    @Query("SELECT q FROM Account q WHERE q.user =?1")
    Account getAccountByUsername(String username);
}
