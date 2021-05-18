package com.alelo.alelofrota.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10 , nullable = false )
	private String placa;
	
	@Column( nullable = false )
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "modelo_id",
			 foreignKey = @ForeignKey(name = "fk_veiculo_modelo"))
	private Modelo modelo;
	
}