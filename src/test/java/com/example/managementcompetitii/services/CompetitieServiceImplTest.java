package com.example.managementcompetitii.services;
import com.example.managementcompetitii.dto.CompetitieDtoUpdate;
import com.example.managementcompetitii.exception.ClubNotFoundException;
import com.example.managementcompetitii.exception.CompetitieNotFoundException;
import com.example.managementcompetitii.exception.DuplicateClubException;
import com.example.managementcompetitii.exception.DuplicateCompetitieException;
import com.example.managementcompetitii.model.Antrenor;
import com.example.managementcompetitii.model.Club;
import com.example.managementcompetitii.model.Competitie;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.repositories.CompetitieRepository;
import com.example.managementcompetitii.repositories.TipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CompetitieServiceImplTest {
    @InjectMocks
    private CompetitieServiceImpl competitieService;

    @Mock
    private CompetitieRepository competitieRepository;
    @Mock
    private TipRepository tipRepository;

    @Test
    void whenCompetitieAlreadyExists_saveCompetitie_throwsDuplicateCompetitieException() throws ParseException {
        //Arrange
        Tip tip = new Tip("nationala");
        Competitie competitie = new Competitie();
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        when(competitieRepository.findByNume(competitie.getNume()))
                .thenReturn(Optional.of(competitie));

        //Act
        DuplicateCompetitieException exception = assertThrows(DuplicateCompetitieException.class,
                () -> competitieService.saveCompetitie(competitie));

        //Assert
        assertEquals("Exista competitie cu acelasi nume", exception.getMessage());
        verify(competitieRepository, times(0)).save(competitie);
    }

    @Test
    void whenCompetitieDoesntExist_saveCompetitie_savesTheCompetitie() throws ParseException {
        //Arrange
        Tip tip = new Tip("nationala");
        Competitie competitie = new Competitie();
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        when(competitieRepository.findByNume(competitie.getNume()))
                .thenReturn(Optional.empty());

        Competitie savedCompetitie = new Competitie();
        savedCompetitie.setNume("Cupa de primavara 2023");
        savedCompetitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        savedCompetitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        savedCompetitie.setTaxaParticipare(200.5);
        savedCompetitie.setTip(tip);
        savedCompetitie.setId(1);


        when(competitieRepository.save(competitie)).thenReturn(savedCompetitie);

        //Act
        Competitie result = competitieService.saveCompetitie(competitie);

        //Assert
        assertNotNull(result);
        assertEquals(savedCompetitie.getId(),result.getId());

        assertEquals(savedCompetitie.getNume(),result.getNume());
        assertEquals(competitie.getNume(),result.getNume());

        assertEquals(savedCompetitie.getDataStart(),result.getDataStart());
        assertEquals(competitie.getDataStart(),result.getDataStart());

        assertEquals(savedCompetitie.getDataFinal(),result.getDataFinal());
        assertEquals(competitie.getDataFinal(),result.getDataFinal());

        assertEquals(savedCompetitie.getTaxaParticipare(),result.getTaxaParticipare());
        assertEquals(competitie.getTaxaParticipare(),result.getTaxaParticipare());

        assertEquals(savedCompetitie.getTip(),result.getTip());
        assertEquals(competitie.getTip(),result.getTip());

        verify(competitieRepository).findByNume(competitie.getNume());
        verify(competitieRepository).save(competitie);
    }

    @Test
    void whenCompetitieDoesntExists_deleteById_throwsCompetitieNotFoundException(){
        //Arrange
        Long id = 1L;
        when(competitieRepository.findById(id))
                .thenReturn(Optional.empty());
        //Act
        CompetitieNotFoundException exception = assertThrows(CompetitieNotFoundException.class,
                () -> competitieService.deleteById(id));

        //Assert
        assertEquals("Competitia cu id " + id + " nu exista", exception.getMessage());
    }

    @Test
    void whenCompetitieExists_deleteById_deletesTheCompetitie() throws ParseException {
        //Arrange
        Long id = 1L;
        Tip tip = new Tip("nationala");
        Competitie competitie = new Competitie();
        competitie.setId(1);
        competitie.setNume("Cupa de primavara 2023");
        competitie.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie.setTaxaParticipare(200.5);
        competitie.setTip(tip);

        when(competitieRepository.findById(id))
                .thenReturn(Optional.of(competitie));

        //Act
        competitieService.deleteById(id);

        //Assert
        verify(competitieRepository).deleteById(id);
    }

    @Test
    void whenCompetitieDoesntExists_updateCompetitie_throwsCompetitieNotFoundException() throws ParseException {
        //Arrange
        Long id = 1L;
        CompetitieDtoUpdate competitieDtoUpdate = new CompetitieDtoUpdate();
        competitieDtoUpdate.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitieDtoUpdate.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T12:00:00"));
        competitieDtoUpdate.setTaxaParticipare(200.4);

        when(competitieRepository.findById(id))
                .thenReturn(Optional.empty());

        //Act
        CompetitieNotFoundException exception = assertThrows(CompetitieNotFoundException.class,
                () -> competitieService.updateCompetitie(id,competitieDtoUpdate));

        //Assert
        assertEquals("Competitia cu id " + id + " nu exista", exception.getMessage());
        verify(competitieRepository).findById(id);
    }

    @Test
    void whenCompetitieExists_updateCompetitie_returnsNewTheCompetitie() throws ParseException {
        //Arrange
        Long id = 1L;
        CompetitieDtoUpdate competitieDtoUpdate = new CompetitieDtoUpdate();
        competitieDtoUpdate.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitieDtoUpdate.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T12:00:00"));
        competitieDtoUpdate.setTaxaParticipare(200.4);

        Tip tip = new Tip("nationala");
        Competitie competitieOld = new Competitie();
        competitieOld.setId(1);
        competitieOld.setNume("Cupa de primavara 2023");
        competitieOld.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitieOld.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitieOld.setTaxaParticipare(200.5);
        competitieOld.setTip(tip);

        when(competitieRepository.findById(id))
                .thenReturn(Optional.of(competitieOld));

        Competitie competitieNew = new Competitie();
        competitieNew.setId(id);
        competitieNew.setNume("Cupa de primavara 2023");
        competitieNew.setDataStart(competitieDtoUpdate.getDataStart());
        competitieNew.setDataFinal(competitieDtoUpdate.getDataFinal());
        competitieNew.setTaxaParticipare(competitieDtoUpdate.getTaxaParticipare());
        competitieNew.setTip(tip);

        when(competitieRepository.getById(id))
                .thenReturn(competitieOld);
        when(competitieRepository.save(competitieNew)).thenReturn(competitieNew);

        //Act
        Competitie result = competitieService.updateCompetitie(id,competitieDtoUpdate);

        //Assert
        assertEquals(competitieOld.getId(),result.getId());
        assertEquals(competitieOld.getNume(),result.getNume());
        assertEquals(competitieOld.getTip(),result.getTip());

        assertEquals(competitieNew.getDataStart(),result.getDataStart());
        assertEquals(competitieNew.getDataFinal(),result.getDataFinal());
        assertEquals(competitieNew.getTaxaParticipare(),result.getTaxaParticipare());
        assertEquals(competitieNew.getId(),result.getId());
        assertEquals(competitieNew.getNume(),result.getNume());
        assertEquals(competitieNew.getTip(),result.getTip());
        assertEquals(id,result.getId());

        verify(competitieRepository).findById(id);
        verify(competitieRepository).getById(id);
        verify(competitieRepository).save(competitieNew);
    }


    @Test
    void testGetAllCompetitii() throws ParseException {
        //Arrange
        Tip tip = new Tip("nationala");
        Competitie competitie1 = new Competitie();
        competitie1.setId(1);
        competitie1.setNume("Cupa de primavara 2023");
        competitie1.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie1.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie1.setTaxaParticipare(200.5);
        competitie1.setTip(tip);

        Competitie competitie2 = new Competitie();
        competitie2.setId(2);
        competitie2.setNume("Cupa de primavara 2022");
        competitie2.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-22T12:00:00"));
        competitie2.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-03-23T20:00:00"));
        competitie2.setTaxaParticipare(200.5);
        competitie2.setTip(tip);


        List<Competitie> competitieList = new ArrayList<>();
        competitieList.add(competitie1);
        competitieList.add(competitie2);

        when(competitieRepository.findAllByOrderByDataStartDesc()).thenReturn(competitieList);

        //Act
        List<Competitie> result = competitieService.getAllCompetitii();

        //Assert
        assertEquals(competitieList.size(),result.size());
        assertEquals(competitieList,result);

        verify(competitieRepository).findAllByOrderByDataStartDesc();
    }

    @Test
    void testGetCompetitiiYearStartTip() throws ParseException {
        Integer anStart = 2023;
        String numeTip = "nationala";

        Tip tip = new Tip(1,numeTip);
        Tip tip1 = new Tip(2,"europeana");
        Competitie competitie1 = new Competitie();
        competitie1.setId(1);
        competitie1.setNume("Cupa de primavara 2023");
        competitie1.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-22T12:00:00"));
        competitie1.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-03-23T20:00:00"));
        competitie1.setTaxaParticipare(200.5);
        competitie1.setTip(tip);

        Competitie competitie2 = new Competitie();
        competitie2.setId(2);
        competitie2.setNume("Cupa de vara 2023");
        competitie2.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-06-22T12:00:00"));
        competitie2.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2023-06-23T20:00:00"));
        competitie2.setTaxaParticipare(200.5);
        competitie2.setTip(tip);

        Competitie competitie3 = new Competitie();
        competitie3.setId(3);
        competitie3.setNume("Cupa Marea Albastra 2022");
        competitie3.setDataStart(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-09-22T12:00:00"));
        competitie3.setDataFinal(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2022-09-23T20:00:00"));
        competitie3.setTaxaParticipare(110.5);
        competitie3.setTip(tip1);

        List<Competitie> competitieList = new ArrayList<>();
        competitieList.add(competitie1);
        competitieList.add(competitie2);

        when(tipRepository.getByNume(numeTip)).thenReturn(tip);
        when(competitieRepository.findByTipId(tip.getId())).thenReturn(competitieList);

        //Act
        List<Competitie> result = competitieService.getCompetitiiYearStartTip(anStart,numeTip);

        //Assert
        assertEquals(2,result.size());
        assertEquals(competitieList,result);

        verify(competitieRepository).findByTipId(tip.getId());
        verify(tipRepository).getByNume(numeTip);

    }
}
