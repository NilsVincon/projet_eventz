package com.epf.eventz.security;

import com.epf.eventz.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class JwtCleaner {

    private final JwtService jwtService;

    public JwtCleaner(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Scheduled(fixedRate = 1000*15)
    @Transactional
    public void cleanupInactifJwtTokens() {
        jwtService.supprimerJwtInactifs();
    }

}
