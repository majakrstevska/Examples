package mk.ukim.finki.wp.kol2023.g1.service.impl;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    private final PlayerRepository playerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = this.playerRepository.findByName(username);

        if ("user".equals(username)) {
            return User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user")) // Encoded password: "user"
                    .roles("USER")
                    .build();
        } else if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin")) // Encoded password: "admin"
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
    }



