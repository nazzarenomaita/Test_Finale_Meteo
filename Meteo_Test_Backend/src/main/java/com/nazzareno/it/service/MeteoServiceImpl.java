package com.nazzareno.it.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nazzareno.it.dao.MeteoDao;
import com.nazzareno.it.dto.MeteoRegistrationDTO;
import com.nazzareno.it.model.Meteo;

import java.util.List;
import java.util.Optional;

@Service
public class MeteoServiceImpl implements MeteoService {

    @Autowired
    private MeteoDao meteoDao;

    @Autowired
    private UserService userService;

    @Override
    public void saveMeteo(MeteoRegistrationDTO meteoDTO) {
        Meteo meteo = new Meteo();
        meteo.setCittà(meteoDTO.getCitta());
        meteo.setData(meteoDTO.getData());
        meteo.setTempmax(meteoDTO.getTempmax());
        meteo.setTempmin(meteoDTO.getTempmin());
        meteo.setUser(userService.findById(meteoDTO.getUserId()));
        meteoDao.save(meteo);
    }

    @Override
    public List<Meteo> findByUserId(Long userId) {
        return meteoDao.findByUserId(userId);
    }

}
