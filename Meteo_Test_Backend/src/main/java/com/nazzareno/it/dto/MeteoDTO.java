package com.nazzareno.it.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;


public class MeteoDTO {
	@NotNull
	private Long Id;

    public Long getId() {
    	return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	@NotBlank
    private String città;

    @NotNull
    private LocalDate data;

    @NotNull
    private double tempmax;

    @NotNull
    private double tempmin;
    
    @NotNull
    private Long userId;

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getTempmax() {
		return tempmax;
	}

	public void setTempmax(double tempmax) {
		this.tempmax = tempmax;
	}

	public double getTempmin() {
		return tempmin;
	}

	public void setTempmin(double tempmin) {
		this.tempmin = tempmin;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}
