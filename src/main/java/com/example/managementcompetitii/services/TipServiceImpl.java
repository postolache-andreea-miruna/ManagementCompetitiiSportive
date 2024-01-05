package com.example.managementcompetitii.services;

import com.example.managementcompetitii.exception.DuplicateTipException;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.repositories.TipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipServiceImpl implements TipService{
    private final TipRepository tipRepository;

    public TipServiceImpl(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public Tip saveTip(Tip tip) //create
    {
        Optional<Tip> tipExistent = tipRepository.findByNume(tip.getNume());
        tipExistent.ifPresent(t -> {
            throw new DuplicateTipException();
        });
        return tipRepository.save(tip);
    }

    public List<Tip> getAllTipuri()//get all
    {
        return tipRepository.findAll();
    }
}
