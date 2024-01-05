package com.example.managementcompetitii.mapper;

import com.example.managementcompetitii.dto.TipRequest;
import com.example.managementcompetitii.model.Tip;
import org.springframework.stereotype.Component;

@Component
public class TipMapper {
    public Tip tipRequestToTip(TipRequest tipRequest){
        return new Tip(tipRequest.getNume());
    }
}
