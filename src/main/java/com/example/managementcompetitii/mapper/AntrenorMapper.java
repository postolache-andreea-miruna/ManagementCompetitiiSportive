package com.example.managementcompetitii.mapper;

import com.example.managementcompetitii.dto.AntrenorRequest;
import com.example.managementcompetitii.model.Antrenor;
import org.springframework.stereotype.Component;

@Component
public class AntrenorMapper {
    public Antrenor antrenorRequestToAntrenor(AntrenorRequest antrenorRequest){
        return new Antrenor(antrenorRequest.getNume(),antrenorRequest.getPrenume(),antrenorRequest.getAniExperienta(),antrenorRequest.getSalariu(),
                antrenorRequest.getGen(),antrenorRequest.getClub());
    }
}
