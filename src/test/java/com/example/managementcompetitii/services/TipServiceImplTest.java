package com.example.managementcompetitii.services;

import com.example.managementcompetitii.exception.DuplicateTipException;
import com.example.managementcompetitii.model.Tip;
import com.example.managementcompetitii.repositories.TipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TipServiceImplTest {
    @InjectMocks
    private TipServiceImpl tipService;

    @Mock
    private TipRepository tipRepository;

    @Test
    void whenTipAlreadyExists_saveTip_throwsDuplicateTipException(){
        //Arrange
        Tip tip = new Tip();
        tip.setNume("locala");
        when(tipRepository.findByNume(tip.getNume()))
                .thenReturn(Optional.of(tip));

        //Act
        DuplicateTipException exception = assertThrows(DuplicateTipException.class,
                () -> tipService.saveTip(tip));

        //Assert
        assertEquals("Acest tip de competitie a fost deja introdus.",exception.getMessage());
        verify(tipRepository, times(0)).save(tip);
    }

    @Test
    void whenTipDoesntExist_saveTip_savesTheTip(){
        //Arrange
        Tip tip = new Tip();
        tip.setNume("comunala");

        when(tipRepository.findByNume(tip.getNume()))
                .thenReturn(Optional.empty());

        Tip savedTip = new Tip();
        savedTip.setNume("comunala");
        savedTip.setId(1);

        when(tipRepository.save(tip)).thenReturn(savedTip);

        //Act
        Tip result = tipService.saveTip(tip);

        //Assert
        assertNotNull(result);
        assertEquals(savedTip.getId(), result.getId());

        assertEquals(savedTip.getNume(), result.getNume());
        assertEquals(tip.getNume(), result.getNume());

        verify(tipRepository).findByNume(tip.getNume());
        verify(tipRepository).save(tip);

    }

    @Test
    void testGetAllTipuri(){
        //Arrange
        
        Tip tip1 = new Tip();
        tip1.setId(1);
        tip1.setNume("locala");

        Tip tip2 = new Tip();
        tip2.setId(2);
        tip2.setNume("regionala");

        List<Tip> tipList = new ArrayList<>();;
        tipList.add(tip1);
        tipList.add(tip2);

        when(tipRepository.findAll()).thenReturn(tipList);

        //Act
        List<Tip> result = tipService.getAllTipuri();

        //Assert
        assertEquals(tipList.size(),result.size());
        assertEquals(tipList,result);

    }
}
