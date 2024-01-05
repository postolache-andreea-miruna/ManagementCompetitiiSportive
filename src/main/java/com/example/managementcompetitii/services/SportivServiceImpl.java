package com.example.managementcompetitii.services;

import com.example.managementcompetitii.exception.AntrenorNotFoundException;
import com.example.managementcompetitii.exception.ClubNotFoundException;
import com.example.managementcompetitii.exception.DuplicateSportivException;
import com.example.managementcompetitii.exception.SportivNotFoundException;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Sportiv;
import com.example.managementcompetitii.repositories.AntrenorRepository;
import com.example.managementcompetitii.repositories.ClubRepository;
import com.example.managementcompetitii.repositories.SportivRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SportivServiceImpl implements SportivService{
    private final SportivRepository sportivRepository;
    private final AntrenorRepository antrenorRepository;
    private final ClubRepository clubRepository;

    public SportivServiceImpl(SportivRepository sportivRepository, AntrenorRepository antrenorRepository
            , ClubRepository clubRepository) {
        this.sportivRepository = sportivRepository;
        this.antrenorRepository = antrenorRepository;
        this.clubRepository = clubRepository;
    }
//-	GetSportiviByIdClub -DONE
//-	GetSportiviByIdAntrenor - DONE
//-	AddNewSportiv - DONE
//-	UpdateSportiv - DONE
//public List<Sportiv> getSportiviByIdAntrenor(Long idAntrenor)
//{
//    List<Sportiv> sportiviIdAntrenor = new ArrayList<>();
//    List<Sportiv> sportivi = sportivRepository.findAll();
//
//    sportivi.forEach(sportiv -> {
//        if(sportiv.getAntrenor().getId() == idAntrenor.intValue())
//            sportiviIdAntrenor.add(sportiv);
//    });
//    return sportiviIdAntrenor;
//}
    public List<Sportiv> getSportiviByIdAntrenor(Long idAntrenor)
    {
        Optional<Antrenor> antrenor = antrenorRepository.findById(idAntrenor.longValue());
        if(antrenor.isPresent()){

        List<Sportiv> sportiviIdAntrenor = new ArrayList<>();
        List<Sportiv> sportivi = sportivRepository.findAll();

        sportivi.forEach(sportiv -> {
            if(sportiv.getAntrenor().getId() == idAntrenor.intValue())
                sportiviIdAntrenor.add(sportiv);
        });
        return sportiviIdAntrenor;}
        else {
            throw new AntrenorNotFoundException(idAntrenor.longValue());
        }
    }

    public List<Sportiv> getSportiviByIdClub(Long idClub)
    {
        Optional<Club> club = clubRepository.findById(idClub);
        if(club.isPresent()){
        List<Sportiv> sportiviIdClub = new ArrayList<>();
        List<Sportiv> sportivi = sportivRepository.findAll();

        sportivi.forEach(sportiv -> {
            if(sportiv.getAntrenor().getClub().getId() == idClub.intValue())
                sportiviIdClub.add(sportiv);
        });
        return sportiviIdClub;}
        else {
            throw new ClubNotFoundException(idClub);
        }
    }
//    public Sportiv saveSportiv(Sportiv sportiv) //create
//    {
//        return sportivRepository.save(sportiv);
//    }
    public Sportiv saveSportiv(Sportiv sportiv) //create
    {
        Optional<Sportiv> sportivExistent = sportivRepository.findByNrLegitimatie(sportiv.getNrLegitimatie());
        sportivExistent.ifPresent(s -> {
            throw new DuplicateSportivException();
        });
        return sportivRepository.save(sportiv);
    }
    public Sportiv updateSportiv(Long id, Sportiv sportiv)
    {
        Optional<Sportiv> sportivul = sportivRepository.findById(id);
        if(sportivul.isPresent()){
            Sportiv sp = sportivRepository.getById(id);
            sp.setNume(sportiv.getNume());
            sp.setPrenume(sportiv.getPrenume());
            sp.setGen(sportiv.getGen());
            sp.setAnNastere(sportiv.getAnNastere());
            sp.setNrLegitimatie(sportiv.getNrLegitimatie());
            sp.setAntrenor(sportiv.getAntrenor());
            sp.setIndemnizatie(sportiv.getIndemnizatie());
            return sportivRepository.save(sp);
        }else
        {
            throw new SportivNotFoundException(id);
        }
    }
//    public Sportiv updateSportiv(Long id, Sportiv sportiv) throws Exception
//    {
//        Sportiv sp = sportivRepository.getById(id);
//        if(sp != null){
//            sp.setNume(sportiv.getNume());
//            sp.setPrenume(sportiv.getPrenume());
//            sp.setGen(sportiv.getGen());
//            sp.setAnNastere(sportiv.getAnNastere());
//            sp.setNrLegitimatie(sportiv.getNrLegitimatie());
//            sp.setAntrenor(sportiv.getAntrenor());
//            sp.setIndemnizatie(sportiv.getIndemnizatie());
//            return sportivRepository.save(sp);
//        }else
//        {
//            throw new Exception(String.format("Nu exista antrenorul cu id-ul dat: %s",id.toString()));
//        }
//    }

}
