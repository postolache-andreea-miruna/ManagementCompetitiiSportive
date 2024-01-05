package com.example.managementcompetitii.services;

import com.example.managementcompetitii.dto.CompetitieDtoUpdate;
import com.example.managementcompetitii.exception.CompetitieNotFoundException;
import com.example.managementcompetitii.exception.DuplicateClubException;
import com.example.managementcompetitii.exception.DuplicateCompetitieException;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Competitie;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.repositories.CompetitieRepository;
import com.example.managementcompetitii.repositories.TipRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitieServiceImpl implements CompetitieService{
//-	GetAllCompetitii – ordonate descrescator dupa data de start  - DONE
//-	GetAllCompetitiiFilterYearStart,tip - DONE
//-	AddNewCompetitie - DONE
//-	UpateCompetitie     - data start,data final, taxa de participare - DONE
//-	DeleteCompetitie -DONE

    private final CompetitieRepository competitieRepository;
    private final TipRepository tipRepository;

    public CompetitieServiceImpl(CompetitieRepository competitieRepository, TipRepository tipRepository) {
        this.competitieRepository = competitieRepository;
        this.tipRepository = tipRepository;
    }

//    public Competitie saveCompetitie(Competitie competitie) //create
//    {
//        return competitieRepository.save(competitie);
//    }
    public Competitie saveCompetitie(Competitie competitie) //create
    {
        Optional<Competitie> compExistenta = competitieRepository.findByNume(competitie.getNume());
        compExistenta.ifPresent(c ->{
            throw new DuplicateCompetitieException();
        });
        return competitieRepository.save(competitie);
    }

    public List<Competitie> getAllCompetitii()//get all order desc by dataStart
    {
        return competitieRepository.findAllByOrderByDataStartDesc();
    }

    public List<Competitie> getCompetitiiYearStartTip(Integer anStart, String numeTip)
    {
        List<Competitie> competitii = new ArrayList<>();
        Tip tip = tipRepository.getByNume(numeTip);

        if (tip != null) {
            Long idTip = tip.getId();
            List<Competitie> allCompetitiiForTip = competitieRepository.findByTipId(idTip);
            allCompetitiiForTip.forEach(competitie -> {
                Integer anComp = competitie.getDataStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
                if(anComp.intValue() == anStart.intValue()) //fiind Integer tb sa te uiti la valoare salvata in ele.
                    competitii.add(competitie);
            });
        }
        return competitii;

    }

//    public Competitie updateCompetitie(Long id, CompetitieDtoUpdate competitie) throws Exception
//    {
//        Competitie comp = competitieRepository.getById(id);
//        if(comp != null){
//            comp.setDataStart(competitie.getDataStart());
//            comp.setDataFinal(competitie.getDataFinal());
//            comp.setTaxaParticipare(competitie.getTaxaParticipare());
//            return competitieRepository.save(comp);
//        }else
//        {
//            throw new Exception(String.format("Nu exista competitia cu id-ul dat: %s",id.toString()));
//        }
//    }
    public Competitie updateCompetitie(Long id, CompetitieDtoUpdate competitie)
    {
        Optional<Competitie> competitia = competitieRepository.findById(id);
        if(competitia.isPresent()){
            Competitie comp = competitieRepository.getById(id);
            comp.setDataStart(competitie.getDataStart());
            comp.setDataFinal(competitie.getDataFinal());
            comp.setTaxaParticipare(competitie.getTaxaParticipare());
            return competitieRepository.save(comp);
        }else
        {
            throw new CompetitieNotFoundException(id.longValue());
        }
    }

//    public void deleteById(Long id){
//        competitieRepository.deleteById(id);
//    }
    public void deleteById(Long id){
        Optional<Competitie> competitie = competitieRepository.findById(id);
        if(competitie.isPresent()){
            competitieRepository.deleteById(id);
        }else
        {
            throw new CompetitieNotFoundException(id.longValue());
        }

    }
}
