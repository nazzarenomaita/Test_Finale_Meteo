package com.nazzareno.it.controller;

import com.nazzareno.it.dto.UserDTO;
import com.nazzareno.it.dto.MeteoDTO;

import com.nazzareno.it.model.User;
import com.nazzareno.it.service.UserService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/users")
public class UserController {
    @Autowired
    private UserService userService;




    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@PathParam("email") String email) {
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setCognome(user.getCognome());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRuolo(user.getRuolo());
        userDTO.setId(user.getId());
        List<MeteoDTO> meteoDTO = new ArrayList<MeteoDTO>();
        user.getRicercheMeteo().forEach(r -> {
          MeteoDTO meteo = new MeteoDTO();
          meteo.setCittà(r.getCittà());
          meteo.setData(r.getData());
          meteo.setId(r.getId());
          meteo.setTempmax(r.getTempmax());
          meteo.setTempmin(r.getTempmin());
          meteoDTO.add(meteo);
        });
        userDTO.setRicercheMeteo(meteoDTO);
        return Response.ok(userDTO).build();
    }
}
