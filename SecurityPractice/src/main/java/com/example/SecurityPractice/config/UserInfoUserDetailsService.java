package com.example.SecurityPractice.config;

import com.example.SecurityPractice.model.UserInfo;
import com.example.SecurityPractice.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfoOptional=userInfoRepository.findByName(username);

        return userInfoOptional.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("No user found"));
    }
}
