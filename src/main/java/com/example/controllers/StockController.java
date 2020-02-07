package com.example.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.models.Stock;
import com.example.models.StockHistory;
import com.example.repositories.StockHistoryRepository;
import com.example.repositories.StockRepository;

@Controller
public class StockController {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockHistoryRepository stockHistoryRepository;

	@GetMapping(value={"/"})
    public ModelAndView getRoot(Model model)
    {
    	ModelAndView mnv= new ModelAndView("index");
        mnv.getModelMap().addAttribute("stocks", stockRepository.findAll());
        return mnv;
    }
	
	@GetMapping(value={"/api/stocks"})
    public ModelAndView getRoot2(Model model)
    {
    	ModelAndView mnv= new ModelAndView("index");
        mnv.getModelMap().addAttribute("stocks", stockRepository.findAll());
        return mnv;
    }

	@GetMapping("/api/stocks/{id}")
	public ModelAndView one(@PathVariable Long id) {
		ModelAndView mnv = new ModelAndView("index");
        mnv.getModelMap().addAttribute("stocks", stockRepository.findById(id).get());
        return mnv;
	}

	@GetMapping("/api/stocks/{id}/history")
	ModelAndView oneHistory(@PathVariable Long id) {
		ModelAndView mnv= new ModelAndView("index-history");
		mnv.getModelMap().addAttribute("stocks", StreamSupport.stream(stockHistoryRepository.findAll().spliterator(), false).filter(stockHistory -> id.equals(stockHistory.getIdStock()))     // we dont like mkyong
		        .collect(Collectors.toList()));
        return mnv;
	}

	@PutMapping("/api/stocks/{id}")
	Stock replaceStock(@RequestBody Stock newStock, @PathVariable Long id) {
		StockHistory sh = new StockHistory();
		Stock stock2 = stockRepository.findById(id).get();
		sh.setIdStock(id);
		sh.setCurrentPrice(stock2.getCurrentPrice());
		sh.setLastUpdate(new java.util.Date());
		stockHistoryRepository.save(sh);
		
		return stockRepository.findById(id).map(stock -> {
			stock.setCurrentPrice(newStock.getCurrentPrice());
			stock.setLastUpdate(new java.util.Date());
			return stockRepository.save(stock);
		}).orElseGet(() -> {
			newStock.setId(id);
			return stockRepository.save(newStock);
		});
	}

	@PostMapping("/api/stocks")
	Stock newStock(@RequestBody Stock newStock) {
		return stockRepository.save(newStock);
	}

}
