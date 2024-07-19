package com.nazzareno.it.controller;

import com.nazzareno.it.dto.MeteoDTO;
import com.nazzareno.it.service.MeteoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Path("/meteo")
public class MeteoController {
    @Autowired
    private MeteoService meteoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MeteoDTO saveMeteo(@Valid MeteoDTO meteoDTO) {
        return meteoService.saveMeteo(meteoDTO);
    }

    @GET
    @Path("/user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MeteoDTO> getMeteosByUserId(@PathParam("userId") Long userId) {
        return meteoService.findByUserId(userId);
    }
}
