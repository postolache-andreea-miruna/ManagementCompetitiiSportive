package com.example.managementcompetitii.services;

import com.example.managementcompetitii.dto.*;
import com.example.managementcompetitii.exception.*;
import com.example.managementcompetitii.model.*;
import com.example.managementcompetitii.repositories.CompetitieRepository;
import com.example.managementcompetitii.repositories.ParticipaRepository;
import com.example.managementcompetitii.repositories.ProbaRepository;
import com.example.managementcompetitii.repositories.SportivRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipaServiceImpl implements ParticipaService{
    private final ParticipaRepository participaRepository;
    private final CompetitieRepository competitieRepository;
    private final ProbaRepository probaRepository;
    private final SportivRepository sportivRepository;
    public ParticipaServiceImpl(ParticipaRepository participaRepository, CompetitieRepository competitieRepository,
                                ProbaRepository probaRepository, SportivRepository sportivRepository) {
        this.participaRepository = participaRepository;
        this.competitieRepository = competitieRepository;
        this.probaRepository = probaRepository;
        this.sportivRepository = sportivRepository;
    }

    //-	GetRezultateBySportivId  - nume competitie, nume proba, timp, loc - DONE
    //-	GetRezultateBySportivIdCompetitieId – se afiseaza nume proba, timp si loc - DONE
    //-	GetRezultateByCompetitieId – grupate dupa proba (nrLegitimatie, nume, prenume sp, timp, loc, nume proba) - DONE
    //-	GetBestOfBySportivId - DONE
    //-	AddNewParticipare - DONE
    //-	UpdateParticipare - DONE
    //-	DeleteParticipare – neh maybe? - DONE

    public List<RezultateDtoComp> getRezultateByCompetitieId(Long competitieId)
    {
        Optional<Competitie> competitie = competitieRepository.findById(competitieId);
        if(competitie.isPresent()) {
            List<RezultateDtoComp> rezultate = new ArrayList<>();
            participaRepository.findAll().stream()
                    .filter(p -> p.getIdCompetitie() == competitieId.longValue())
                    .forEach(p -> {
                        RezultateDtoComp rezultateDtoComp = new RezultateDtoComp();
                        rezultateDtoComp.setNrLegitimatie(p.getSportiv().getNrLegitimatie());
                        rezultateDtoComp.setNumeSportiv(p.getSportiv().getNume());
                        rezultateDtoComp.setPrenumeSportiv(p.getSportiv().getPrenume());
                        rezultateDtoComp.setTimp(p.getTimp());
                        rezultateDtoComp.setNumeProba(p.getProba().getNume());
                        rezultateDtoComp.setLocClasament(p.getLocClasament());
                        rezultate.add(rezultateDtoComp);
                    });
            rezultate.sort(Comparator.comparing(RezultateDtoComp::getNumeProba).thenComparing(RezultateDtoComp::getLocClasament));
            return rezultate;
        }
        else{
            throw new CompetitieNotFoundException(competitieId);
        }
    }

public List<RezultateDtoBestOf> getBestOfBySportivId(Long sportivId) {
   Optional<Sportiv> sportiv = sportivRepository.findById(sportivId);
   if(sportiv.isPresent()){
    List<RezultateDtoBestOf> rezultate = new ArrayList<>();
    participaRepository.findAll()
            .stream()
            .filter(p -> p.getIdSportiv() == sportivId.longValue())
            .collect(Collectors.groupingBy(p -> p.getProba().getNume()))
            .values()
            .forEach(participas -> {
                Optional<Participa> minParticipa = participas.stream()
                        .min(Comparator.comparing(Participa::getTimp));

                minParticipa.ifPresent(rezultateBestOf -> {
                    RezultateDtoBestOf rezultateDtoBestOf = new RezultateDtoBestOf();
                    rezultateDtoBestOf.setNumeProba(rezultateBestOf.getProba().getNume());
                    rezultateDtoBestOf.setNumeCompetitie(rezultateBestOf.getCompetitie().getNume());
                    rezultateDtoBestOf.setDataStart(rezultateBestOf.getCompetitie().getDataStart());
                    rezultateDtoBestOf.setDataFinal(rezultateBestOf.getCompetitie().getDataFinal());
                    rezultateDtoBestOf.setTimp(rezultateBestOf.getTimp());
                    rezultate.add(rezultateDtoBestOf);
                });
            });

           return rezultate;
    }
   else {
       throw new SportivNotFoundException(sportivId);
   }
}


    public List<RezultateDtoSpComp> getRezultateBySportivIdCompetitieId(Long sportivId, Long competitieId) {
        Optional<Sportiv> sportiv = sportivRepository.findById(sportivId);
        Optional<Competitie> competitie = competitieRepository.findById(competitieId);
        if (sportiv.isPresent() & competitie.isPresent()) {
            List<RezultateDtoSpComp> rezultate = new ArrayList<>();
            participaRepository.findAll().forEach(participa -> {
                if (participa.getIdSportiv() == sportivId.longValue() & participa.getIdCompetitie() == competitieId.longValue()) {
                    Optional<Proba> proba = probaRepository.findById(participa.getIdProba());
                    if (proba.isPresent()) {
                        RezultateDtoSpComp rezultateDtoSpComp = new RezultateDtoSpComp();
                        rezultateDtoSpComp.setNumeProba(proba.get().getNume());
                        rezultateDtoSpComp.setTimp(participa.getTimp());
                        rezultateDtoSpComp.setLocClasament(participa.getLocClasament());
                        rezultate.add(rezultateDtoSpComp);
                    }
                }
            });
            return rezultate;
        }else {
            throw new SportivAndCompetitieNotFoundException(sportivId,competitieId);
        }
    }
    public List<RezultateDtoSp> getRezultateBySportivId(Long sportivId) //nume competitie, nume proba, timp, loc
    {
        Optional<Sportiv> sportiv = sportivRepository.findById(sportivId);
        if(sportiv.isPresent()) {
            List<RezultateDtoSp> rezultate = new ArrayList<>();
            participaRepository.findAll().forEach(participa -> {
                if (participa.getIdSportiv() == sportivId.longValue()) {
                    Optional<Competitie> competitie = competitieRepository.findById(participa.getIdCompetitie());
                    Optional<Proba> proba = probaRepository.findById(participa.getIdProba());
                    if (competitie.isPresent() & proba.isPresent()) {
                        String numeCompetitie = competitie.get().getNume();
                        String numeProba = proba.get().getNume();
                        RezultateDtoSp rezultateDtoSp = new RezultateDtoSp();
                        rezultateDtoSp.setNumeCompetitie(numeCompetitie);
                        rezultateDtoSp.setNumeProba(numeProba);
                        rezultateDtoSp.setTimp(participa.getTimp());
                        rezultateDtoSp.setLocClasament(participa.getLocClasament());
                        rezultate.add(rezultateDtoSp);
                    }
                }
            });
            return rezultate;
        }else {
            throw new SportivNotFoundException(sportivId);
        }
    }
    public Participa saveParticipa(Participa participa) //create
    {
        ParticipaId participaId = new ParticipaId(participa.getIdSportiv(),participa.getIdProba(),participa.getIdCompetitie());
        Optional<Participa> participaExistent = participaRepository.findById(participaId);
        participaExistent.ifPresent(p -> {
            throw new DuplicateParticipaException();
        });
        return participaRepository.save(participa);
    }
//    public Participa updateParticipa(ParticipaId id, ParticipaDtoUpdate participaDtoUpdate) throws Exception
//    {
//
//        Participa participa = participaRepository.getById(id);
//        if(participa != null){
//            participa.setTimp(participaDtoUpdate.getTimp());
//            participa.setLocClasament(participaDtoUpdate.getLocClasament());
//            return participaRepository.save(participa);
//        }else
//        {
//            throw new Exception(String.format("Nu exista participare cu id-ul dat: %s",id.toString()));
//        }
//    }
public Participa updateParticipa(ParticipaId id, ParticipaDtoUpdate participaDtoUpdate)
{
    Optional<Participa> particip = participaRepository.findById(id);
    if(particip.isPresent()){
        Participa participa = participaRepository.getById(id);
        participa.setTimp(participaDtoUpdate.getTimp());
        participa.setLocClasament(participaDtoUpdate.getLocClasament());
        return participaRepository.save(participa);
    }else
    {
        throw new ParticipaNotFoundException(id);
    }
}
    public void deleteParticipaById(ParticipaId id){
        Optional<Participa> particip = participaRepository.findById(id);
        if(particip.isPresent()){
        participaRepository.deleteById(id);
    }else {
            throw new ParticipaNotFoundException(id);
        }
    }
}
