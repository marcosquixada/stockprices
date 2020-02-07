package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

}