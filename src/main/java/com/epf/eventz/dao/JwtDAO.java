package com.epf.eventz.dao;


import com.epf.eventz.model.Jwt;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface JwtDAO extends CrudRepository<Jwt,Long> {
    <Optional> Jwt findByValeur(String valeur);
    void deleteByExpireDateBefore(Date date);
    <Optional> Jwt findByUsername(String username);
    @Modifying
    @Query("DELETE FROM Jwt j WHERE j.actif = false")
    void deleteInactifJwt();

    @Modifying
    @Query("UPDATE Jwt SET actif = :actif WHERE username = :username")
    void setLogoutStatus(@Param("username") String username, @Param("actif") boolean actif);
}