package com.nazzareno.it.service;



import java.util.List;

import com.nazzareno.it.dto.MeteoRegistrationDTO;
import com.nazzareno.it.model.Meteo;

public interface MeteoService {
    void saveMeteo(MeteoRegistrationDTO meteoDTO);
    List<Meteo> findByUserId(Long userId);
}