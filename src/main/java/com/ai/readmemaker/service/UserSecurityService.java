package com.ai.readmemaker.service;

import com.ai.readmemaker.domain.User;
import com.ai.readmemaker.repository.UserRepository;
import com.ai.readmemaker.user.CustomUserDetails;
import com.ai.readmemaker.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called with email: " + email);

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(email + " 사용자를 찾을 수 없습니다.");
        }

        User user = userOptional.get();
        System.out.println("User found: " + user.getEmail());

        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(user.getEmail())) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        System.out.println("Authorities granted: " + authorities);

        CustomUserDetails customUserDetails = new CustomUserDetails(user.getEmail(), user.getPassword(), user.getNickname(), authorities);
        System.out.println("CustomUserDetails created: " + customUserDetails.getEmail());
        System.out.println("customUserDetails.getNickname() = " + customUserDetails.getNickname());
        return customUserDetails;
    }
}
