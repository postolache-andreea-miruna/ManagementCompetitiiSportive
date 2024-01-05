package com.example.managementcompetitii.services;

import com.example.managementcompetitii.model.Tip;

import java.util.List;

public interface TipService {
    Tip saveTip(Tip tip);
    List<Tip> getAllTipuri();
}
