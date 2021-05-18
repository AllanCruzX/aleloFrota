package com.alelo.alelofrota.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alelo.alelofrota.api.exceptionhandler.Problem;
import com.alelo.alelofrota.api.model.VeiculoModel;
import com.alelo.alelofrota.api.model.VeiculoModelResmo;
import com.alelo.alelofrota.api.model.input.VeiculoInput;
import com.alelo.alelofrota.domain.filter.VeiculoFiltro;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Veiculos")
public interface VeiculoControllerOpenApi {

	@ApiOperation("Lista os veículos")
	Page<VeiculoModel> listar(
			@ApiParam(name = "corpo", value = "Representação de um veículo para pesquisa") VeiculoFiltro veiculoFiltro,
			Pageable pageable);
	
	@ApiOperation("Busca um veículos por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do veículo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Veículos não encontrado", response = Problem.class)
	})
	VeiculoModel buscar(
			@ApiParam(value = "ID de um veículo", example = "1", required = true)
			Long veiculoId);
	
	@ApiOperation("Cadastra um veículo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Veículo cadastrado"),
	})
	VeiculoModelResmo adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma novo veículo", required = true)
			VeiculoInput veiculoInput);
	
	@ApiOperation("Atualiza um veículo por id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Veículo atualizado"),
		@ApiResponse(code = 404, message = "Veículo não encontrada", response = Problem.class)
	})
	VeiculoModel atualizar(
			@ApiParam(value = "ID de um veículo", example = "1", required = true) 
			Long veiculoId,
			
			@ApiParam(name = "corpo", value = "Representação de uma veículo com os novos dados", required = true)
			VeiculoInput veiculoInput);
	
	@ApiOperation("Exclui um veículo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Veículo excluído"),
		@ApiResponse(code = 404, message = "Veículo não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um veículo", example = "1", required = true)
			Long veiculoId);
	
}
