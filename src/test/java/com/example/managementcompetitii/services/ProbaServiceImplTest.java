package com.example.managementcompetitii.services;

import com.example.managementcompetitii.exception.DuplicateProbaException;
import com.example.managementcompetitii.model.Proba;
import com.example.managementcompetitii.repositories.ProbaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ProbaServiceImplTest {
    @InjectMocks
    private ProbaServiceImpl probaService;

    @Mock
    private ProbaRepository probaRepository;

    @Test
    void whenProbaAlreadyExists_saveProba_throwsDuplicateProbaException(){
        //Arrange
        Proba proba = new Proba();
        proba.setNume("400Liber");

        when(probaRepository.findByNume(proba.getNume()))
                .thenReturn(Optional.of(proba));

        //Act
        DuplicateProbaException exception = assertThrows(DuplicateProbaException.class,
                () -> probaService.saveProba(proba));

        //Assert
        assertEquals("Acesta proba a fost deja introdusa.",exception.getMessage());
        verify(probaRepository,times(0)).save(proba);
    }

    @Test
    void whenProbaDoesntExist_saveProba_savesTheProba(){
        //Arrange
        Proba proba = new Proba();
        proba.setNume("400Liber");

        when(probaRepository.findByNume(proba.getNume()))
                .thenReturn(Optional.empty());

        Proba savedProba = new Proba();
        savedProba.setNume("400Liber");
        savedProba.setId(1);

        when(probaRepository.save(proba)).thenReturn(savedProba);

        //Act
        Proba result = probaService.saveProba(proba);

        //Assert
        assertNotNull(result);
        assertEquals(savedProba.getId(),result.getId());

        assertEquals(savedProba.getNume(),result.getNume());
        assertEquals(proba.getNume(),result.getNume());

        verify(probaRepository).findByNume(proba.getNume());
        verify(probaRepository).save(proba);
    }

    @Test
    void testGetAllProbe(){
        //Arrange

        Proba proba1 = new Proba();
        proba1.setId(1);
        proba1.setNume("50Spate");

        Proba proba2 = new Proba();
        proba2.setId(1);
        proba2.setNume("800Liber");

        List<Proba> probaList = new ArrayList<>();;
        probaList.add(proba1);
        probaList.add(proba2);

        when(probaRepository.findAll()).thenReturn(probaList);

        //Act
        List<Proba> result = probaService.getAllProbe();

        //Assert
        assertEquals(probaList.size(),result.size());
        assertEquals(probaList,result);

    }


}

