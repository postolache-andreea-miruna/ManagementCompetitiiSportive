package com.example.managementcompetitii.mapper;

import com.example.managementcompetitii.dto.AntrenorRequest;
import com.example.managementcompetitii.dto.SportivRequest;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Sportiv;
import org.springframework.stereotype.Component;

@Component
public class SportivMapper {
    public Sportiv sportivRequestToSportiv(SportivRequest sportivRequest){
        return new Sportiv(sportivRequest.getNume(), sportivRequest.getPrenume(), sportivRequest.getGen(),
                sportivRequest.getAnNastere(), sportivRequest.getIndemnizatie(), sportivRequest.getNrLegitimatie(),
                sportivRequest.getAntrenor());
    }
}
