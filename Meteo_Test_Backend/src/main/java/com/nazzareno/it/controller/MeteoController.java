package com.nazzareno.it.controller;



import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nazzareno.it.dto.MeteoRegistrationDTO;
import com.nazzareno.it.jwt.JWTTokenNeeded;
import com.nazzareno.it.jwt.Secured;
import com.nazzareno.it.service.MeteoService;

@Component
@Path("/meteo")
@JWTTokenNeeded
@Secured(role = "USER")
public class MeteoController {

    @Autowired
    private MeteoService meteoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveMeteo(@Valid MeteoRegistrationDTO meteoDTO) {
        meteoService.saveMeteo(meteoDTO);
        return Response.status(Response.Status.OK).build();
    }

   
}
