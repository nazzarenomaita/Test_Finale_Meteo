package com.nazzareno.it.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "meteo")

public class Meteo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String città;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal tempmax;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal tempmin;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
