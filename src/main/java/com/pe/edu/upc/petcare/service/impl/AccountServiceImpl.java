package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.exception.ResourceNotFoundException;
import com.pe.edu.upc.petcare.model.Account;
import com.pe.edu.upc.petcare.repository.AccountRepository;
import com.pe.edu.upc.petcare.repository.RolRepository;
import com.pe.edu.upc.petcare.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    //
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Page<Account> getAllAccountsByRolId(Long rolId, Pageable pageable) {
        return accountRepository.findByRolId(rolId,pageable);
    }

    @Override
    public Account getAccountByIdAndRolId(Long rolId, Long accountId) {
        return accountRepository.findByIdAndRolId(accountId,rolId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Account not found with Id"+accountId+
                                "and RolId"+rolId));
    }

    @Override
    public Account createAccountService(Long rolId, Account account) {
        return rolRepository.findById(rolId).map(rol-> {
            account.setRol(rol);
            return accountRepository.save(account);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Rol" + "Id" + rolId));
    }

    @Override
    public Account updateAccountService(Long rolId, Long accountId, Account accountRequest) {
        if(!rolRepository.existsById(rolId))
            throw new ResourceNotFoundException("Rol","Id",rolId);

        return accountRepository.findById(rolId).map(account -> {
            account.setUser(accountRequest.getUser());
            account.setPassword(accountRequest.getPassword());

            return accountRepository.save(account);
        }).orElseThrow(()->new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public ResponseEntity<?> deleteAccountService(Long rolId, Long accountId) {
        return accountRepository.findByIdAndRolId(accountId,rolId).map(account -> {
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "Account not found with Id"+accountId+"and RolId"+rolId));
    }
}
