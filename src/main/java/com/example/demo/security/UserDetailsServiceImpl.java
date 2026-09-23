package com.example.demo.security;

import com.example.demo.entity.user.User;
import com.example.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
  public UserDetails loadUserByUsername(String username){
      User user = userRepository.findByEmail(username).orElseThrow(()->
              new UsernameNotFoundException(" Нет такого email! "));
      UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(user);
      return userDetailsAdapter;
  }
}
