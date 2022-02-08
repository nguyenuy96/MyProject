package com.app.service;

import com.app.entity.Account;
import com.app.entity.Role;
import com.app.repository.AccountRepository;
import com.app.repository.RoleRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(""));
        Role role = roleRepository.findById(account.getId()).orElseThrow(() -> new UsernameNotFoundException(""));
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        grantedAuthoritiesList.add(grantedAuthority);
        return new User(account.getUserName(), account.getSalt(), grantedAuthoritiesList);
    }
}
