package com.example.managementcompetitii.services;
import com.example.managementcompetitii.dto.*;
import com.example.managementcompetitii.exception.*;
import com.example.managementcompetitii.model.*;
import com.example.managementcompetitii.repositories.CompetitieRepository;
import com.example.managementcompetitii.repositories.ParticipaRepository;
import com.example.managementcompetitii.repositories.ProbaRepository;
import com.example.managementcompetitii.repositories.SportivRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ParticipaServiceImplTest {
    @InjectMocks
    private ParticipaServiceImpl participaService;

    @Mock
    private ParticipaRepository participaRepository;
    @Mock
    private CompetitieRepository competitieRepository;
    @Mock
    private ProbaRepository probaRepository;
    @Mock
    private SportivRepository sportivRepository;

    @Test
    void whenParticipaAlreadyExists_saveParticipa_throwsDuplicateParticipaException() throws ParseException {
        //Arrange
        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Proba proba = new Proba();
        proba.setNume("400Liber");
        proba.setId(1);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        Participa participa = new Participa();
        participa.setIdCompetitie(1);
        participa.setIdProba(1);
        participa.setIdSportiv(1);
        participa.setLocClasament(1);
        participa.setTimp(30);
        participa.setCompetitie(competitie);
        participa.setProba(proba);
        participa.setSportiv(sportiv);

        when(participaRepository.findById(new ParticipaId(1,1,1)))
                .thenReturn(Optional.of(participa));

        //Act
        DuplicateParticipaException exception = assertThrows(DuplicateParticipaException.class,
                () -> participaService.saveParticipa(participa));

        //Assert
        assertEquals("Sportivul a fost deja inscris la aceasta proba din cadrul competitiei alese", exception.getMessage());
        verify(participaRepository, times(0)).save(participa);
    }

    @Test
    void whenParticipaDoesntExist_saveParticipa_savesTheParticipa() throws ParseException {
        //Arrange
        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Proba proba = new Proba();
        proba.setNume("400Liber");
        proba.setId(1);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        Participa participa = new Participa();
        participa.setIdCompetitie(1);
        participa.setIdProba(1);
        participa.setIdSportiv(1);
        participa.setLocClasament(1);
        participa.setTimp(30);
        participa.setCompetitie(competitie);
        participa.setProba(proba);
        participa.setSportiv(sportiv);


        when(participaRepository.findById(new ParticipaId(1,1,1)))
                .thenReturn(Optional.empty());

        Participa savedParticipa = new Participa();
        savedParticipa.setIdCompetitie(1);
        savedParticipa.setIdProba(1);
        savedParticipa.setIdSportiv(1);
        savedParticipa.setLocClasament(1);
        savedParticipa.setTimp(30);
        savedParticipa.setCompetitie(competitie);
        savedParticipa.setProba(proba);
        savedParticipa.setSportiv(sportiv);

        when(participaRepository.save(participa)).thenReturn(savedParticipa);

        //Act
        Participa result = participaService.saveParticipa(participa);

        //Assert
        assertNotNull(result);
        assertEquals(savedParticipa.getIdCompetitie(),result.getIdCompetitie());
        assertEquals(participa.getIdCompetitie(),result.getIdCompetitie());

        assertEquals(savedParticipa.getIdProba(),result.getIdProba());
        assertEquals(participa.getIdProba(),result.getIdProba());

        assertEquals(savedParticipa.getIdSportiv(),result.getIdSportiv());
        assertEquals(participa.getIdSportiv(),result.getIdSportiv());

        assertEquals(savedParticipa.getLocClasament(),result.getLocClasament());
        assertEquals(participa.getLocClasament(),result.getLocClasament());

        assertEquals(savedParticipa.getTimp(),result.getTimp());
        assertEquals(participa.getTimp(),result.getTimp());

        assertEquals(savedParticipa.getCompetitie(),result.getCompetitie());
        assertEquals(participa.getCompetitie(),result.getCompetitie());

        assertEquals(savedParticipa.getProba(),result.getProba());
        assertEquals(participa.getProba(),result.getProba());

        assertEquals(savedParticipa.getSportiv(),result.getSportiv());
        assertEquals(participa.getSportiv(),result.getSportiv());

        verify(participaRepository).findById(new ParticipaId(1,1,1));
        verify(participaRepository).save(participa);
    }
    @Test
    void whenParticipaDoesntExists_updateParticipa_throwsParticipaNotFoundException(){
        //Arrange
        ParticipaId participaId = new ParticipaId(1,1,1);
        ParticipaDtoUpdate participaDtoUpdate = new ParticipaDtoUpdate();
        participaDtoUpdate.setTimp(37);
        participaDtoUpdate.setLocClasament(5);


        when(participaRepository.findById(participaId))
                .thenReturn(Optional.empty());

        //Act
        ParticipaNotFoundException exception = assertThrows(ParticipaNotFoundException.class,
                () -> participaService.updateParticipa(participaId,participaDtoUpdate));

        //Assert
        assertEquals("Participarea cu id " + participaId + " nu exista", exception.getMessage());
        verify(participaRepository).findById(participaId);
    }
    @Test
    void whenParticipaExists_updateParticipa_returnsNewTheParticipa() throws ParseException {
        //Arrange
        ParticipaId participaId = new ParticipaId(1,1,1);
        ParticipaDtoUpdate participaDtoUpdate = new ParticipaDtoUpdate();
        participaDtoUpdate.setTimp(37);
        participaDtoUpdate.setLocClasament(5);

        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Proba proba = new Proba();
        proba.setNume("400Liber");
        proba.setId(1);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        Participa participaOld = new Participa();
        participaOld.setIdCompetitie(1);
        participaOld.setIdProba(1);
        participaOld.setIdSportiv(1);
        participaOld.setLocClasament(1);
        participaOld.setTimp(30);
        participaOld.setCompetitie(competitie);
        participaOld.setProba(proba);
        participaOld.setSportiv(sportiv);


        Participa participaNew = new Participa();
        participaNew.setIdCompetitie(1);
        participaNew.setIdProba(1);
        participaNew.setIdSportiv(1);
        participaNew.setLocClasament(participaDtoUpdate.getLocClasament());
        participaNew.setTimp(participaDtoUpdate.getTimp());
        participaNew.setCompetitie(competitie);
        participaNew.setProba(proba);
        participaNew.setSportiv(sportiv);

        when(participaRepository.findById(Mockito.any(ParticipaId.class)))
                .thenReturn(Optional.of(participaOld));

        when(participaRepository.getById(Mockito.any(ParticipaId.class)))
                .thenReturn(participaOld);

        when(participaRepository.save(participaOld)).thenReturn(participaNew);

        //Act
        Participa result = participaService.updateParticipa(participaId,participaDtoUpdate);

        //Assert
        assertNotNull(result);
        assertEquals(participaNew.getIdCompetitie(),result.getIdCompetitie());
        assertEquals(participaNew.getIdProba(),result.getIdProba());
        assertEquals(participaNew.getIdSportiv(),result.getIdSportiv());
        assertEquals(participaNew.getTimp(),result.getTimp());
        assertEquals(participaNew.getLocClasament(),result.getLocClasament());
        assertEquals(participaNew.getCompetitie(),result.getCompetitie());
        assertEquals(participaNew.getSportiv(),result.getSportiv());
        assertEquals(participaNew.getProba(),result.getProba());

        verify(participaRepository).findById(participaId);
        verify(participaRepository).getById(participaId);
        verify(participaRepository).save(participaOld);
    }

    @Test
    void whenParticipaDoesntExists_deleteParticipaById_throwsParticipaNotFoundException(){
        //Arrange
        ParticipaId participaId = new ParticipaId(1,1,1);
        when(participaRepository.findById(participaId))
                .thenReturn(Optional.empty());
        //Act
        ParticipaNotFoundException exception = assertThrows(ParticipaNotFoundException.class,
                () -> participaService.deleteParticipaById(participaId));

        //Assert
        assertEquals("Participarea cu id " + participaId + " nu exista", exception.getMessage());
    }

    @Test
    void whenParticipaExists_deleteParticipaById_deletesTheParticipa() throws ParseException {
        //Arrange
        ParticipaId participaId = new ParticipaId(1,1,1);
        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Proba proba = new Proba();
        proba.setNume("400Liber");
        proba.setId(1);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        Participa participa = new Participa();
        participa.setIdCompetitie(1);
        participa.setIdProba(1);
        participa.setIdSportiv(1);
        participa.setLocClasament(1);
        participa.setTimp(30);
        participa.setCompetitie(competitie);
        participa.setProba(proba);
        participa.setSportiv(sportiv);

        when(participaRepository.findById(participaId))
                .thenReturn(Optional.of(participa));

        //Act
        participaService.deleteParticipaById(participaId);

        //Assert
        verify(participaRepository).deleteById(participaId);
    }

    @Test
    void whenCompetitieDoesntExists_getRezultateByCompetitieId_throwsCompetitieNotFoundException(){
        //Arrange
        Long competitieId = 1L;
        when(competitieRepository.findById(competitieId))
                .thenReturn(Optional.empty());
        //Act
        CompetitieNotFoundException exception = assertThrows(CompetitieNotFoundException.class,
                () -> participaService.getRezultateByCompetitieId(competitieId));

        //Assert
        assertEquals("Competitia cu id " + competitieId + " nu exista", exception.getMessage());

    }

    @Test
    void whenCompetitieExists_getRezultateByCompetitieId_returnsTheRezultateByCompetitieId() throws ParseException {
        //Arrange
        Long competitieId = 1L;
        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        when(competitieRepository.findById(competitieId))
                .thenReturn(Optional.of(competitie));


        Proba proba = new Proba();
        proba.setNume("50Liber");
        proba.setId(1);

        Proba proba2 = new Proba();
        proba2.setNume("50Spate");
        proba2.setId(2);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        Participa participa1 = new Participa();
        participa1.setIdCompetitie(1);
        participa1.setIdProba(1);
        participa1.setIdSportiv(1);
        participa1.setLocClasament(1);
        participa1.setTimp(30);
        participa1.setCompetitie(competitie);
        participa1.setProba(proba);
        participa1.setSportiv(sportiv);

        Participa participa2 = new Participa();
        participa2.setIdCompetitie(1);
        participa2.setIdProba(2);
        participa2.setIdSportiv(1);
        participa2.setLocClasament(2);
        participa2.setTimp(40);
        participa2.setCompetitie(competitie);
        participa2.setProba(proba2);
        participa2.setSportiv(sportiv);

        List<Participa> participaList = new ArrayList<>();
        participaList.add(participa1);
        participaList.add(participa2);

        RezultateDtoComp rezultate1 = new RezultateDtoComp();
        rezultate1.setNrLegitimatie(participa1.getSportiv().getNrLegitimatie());
        rezultate1.setNumeSportiv(participa1.getSportiv().getNume());
        rezultate1.setPrenumeSportiv(participa1.getSportiv().getPrenume());
        rezultate1.setTimp(participa1.getTimp());
        rezultate1.setLocClasament(participa1.getLocClasament());
        rezultate1.setNumeProba(participa1.getProba().getNume());

        RezultateDtoComp rezultate2 = new RezultateDtoComp();
        rezultate2.setNrLegitimatie(participa2.getSportiv().getNrLegitimatie());
        rezultate2.setNumeSportiv(participa2.getSportiv().getNume());
        rezultate2.setPrenumeSportiv(participa2.getSportiv().getPrenume());
        rezultate2.setTimp(participa2.getTimp());
        rezultate2.setLocClasament(participa2.getLocClasament());
        rezultate2.setNumeProba(participa2.getProba().getNume());

        // private Integer nrLegitimatie;
        //    private String numeSportiv;
        //    private String prenumeSportiv;
        //    private Integer timp;
        //    private Integer locClasament;
        //    private String numeProba;

        List<RezultateDtoComp> rezultateList = new ArrayList<>();
        rezultateList.add(rezultate1);
        rezultateList.add(rezultate2);

        when(participaRepository.findAll()).thenReturn(participaList);

        //Act
        List<RezultateDtoComp> result = participaService.getRezultateByCompetitieId(competitieId);

        //Assert
        assertNotNull(result);

        assertEquals(2,result.size());

        assertEquals(rezultate1.getNumeProba(),result.get(0).getNumeProba());//proba este 50Liber si se ordoneaza dupa probe
        assertEquals(rezultate1.getNrLegitimatie(),result.get(0).getNrLegitimatie());
        assertEquals(rezultate1.getNumeSportiv(),result.get(0).getNumeSportiv());
        assertEquals(rezultate1.getPrenumeSportiv(),result.get(0).getPrenumeSportiv());
        assertEquals(rezultate1.getTimp(),result.get(0).getTimp());
        assertEquals(rezultate1.getLocClasament(),result.get(0).getLocClasament());

        assertEquals(rezultate2.getNumeProba(),result.get(1).getNumeProba());//50Spate
        assertEquals(rezultate2.getNrLegitimatie(),result.get(1).getNrLegitimatie());
        assertEquals(rezultate2.getNumeSportiv(),result.get(1).getNumeSportiv());
        assertEquals(rezultate2.getPrenumeSportiv(),result.get(1).getPrenumeSportiv());
        assertEquals(rezultate2.getTimp(),result.get(1).getTimp());
        assertEquals(rezultate2.getLocClasament(),result.get(1).getLocClasament());

        verify(competitieRepository).findById(competitieId);
        verify(participaRepository).findAll();
    }
    @Test
    void whenSportivDoesntExists_getRezultateBySportivId_throwsSportivNotFoundException(){
        //Arrange
        Long sportivId = 1L;
        when(sportivRepository.findById(sportivId))
                .thenReturn(Optional.empty());
        //Act
        SportivNotFoundException exception = assertThrows(SportivNotFoundException.class,
                () -> participaService.getRezultateBySportivId(sportivId));

        //Assert
        assertEquals("Sportivul cu id " + sportivId + " nu exista.", exception.getMessage());
    }

    @Test
    void whenSportivExists_getRezultateBySportivId_returnsTheRezultateBySportivId() throws ParseException {
        //Arrange
        Long sportivId = 1L;

        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Proba proba = new Proba();
        proba.setNume("50Liber");
        proba.setId(1);

        Proba proba2 = new Proba();
        proba2.setNume("50Spate");
        proba2.setId(2);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        when(sportivRepository.findById(sportivId))
                .thenReturn(Optional.of(sportiv));

        Sportiv sportiv1 = new Sportiv();
        sportiv1.setId(2);
        sportiv1.setNume("Spir");
        sportiv1.setPrenume("Marina");
        sportiv1.setGen("F");
        sportiv1.setAnNastere(1989);
        sportiv1.setNrLegitimatie(22);
        sportiv1.setIndemnizatie(200.5);
        sportiv1.setAntrenor(antrenor1);

        Participa participa1 = new Participa();
        participa1.setIdCompetitie(1);
        participa1.setIdProba(1);
        participa1.setIdSportiv(1);
        participa1.setLocClasament(1);
        participa1.setTimp(30);
        participa1.setCompetitie(competitie);
        participa1.setProba(proba);
        participa1.setSportiv(sportiv);

        Participa participa3 = new Participa();
        participa3.setIdCompetitie(1);
        participa3.setIdProba(2);
        participa3.setIdSportiv(2);
        participa3.setLocClasament(2);
        participa3.setTimp(40);
        participa3.setCompetitie(competitie);
        participa3.setProba(proba2);
        participa3.setSportiv(sportiv1);

        List<Participa> participaList = new ArrayList<>();
        participaList.add(participa1);

        RezultateDtoSp rezultate1 = new RezultateDtoSp();
        rezultate1.setNumeCompetitie(participa1.getCompetitie().getNume());
        rezultate1.setNumeProba(participa1.getProba().getNume());
        rezultate1.setTimp(participa1.getTimp());
        rezultate1.setLocClasament(participa1.getLocClasament());

       // private String numeCompetitie;
        //    private String numeProba;
        //    private Integer timp;
        //    private Integer locClasament;

        List<RezultateDtoSp> rezultateList = new ArrayList<>();
        rezultateList.add(rezultate1);

        when(participaRepository.findAll()).thenReturn(participaList);
        when(competitieRepository.findById(competitie.getId())).thenReturn(Optional.of(competitie));
        when(probaRepository.findById(proba.getId())).thenReturn(Optional.of(proba));


        //Act
        List<RezultateDtoSp> result = participaService.getRezultateBySportivId(sportivId);

        //Assert
        assertNotNull(result);

        assertEquals(1,result.size());

        assertEquals(rezultate1.getNumeProba(),result.get(0).getNumeProba());
        assertEquals(rezultate1.getNumeCompetitie(),result.get(0).getNumeCompetitie());
        assertEquals(rezultate1.getTimp(),result.get(0).getTimp());
        assertEquals(rezultate1.getLocClasament(),result.get(0).getLocClasament());

        verify(sportivRepository).findById(sportivId);
        verify(participaRepository).findAll();
    }

    @Test
    void whenSportivDoesntExists_getBestOfBySportivId_throwsSportivNotFoundException(){
        //Arrange
        Long sportivId = 1L;
        when(sportivRepository.findById(sportivId))
                .thenReturn(Optional.empty());
        //Act
        SportivNotFoundException exception = assertThrows(SportivNotFoundException.class,
                () -> participaService.getBestOfBySportivId(sportivId));

        //Assert
        assertEquals("Sportivul cu id " + sportivId + " nu exista.", exception.getMessage());
    }

    @Test
    void whenSportivExists_getBestOfBySportivId_returnsTheBestOfBySportivId() throws ParseException {
        //Arrange
        Long sportivId = 1L;

        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Competitie competitie2 = new Competitie();
        competitie2.setId(2);
        competitie2.setNume("Cupa de primavara 2022");
        competitie2.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-22T12:00:00"));
        competitie2.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-23T20:00:00"));
        competitie2.setTaxaParticipare(200.5);
        competitie2.setTip(tip);

        Proba proba = new Proba();
        proba.setNume("50Liber");
        proba.setId(1);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        when(sportivRepository.findById(sportivId))
                .thenReturn(Optional.of(sportiv));


        Participa participa1 = new Participa();
        participa1.setIdCompetitie(1);
        participa1.setIdProba(1);
        participa1.setIdSportiv(1);
        participa1.setLocClasament(1);
        participa1.setTimp(30);
        participa1.setCompetitie(competitie);
        participa1.setProba(proba);
        participa1.setSportiv(sportiv);

        Participa participa3 = new Participa();
        participa3.setIdCompetitie(2);
        participa3.setIdProba(1);
        participa3.setIdSportiv(1);
        participa3.setLocClasament(2);
        participa3.setTimp(29);
        participa3.setCompetitie(competitie2);
        participa3.setProba(proba);
        participa3.setSportiv(sportiv);

        List<Participa> participaList = new ArrayList<>();
        participaList.add(participa1);
        participaList.add(participa3);

        RezultateDtoBestOf rezultate1 = new RezultateDtoBestOf();
        rezultate1.setNumeCompetitie(participa3.getCompetitie().getNume());
        rezultate1.setDataStart(participa3.getCompetitie().getDataStart());
        rezultate1.setDataFinal(participa3.getCompetitie().getDataFinal());
        rezultate1.setNumeProba(participa3.getProba().getNume());
        rezultate1.setTimp(participa3.getTimp());

        // private String numeProba;
        //    private String numeCompetitie;
        //    private Date dataStart;
        //    private Date dataFinal;
        //    private Integer timp;

        List<RezultateDtoBestOf> rezultateList = new ArrayList<>();
        rezultateList.add(rezultate1);

        when(participaRepository.findAll()).thenReturn(participaList);


        //Act
        List<RezultateDtoBestOf> result = participaService.getBestOfBySportivId(sportivId);

        //Assert
        assertNotNull(result);
        assertEquals(1,result.size());

        assertEquals(rezultate1.getNumeProba(),result.get(0).getNumeProba());
        assertEquals(rezultate1.getNumeCompetitie(),result.get(0).getNumeCompetitie());
        assertEquals(rezultate1.getTimp(),result.get(0).getTimp());
        assertEquals(rezultate1.getDataFinal(),result.get(0).getDataFinal());
        assertEquals(rezultate1.getDataStart(),result.get(0).getDataStart());

        verify(sportivRepository).findById(sportivId);
        verify(participaRepository).findAll();
    }

    @Test
    void whenSportivCompetitieDoesntExists_getRezultateBySportivIdCompetitieId_throwsSportivAndCompetitieNotFoundException(){
        //Arrange
        Long sportivId = 1L;
        Long competitieId = 1L;
        when(sportivRepository.findById(sportivId))
                .thenReturn(Optional.empty());
        when(competitieRepository.findById(competitieId))
                .thenReturn(Optional.empty());
        //Act
        SportivAndCompetitieNotFoundException exception = assertThrows(SportivAndCompetitieNotFoundException.class,
                () -> participaService.getRezultateBySportivIdCompetitieId(sportivId,competitieId));

        //Assert
        assertEquals("Combinatia sportiv cu id "+sportivId+" competitia cu id "+competitieId+" nu exista", exception.getMessage());
    }

    @Test
    void whenSportivCompetitieExists_getRezultateBySportivIdCompetitieId_returnsTheRezultateBySportivIdCompetitieId() throws ParseException {
        //Arrange
        Long sportivId = 1L;
        Long competitieId = 1L;

        Tip tip = new Tip(1,"nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        Competitie competitie2 = new Competitie();
        competitie2.setId(2);
        competitie2.setNume("Cupa de primavara 2022");
        competitie2.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-22T12:00:00"));
        competitie2.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-23T20:00:00"));
        competitie2.setTaxaParticipare(200.5);
        competitie2.setTip(tip);

        Proba proba = new Proba();
        proba.setNume("50Liber");
        proba.setId(1);

        Proba proba1 = new Proba();
        proba1.setNume("50Spate");
        proba1.setId(2);

        Club club = new Club();
        club.setId(1);
        club.setNume("BSC");
        club.setNrInregistrare(500);

        Antrenor antrenor1 = new Antrenor();
        antrenor1.setNume("Mili");
        antrenor1.setPrenume("Aurelia");
        antrenor1.setAniExperienta(2);
        antrenor1.setSalariu(200.5f);
        antrenor1.setGen("F");
        antrenor1.setClub(club);
        antrenor1.setId(1);

        Sportiv sportiv = new Sportiv();
        sportiv.setId(1);
        sportiv.setNume("Spirida");
        sportiv.setPrenume("Maria");
        sportiv.setGen("F");
        sportiv.setAnNastere(2000);
        sportiv.setNrLegitimatie(20);
        sportiv.setIndemnizatie(200.5);
        sportiv.setAntrenor(antrenor1);

        when(sportivRepository.findById(sportivId))
                .thenReturn(Optional.of(sportiv));
        when(competitieRepository.findById(competitieId))
                .thenReturn(Optional.of(competitie));

        Participa participa1 = new Participa();
        participa1.setIdCompetitie(1);
        participa1.setIdProba(1);
        participa1.setIdSportiv(1);
        participa1.setLocClasament(1);
        participa1.setTimp(30);
        participa1.setCompetitie(competitie);
        participa1.setProba(proba);
        participa1.setSportiv(sportiv);

        Participa participa2 = new Participa();
        participa2.setIdCompetitie(1);
        participa2.setIdProba(2);
        participa2.setIdSportiv(1);
        participa2.setLocClasament(1);
        participa2.setTimp(40);
        participa2.setCompetitie(competitie);
        participa2.setProba(proba1);
        participa2.setSportiv(sportiv);

        Participa participa3 = new Participa();
        participa3.setIdCompetitie(2);
        participa3.setIdProba(1);
        participa3.setIdSportiv(1);
        participa3.setLocClasament(2);
        participa3.setTimp(29);
        participa3.setCompetitie(competitie2);
        participa3.setProba(proba);
        participa3.setSportiv(sportiv);

        List<Participa> participaList = new ArrayList<>();
        participaList.add(participa1);
        participaList.add(participa3);
        participaList.add(participa2);

        RezultateDtoSpComp rezultate1 = new RezultateDtoSpComp();
        rezultate1.setLocClasament(participa1.getLocClasament());
        rezultate1.setNumeProba(participa1.getProba().getNume());
        rezultate1.setTimp(participa1.getTimp());

        RezultateDtoSpComp rezultate2 = new RezultateDtoSpComp();
        rezultate2.setLocClasament(participa2.getLocClasament());
        rezultate2.setNumeProba(participa2.getProba().getNume());
        rezultate2.setTimp(participa2.getTimp());

        //     private String numeProba;
        //    private Integer timp;
        //    private Integer locClasament;

        List<RezultateDtoSpComp> rezultateList = new ArrayList<>();
        rezultateList.add(rezultate1);
        rezultateList.add(rezultate2);

        when(participaRepository.findAll()).thenReturn(participaList);
        when(probaRepository.findById(participa1.getIdProba()))
                .thenReturn(Optional.of(proba));
        when(probaRepository.findById(participa2.getIdProba()))
                .thenReturn(Optional.of(proba1));

        //Act
        List<RezultateDtoSpComp> result = participaService.getRezultateBySportivIdCompetitieId(sportivId,competitieId);

        //Assert
        assertNotNull(result);
        assertEquals(2,result.size());

        assertEquals(rezultateList.get(0).getNumeProba(),result.get(0).getNumeProba());
        assertEquals(rezultateList.get(0).getLocClasament(),result.get(0).getLocClasament());
        assertEquals(rezultateList.get(0).getTimp(),result.get(0).getTimp());

        assertEquals(rezultateList.get(1).getNumeProba(),result.get(1).getNumeProba());
        assertEquals(rezultateList.get(1).getLocClasament(),result.get(1).getLocClasament());
        assertEquals(rezultateList.get(1).getTimp(),result.get(1).getTimp());

        verify(sportivRepository).findById(sportivId);
        verify(competitieRepository).findById(competitieId);
        verify(participaRepository).findAll();
        verify(probaRepository).findById(participa1.getIdProba());
        verify(probaRepository).findById(participa2.getIdProba());
    }
}
