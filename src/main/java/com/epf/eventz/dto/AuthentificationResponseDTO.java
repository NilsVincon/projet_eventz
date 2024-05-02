package com.epf.eventz.dto;

import lombok.Data;

@Data
public class AuthentificationResponseDTO {
    private String accesToken;
    private String tokenType = "Bearer ";

    public AuthentificationResponseDTO(String accesToken){
        this.accesToken=accesToken;
    }
}
