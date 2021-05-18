package com.alelo.alelofrota.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alelo.alelofrota.api.assembler.FabricanteModelAssembler;
import com.alelo.alelofrota.api.assembler.ModeloModelAssembler;
import com.alelo.alelofrota.api.model.FabricanteModel;
import com.alelo.alelofrota.api.model.ModeloModel;
import com.alelo.alelofrota.api.openapi.controller.FabricanteControllerOpenApi;
import com.alelo.alelofrota.domain.exception.FabricanteNaoEncontradoException;
import com.alelo.alelofrota.domain.model.Fabricante;
import com.alelo.alelofrota.domain.model.Modelo;
import com.alelo.alelofrota.domain.repository.FabricanteRepository;
import com.alelo.alelofrota.domain.repository.ModeloRepository;

@RestController
@RequestMapping(path = "/fabricantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class FabricanteController implements FabricanteControllerOpenApi {

	@Autowired
	private FabricanteRepository fabricanteRepository;

	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private FabricanteModelAssembler fabricanteModelAssembler;

	@Autowired
	private ModeloModelAssembler modeloModelAssembler;

	@Override
	@GetMapping
	public List<FabricanteModel> listar() {

		List<Fabricante> todosFabricantes = fabricanteRepository.findAll();

		return fabricanteModelAssembler.toCollectionModel(todosFabricantes);
	}

	@Override
	@GetMapping("/modelos/{fabricanteId}")
	public List<ModeloModel> buscarModedlos(@PathVariable Long fabricanteId) {

		Fabricante fabricante = fabricanteRepository.findById(fabricanteId)
				.orElseThrow(() -> new FabricanteNaoEncontradoException(fabricanteId));

		List<Modelo> modelos = modeloRepository.buscarModelosPorFabricante(fabricante.getId());

		return modeloModelAssembler.toCollectionModel(modelos);
	}

}
