package com.alelo.alelofrota.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VeiculoFiltro {

	@ApiModelProperty(example = "1", value = "ID do veículo para filtro da pesquisa")
	private Long veiculoId;
	
	@ApiModelProperty(example = "ABC1234", value = "Placa do veículo para filtro da pesquisa")
	private String placa;
	
	@ApiModelProperty(example = "true", value = "Status do veículo para filtro da pesquisa")
	private boolean status;
	
}
