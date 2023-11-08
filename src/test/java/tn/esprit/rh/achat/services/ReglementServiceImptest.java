package tn.esprit.rh.achat.services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReglementServiceImptest {

    @Mock
    ReglementRepository reglementRepository;

    @InjectMocks
    ReglementServiceImpl reglementService;

    @Test
    @Order(1)
    public void testretrieveAllCategorieProduits(){

        List<Reglement> lCat = reglementService.retrieveAllReglements();

        Assertions.assertEquals(0, lCat.size());

    }

}
