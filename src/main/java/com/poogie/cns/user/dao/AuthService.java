package com.poogie.cns.user.dao;

import com.poogie.cns.security.JwtDto;
import com.poogie.cns.security.JwtProvider;
import com.poogie.cns.user.domain.UserEntity;
import com.poogie.cns.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public JwtDto signIn(UserRequestDto.SignIn req) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                req.getEmail(),
                req.getPassword()
        );

        Authentication auth = authenticationManager.authenticate(
                authToken
        );

        if(auth.isAuthenticated()) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(req.getEmail());

            return JwtDto.builder()
                    .accessJwt(jwtProvider.create(userDetails))
                    .user(userRepository.findByEmail(req.getEmail()))
                    .build();
        }

        return JwtDto.builder()
                .accessJwt(null)
                .user(null)
                .build();
    }

    public UserEntity signUp(UserRequestDto.SignUp req) {
        UserEntity user = UserEntity.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .name(req.getName())
                .build();
        return userRepository.save(user);
    }
}
