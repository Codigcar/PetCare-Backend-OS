package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Account;
import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.resource.AccountResource;
import com.pe.edu.upc.petcare.resource.PetResource;
import com.pe.edu.upc.petcare.resource.save.SaveAccountResource;
import com.pe.edu.upc.petcare.resource.save.SavePetResource;
import com.pe.edu.upc.petcare.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    //
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountService accountService;

    @GetMapping("/roles/{rolesId}/accounts")
    public Page<AccountResource> getAllAccountsByRolId(@PathVariable(name = "rolesId")Long rolesId,
                                                        Pageable pageable){
        Page<Account> accountPage=accountService.getAllAccountsByRolId(rolesId,pageable);
        List<AccountResource> resources=accountPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/roles/{rolesId}/accounts/{accountId}")
    public AccountResource getAccountByIdAndRolId(@PathVariable(name = "rolesId")Long rolId,
                                               @PathVariable(name = "accountId")Long accountId){
        return convertToResource(accountService.getAccountByIdAndRolId(rolId,accountId));
    }


    @PostMapping("/roles/{rolesId}/accounts")
    public AccountResource createAccount(@PathVariable(name = "rolesId")Long rolesId,
                                 @Valid @RequestBody SaveAccountResource resource){
        return convertToResource(accountService.createAccountService(rolesId,convertToEntity(resource)));
    }

    @PutMapping("/roles/{rolesId}/accounts/{accountId}")
    public AccountResource updateAccount(@PathVariable(name = "rolesId")Long rolesId,
                                 @PathVariable(name = "accountId")Long accountId,
                                 @Valid @RequestBody SaveAccountResource resource){
        return convertToResource(accountService.updateAccountService(rolesId,accountId,convertToEntity(resource)));
    }

    @DeleteMapping("/roles/{rolesId}/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "rolesId")Long rolesId,
                                       @PathVariable(name = "accountId")Long accountId){
        return accountService.deleteAccountService(rolesId,accountId);
    }

    private Account convertToEntity(SaveAccountResource resource) {
        return mapper.map(resource, Account.class);
    }

    private AccountResource convertToResource(Account entity) {
        return mapper.map(entity, AccountResource.class);
    }
}
