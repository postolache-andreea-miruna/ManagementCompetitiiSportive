package com.example.managementcompetitii.services;

import com.example.managementcompetitii.exception.DuplicateProbaException;
import com.example.managementcompetitii.exception.DuplicateTipException;
import com.example.managementcompetitii.model.Competitie;
import com.example.managementcompetitii.model.Proba;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.repositories.ProbaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProbaServiceImpl implements ProbaService{
    private final ProbaRepository probaRepository;

    public ProbaServiceImpl(ProbaRepository probaRepository) {
        this.probaRepository = probaRepository;
    }

//-	AddNewProba
//-	GetAllProbe
    public Proba saveProba(Proba proba) //create
    {
        Optional<Proba> probaExistent = probaRepository.findByNume(proba.getNume());
        probaExistent.ifPresent(p -> {
            throw new DuplicateProbaException();
        });
        return probaRepository.save(proba);
    }

    public List<Proba> getAllProbe()//get all
    {
        return probaRepository.findAll();
    }

}
