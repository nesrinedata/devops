package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    // Test methods will go here

    @Test
    void testDeleteStock()
        Long stockId = 1L;

        stockService.deleteStock(stockId);

        verify(stockRepository, times(1)).deleteById(stockId);
    }
    @Test
    void testAddStock() {
        Stock stock = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);
        Stock result = stockService.addStock(stock);
        assertEquals(stock, result);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testRetrieveStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        Stock result = stockService.retrieveStock(stockId);
        assertEquals(stock, result);
        verify(stockRepository, times(1)).findById(stockId);
    }
    @Test
    void testRetrieveAllStocks() {
        // Arrange
        Stock stock = new Stock();
        List<Stock> stocks = Collections.singletonList(stock);
        when(stockRepository.findAll()).thenReturn(stocks);
        List<Stock> result = stockService.retrieveAllStocks();
        assertEquals(stocks, result);
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveStatusStock() {
        Stock stock = new Stock();
        List<Stock> stocksEnRouge = Collections.singletonList(stock);
        when(stockRepository.retrieveStatusStock()).thenReturn(stocksEnRouge);
        String result = stockService.retrieveStatusStock();
        assertNotNull(result);
        verify(stockRepository, times(1)).retrieveStatusStock();
    }




}
