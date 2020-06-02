package com.pe.edu.upc.petcare.service;

import com.pe.edu.upc.petcare.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    //
    Page<Account> getAllAccountsByRolId(Long rolId, Pageable pageable);
    Account getAccountByIdAndRolId(Long rolId,Long accountId);
    Account createAccountService(Long rolId,Account account);
    Account updateAccountService(Long rolId,Long accountId,Account accountRequest);
    ResponseEntity<?> deleteAccountService(Long rolId, Long accountId);
}
