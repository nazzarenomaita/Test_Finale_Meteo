package com.nazzareno.it.controller;

import com.nazzareno.it.dto.UserDTO;
import com.nazzareno.it.dto.UserLoginDTO;
import com.nazzareno.it.dto.UserLoginResponseDto;
import com.nazzareno.it.dto.UserSignupDTO;
import com.nazzareno.it.enums.TipoRuolo;
import com.nazzareno.it.model.User;
import com.nazzareno.it.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    
  //  private ModelMapper modelMapper = new ModelMapper();

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(@Valid UserSignupDTO userSignupDTO) {
    	
        UserDTO user = userService.signUp(userSignupDTO);
        if (user != null) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(@Valid UserLoginDTO userLoginDTO) {
    	User userOptional = userService.getUserByEmail(userLoginDTO.getEmail());
    	if (!userOptional.getPassword().isEmpty()) {
            User user = userOptional;
            String hashedPassword = DigestUtils.sha256Hex(userLoginDTO.getPassword());
            if (user.getPassword().equals(hashedPassword)) {
            	UserLoginResponseDto token = issueToken(userLoginDTO.getEmail());
                return Response.status(Status.OK).entity(token).build();
                
            }
        }
        return Response.status(Status.BAD_REQUEST).build();
       
    }
    
    
    public UserLoginResponseDto issueToken (String email) {
	    
	      byte[] secret = "33trentinientraronoatrentotuttie33trotterellando1234567890".getBytes();
	      Key key = Keys.hmacShaKeyFor(secret);
	      
	      User informazioniUtenteOptional = userService.getUserByEmail(email);
	      User informazioniUtente = informazioniUtenteOptional;
	      Map<String, Object> map = new HashMap<>();
	      map.put("nome", informazioniUtente.getNome());
	      map.put("cognome", informazioniUtente.getCognome());
	      map.put("email", email);
	      
	      
	      
	      
	      

	      map.put("ruolo", informazioniUtente.getRuolo());

	      Date creation = new Date();
	      Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
	      
	      String tokenJwts = Jwts.builder()
	    		  .setClaims(map)
	    		  .setIssuer("http://localhost:8080")
	    		  .setIssuedAt(creation)
	    		  .setExpiration(end)
	    		  .signWith(key)
	    		  .compact();
	      
	      UserLoginResponseDto token = new UserLoginResponseDto();
	      
	      token.setToken(tokenJwts);
	      token.setTct(creation);
	      token.setTtl(end);
	      
	      return token;
		
	}
}
