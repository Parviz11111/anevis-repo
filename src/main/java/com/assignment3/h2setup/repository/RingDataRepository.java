package com.assignment3.h2setup.repository;

import com.assignment3.h2setup.model.RingData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RingDataRepository extends CrudRepository<RingData, Long> {
}
