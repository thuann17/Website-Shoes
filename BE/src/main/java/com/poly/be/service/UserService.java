package com.poly.be.service;

import com.poly.be.model.User;
import com.poly.be.model.UserRole;
import com.poly.be.repository.UserRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  loadUserByUsername(username);
    }
    private UserDetails loadUser(String username){
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        System.out.println(username);
        if(user == null){
            return null;
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        List<UserRole> roles = user.getUserRole();
        for(UserRole role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
