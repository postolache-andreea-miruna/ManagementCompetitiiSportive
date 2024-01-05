package com.example.managementcompetitii.mapper;

import com.example.managementcompetitii.dto.CompetitieRequest;
import com.example.managementcompetitii.model.Competitie;
import org.springframework.stereotype.Component;

@Component
public class CompetitieMapper {
    public Competitie competitieRequestToCompetitie(CompetitieRequest competitieRequest){
        return new Competitie(competitieRequest.getNume(),competitieRequest.getDataStart(),competitieRequest.getDataFinal(),
                competitieRequest.getTaxaParticipare(),competitieRequest.getTip());
    }
}
