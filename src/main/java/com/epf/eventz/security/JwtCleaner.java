package com.epf.eventz.security;

import com.epf.eventz.dao.JwtDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
public class JwtCleaner {

    @Autowired
    private JwtDAO jwtDAO;

    @Scheduled(fixedRate = 180000)
    @Transactional
    public void cleanupExpiredJwtTokens() {
        Date currentDate = new Date();
        jwtDAO.deleteByExpireDateBefore(currentDate);
    }

    @Scheduled(fixedRate = 300000)
    @Transactional
    public void cleanupInactifJwtTokens() {
        jwtDAO.deleteInactifJwt();
    }

}
