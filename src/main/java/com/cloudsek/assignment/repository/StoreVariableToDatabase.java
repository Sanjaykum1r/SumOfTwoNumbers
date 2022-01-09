package com.cloudsek.assignment.repository;

import com.cloudsek.assignment.model.NumberEquality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreVariableToDatabase extends JpaRepository<NumberEquality, Integer> {
}
