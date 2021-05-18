package com.alelo.alelofrota.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VeiculoModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "ABC1234")
	private String placa;
	
	@ApiModelProperty(example = "true")
	private boolean status;
	
	private ModeloModel modelo;
	
	

}
