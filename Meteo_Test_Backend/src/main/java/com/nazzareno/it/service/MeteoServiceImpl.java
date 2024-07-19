package com.nazzareno.it.service;

import com.nazzareno.it.dao.MeteoDao;
import com.nazzareno.it.dto.MeteoDTO;
import com.nazzareno.it.model.Meteo;
import com.nazzareno.it.service.MeteoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeteoServiceImpl implements MeteoService {
    @Autowired
    private MeteoDao meteoDao;

    
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public MeteoDTO saveMeteo(MeteoDTO meteoDTO) {
        Meteo meteo = modelMapper.map(meteoDTO, Meteo.class);
        meteo = meteoDao.save(meteo);

        return modelMapper.map(meteo, MeteoDTO.class);
    }

    @Override
    public List<MeteoDTO> findByUserId(Long userId) {
        List<Meteo> meteos = meteoDao.findByUserId(userId);
        return meteos.stream().map(meteo -> modelMapper.map(meteo, MeteoDTO.class)).collect(Collectors.toList());
    }
}
