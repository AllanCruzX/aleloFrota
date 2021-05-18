package com.alelo.alelofrota.api.openapi.controller;

import java.util.List;

import com.alelo.alelofrota.api.exceptionhandler.Problem;
import com.alelo.alelofrota.api.model.FabricanteModel;
import com.alelo.alelofrota.api.model.ModeloModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Fabricantes")
public interface FabricanteControllerOpenApi {

	@ApiOperation("Listar os fabricantes")
	List<FabricanteModel> listar();
	
	@ApiOperation("Listar modelos do fabricante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do fabricante inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "fabricante não encontrado", response = Problem.class)
	})
	List<ModeloModel> buscarModedlos(
			@ApiParam(value = "ID de um fabricante", example = "1", required = true)
			Long fabricanteId);
	
}
