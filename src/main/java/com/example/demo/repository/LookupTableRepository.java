package com.example.demo.repository;

import com.example.demo.entity.LookupTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LookupTableRepository extends JpaRepository<LookupTable, String> {
    Optional<LookupTable> findByxIdentity(String xIdentity);
}

