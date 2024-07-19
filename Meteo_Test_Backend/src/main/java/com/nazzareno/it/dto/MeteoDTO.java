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
    private BigDecimal tempmax;

    @NotNull
    private BigDecimal tempmin;
    
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

	public BigDecimal getTempmax() {
		return tempmax;
	}

	public void setTempmax(BigDecimal tempmax) {
		this.tempmax = tempmax;
	}

	public BigDecimal getTempmin() {
		return tempmin;
	}

	public void setTempmin(BigDecimal tempmin) {
		this.tempmin = tempmin;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}
