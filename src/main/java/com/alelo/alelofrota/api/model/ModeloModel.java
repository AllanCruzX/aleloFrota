package com.alelo.alelofrota.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModeloModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Class C 1.6 Avantgarde Turbo Flex")
	private String nome;
	
	@ApiModelProperty(example = "Mercedez-Benz")
	private String fabricanteNome;

}
