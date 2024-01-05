package com.example.managementcompetitii.mapper;

import com.example.managementcompetitii.dto.ProbaRequest;
import com.example.managementcompetitii.model.Proba;
import org.springframework.stereotype.Component;

@Component
public class ProbaMapper {
    public Proba probaRequestToProba(ProbaRequest probaRequest){
        return new Proba(probaRequest.getNume());
    }
}
