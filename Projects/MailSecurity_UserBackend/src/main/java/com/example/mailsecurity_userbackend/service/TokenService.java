package com.example.mailsecurity_userbackend.service;



import com.example.mailsecurity_userbackend.model.User;
import com.example.mailsecurity_userbackend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private final JwtEncoder encoder;
    private final UserRepository userRepository;

    public TokenService(JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.encoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    public String generateToken(Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).get();
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("id", user.getId())
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
