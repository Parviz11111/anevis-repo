package com.assignment3.h2setup.repository;

import com.assignment3.h2setup.model.PieData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieDataRepository extends CrudRepository<PieData, Long> {
}
