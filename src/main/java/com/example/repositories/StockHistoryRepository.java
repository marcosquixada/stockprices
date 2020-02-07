package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.StockHistory;

@Repository
public interface StockHistoryRepository extends CrudRepository<StockHistory, Long> {

}