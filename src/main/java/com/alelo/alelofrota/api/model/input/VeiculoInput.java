package com.alelo.alelofrota.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VeiculoInput {

	@NotBlank
	@ApiModelProperty(example = "ABC1234")
	private String placa;

	@NotNull
	@ApiModelProperty(example = "1")
	private Long modelo;

	@ApiModelProperty(example = "true")
	private boolean status;

}
