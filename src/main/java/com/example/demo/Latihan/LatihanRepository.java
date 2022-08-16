package com.example.demo.Latihan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LatihanRepository extends JpaRepository<Latihan,Long> {

    @Query("SELECT s FROM Latihan s WHERE s.email = ?1")
    Optional<Latihan> findLatihanByEmail(String email);
}
