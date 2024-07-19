package com.nazzareno.it.dao;

import com.nazzareno.it.model.Meteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeteoDao extends JpaRepository<Meteo, Long> {
    List<Meteo> findByUserId(Long userId);
}
