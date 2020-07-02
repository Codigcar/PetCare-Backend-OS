package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.Account;
import com.pe.edu.upc.petcare.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Implement Repository-based User Store
        Account accountDb = accountRepository.getAccountByUsername(username);

        String defaultPassword = passwordEncoder.encode(accountDb.getPassword());
        if(accountDb.getUser().equals(username)) {
            return new User(accountDb.getUser(), defaultPassword, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException("User not found with username " + username);
    }

}
