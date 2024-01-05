package com.example.managementcompetitii.mapper;

import com.example.managementcompetitii.dto.ClubRequest;
import com.example.managementcompetitii.model.Club;
import org.springframework.stereotype.Component;

@Component
public class ClubMapper {
    public Club clubRequestToClub(ClubRequest clubRequest){
        return new Club(clubRequest.getNume(), clubRequest.getNrInregistrare());
    }
}
