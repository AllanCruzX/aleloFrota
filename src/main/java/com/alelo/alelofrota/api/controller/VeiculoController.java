package com.alelo.alelofrota.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alelo.alelofrota.api.assembler.VeiculoInputDisassembler;
import com.alelo.alelofrota.api.assembler.VeiculoModelAssembler;
import com.alelo.alelofrota.api.assembler.VeiculoModelResumoAssembler;
import com.alelo.alelofrota.api.model.VeiculoModel;
import com.alelo.alelofrota.api.model.VeiculoModelResmo;
import com.alelo.alelofrota.api.model.input.VeiculoInput;
import com.alelo.alelofrota.api.openapi.controller.VeiculoControllerOpenApi;
import com.alelo.alelofrota.domain.filter.VeiculoFiltro;
import com.alelo.alelofrota.domain.model.Veiculo;
import com.alelo.alelofrota.domain.repository.VeiculoRepository;
import com.alelo.alelofrota.domain.service.CadastroVeiculoService;

@RestController
@RequestMapping(path = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController implements VeiculoControllerOpenApi {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private CadastroVeiculoService cadastroVeiculoService;
	
	@Autowired 
	private VeiculoModelAssembler veiculoModelAssembler;
	
	@Autowired
	private VeiculoModelResumoAssembler veiculoModelResumoAssembler;
	
	@Autowired
	private VeiculoInputDisassembler veiculoInputDisassembler;

	@Override
	@GetMapping
	public Page<VeiculoModel> listar(VeiculoFiltro veiculoFiltro, @PageableDefault(size = 10) Pageable pageable) {
		
		Page<Veiculo> veiculosPage = veiculoRepository.buscaPaginada(veiculoFiltro, pageable);

		List<VeiculoModel> veiculosModel = veiculoModelAssembler.toCollectionModel(veiculosPage.getContent());

		Page<VeiculoModel> veiculosModelPage = new PageImpl<>(veiculosModel, pageable, veiculosPage.getTotalElements());

		return veiculosModelPage;
	}
	
	
	@Override
	@GetMapping("/{veiculoId}")
	public VeiculoModel buscar(@PathVariable Long veiculoId) {
		Veiculo veiculo = cadastroVeiculoService.buscarOuFalhar(veiculoId);
		
		return veiculoModelAssembler.toModel(veiculo);
	}
	

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VeiculoModelResmo adicionar(@RequestBody @Valid VeiculoInput veiculoInput) {
		
		Veiculo veiculo = veiculoInputDisassembler.toDomainObject(veiculoInput);
		
		veiculo = cadastroVeiculoService.salvar(veiculo);
		
		return veiculoModelResumoAssembler.toModel(veiculo);
	}

	@Override
	@PutMapping("/{veiculoId}")
	public VeiculoModel atualizar(@PathVariable Long veiculoId, @RequestBody @Valid VeiculoInput veiculoInput) {
		
		Veiculo veiculoAtual = cadastroVeiculoService.buscarOuFalhar(veiculoId);
		
		veiculoInputDisassembler.copyToDomainObject(veiculoInput, veiculoAtual);
		
		veiculoAtual = cadastroVeiculoService.atualizar(veiculoAtual);
		
		return veiculoModelAssembler.toModel(veiculoAtual);
	}
	
	
	@Override
	@DeleteMapping("/{veiculoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long veiculoId) {
		cadastroVeiculoService.excluir(veiculoId);	
	}
	
}
