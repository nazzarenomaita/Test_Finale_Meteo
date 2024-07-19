package com.nazzareno.it.service;

import com.nazzareno.it.dto.MeteoDTO;

import java.util.List;

public interface MeteoService {
    MeteoDTO saveMeteo(MeteoDTO meteoDTO);
    List<MeteoDTO> findByUserId(Long userId);
}
