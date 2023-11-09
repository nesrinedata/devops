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
    void testRetrieveAllStocks() {
        // Arrange
        Stock stock = new Stock();
        List<Stock> stocks = Collections.singletonList(stock);
        when(stockRepository.findAll()).thenReturn(stocks);

        // Act
        List<Stock> result = stockService.retrieveAllStocks();

        // Assert
        assertEquals(stocks, result);
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void testAddStock() {
        // Arrange
        Stock stock = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);

        // Act
        Stock result = stockService.addStock(stock);

        // Assert
        assertEquals(stock, result);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testRetrieveStock() {
        // Arrange
        Long stockId = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // Act
        Stock result = stockService.retrieveStock(stockId);

        // Assert
        assertEquals(stock, result);
        verify(stockRepository, times(1)).findById(stockId);
    }

    @Test
    void testRetrieveStatusStock() {
        // Arrange
        Stock stock = new Stock();
        List<Stock> stocksEnRouge = Collections.singletonList(stock);
        when(stockRepository.retrieveStatusStock()).thenReturn(stocksEnRouge);

        // Act
        String result = stockService.retrieveStatusStock();

        // Assert
        assertNotNull(result);
        verify(stockRepository, times(1)).retrieveStatusStock();
    }

    @Test
    void testUpdateStock() {
        // Arrange
        Stock stock = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);

        // Act
        Stock result = stockService.updateStock(stock);

        // Assert
        assertEquals(stock, result);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testDeleteStock() {
        // Arrange
        Long stockId = 1L;

        // Act
        stockService.deleteStock(stockId);

        // Assert
        verify(stockRepository, times(1)).deleteById(stockId);
    }
}