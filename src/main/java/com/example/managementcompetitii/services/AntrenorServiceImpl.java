package com.example.managementcompetitii.services;

import com.example.managementcompetitii.exception.AntrenorNotFoundException;
import com.example.managementcompetitii.exception.ClubNotFoundException;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Competitie;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.repositories.AntrenorRepository;
import com.example.managementcompetitii.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AntrenorServiceImpl implements AntrenorService{
    private final AntrenorRepository antrenorRepository;
    private final ClubRepository clubRepository;

    public AntrenorServiceImpl(AntrenorRepository antrenorRepository, ClubRepository clubRepository) {
        this.antrenorRepository = antrenorRepository;
        this.clubRepository = clubRepository;
    }
//-	GetAntrenoriByIdClub -DONE
//-	GetAntrenoriFilterGen_Exp - DONE
//-	GetAntrenoriAscNume - DONE
//-	GetAntrenoriDescNume - DONE
//-	AddNewAntrenor - DONE
//-	UpdateAntrenor - DONE

    public List<Antrenor> getAntrenoriAscNume()
    {
        return antrenorRepository.findAllByOrderByNumeAsc();
    }

    public List<Antrenor> getAntrenoriDescNume()
    {
        return antrenorRepository.findAllByOrderByNumeDesc();
    }
//    public List<Antrenor> getAntrenoriFilterGenExperienta(String gen, Integer aniExperienta)
//    {
//        List<Antrenor> antrenori = new ArrayList<>();
//        List<Antrenor> antrenoriExp = antrenorRepository.findByAniExperienta(aniExperienta);
//        antrenoriExp.forEach(antrenor -> {
//            if(antrenor.getGen().equals(gen))
//                antrenori.add(antrenor);
//        });
//        return antrenori;
//    }
    public List<Antrenor> getAntrenoriFilterGenExperienta(String gen, Integer aniExperienta)
    {
        return antrenorRepository.findByAniExperientaAndGen(aniExperienta, gen);
    }
    public List<Antrenor> getAntrenoriByIdClub(Long idClub)
    {
        Optional<Club> club = clubRepository.findById(idClub.longValue());
        if(club.isPresent()){

        List<Antrenor> antrenoriIdClub = new ArrayList<>();
        List<Antrenor> antrenori = antrenorRepository.findAll();

        antrenori.forEach(antrenor -> {
            if(antrenor.getClub().getId() == idClub.intValue())
                antrenoriIdClub.add(antrenor);
        });
        return antrenoriIdClub;}
        else {
            throw new ClubNotFoundException(idClub.longValue());
        }
    }

    public Antrenor saveAntrenor(Antrenor antrenor) //create
    {
        return antrenorRepository.save(antrenor);
    }

//    public Antrenor updateAntrenor(Long id, Antrenor antrenor) throws Exception
//    {
//        Antrenor antr = antrenorRepository.getById(id);
//        if(antr != null){
//            antr.setNume(antrenor.getNume());
//            antr.setPrenume(antrenor.getPrenume());
//            antr.setAniExperienta(antrenor.getAniExperienta());
//            antr.setSalariu(antrenor.getSalariu());
//            antr.setGen(antrenor.getGen());
//            antr.setClub(antrenor.getClub());
//            return antrenorRepository.save(antr);
//        }else
//        {
//            throw new Exception(String.format("Nu exista antrenorul cu id-ul dat: %s",id.toString()));
//        }
//    }
    public Antrenor updateAntrenor(Long id, Antrenor antrenor)
    {
        Optional<Antrenor> antrenorul = antrenorRepository.findById(id.longValue());
        if(antrenorul.isPresent()){
            Antrenor antr = antrenorRepository.getById(id);
            antr.setNume(antrenor.getNume());
            antr.setPrenume(antrenor.getPrenume());
            antr.setAniExperienta(antrenor.getAniExperienta());
            antr.setSalariu(antrenor.getSalariu());
            antr.setGen(antrenor.getGen());
            antr.setClub(antrenor.getClub());
            return antrenorRepository.save(antr);
        }else
        {
            throw new AntrenorNotFoundException(id.longValue());
        }
    }
}

