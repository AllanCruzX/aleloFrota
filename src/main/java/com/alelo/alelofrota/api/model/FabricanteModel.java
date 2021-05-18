package com.alelo.alelofrota.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FabricanteModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Mercedez-Benz")
	private String nome;

}
