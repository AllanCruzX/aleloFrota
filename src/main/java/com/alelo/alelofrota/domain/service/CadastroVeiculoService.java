package com.alelo.alelofrota.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alelo.alelofrota.domain.exception.ModeloNaoEncontradoException;
import com.alelo.alelofrota.domain.exception.NegocioException;
import com.alelo.alelofrota.domain.exception.VeiculoNaoEncontradoException;
import com.alelo.alelofrota.domain.model.Veiculo;
import com.alelo.alelofrota.domain.repository.ModeloRepository;
import com.alelo.alelofrota.domain.repository.VeiculoRepository;

@Service
public class CadastroVeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private ModeloRepository modeloRepository;

	@Transactional
	public Veiculo salvar(Veiculo veiculo) {
		
		verificaPlacaExistente(veiculo);
		
		verificaModelo(veiculo);

		return veiculoRepository.save(veiculo);
	}

	@Transactional
	public Veiculo atualizar(Veiculo veiculo) {
		
		verificaPlacaExistente(veiculo);
		
		verificaPlacaAtualizada(veiculo);
		
		verificaModelo(veiculo);

		return veiculoRepository.save(veiculo);
	}

	@Transactional
	public void excluir(Long veiculoId) {
		try {
			veiculoRepository.deleteById(veiculoId);
			veiculoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new VeiculoNaoEncontradoException(veiculoId);
		
		}
	}

	public Veiculo buscarOuFalhar(Long veiculoId) {
		return veiculoRepository.findById(veiculoId).orElseThrow(() -> new VeiculoNaoEncontradoException(veiculoId));
	}
	
	private void verificaPlacaExistente(Veiculo veiculo) {
		veiculoRepository.detach(veiculo);

		Optional<Veiculo> veiculoExistente = veiculoRepository.findByPlaca(veiculo.getPlaca());

		if (veiculoExistente.isPresent() && !veiculoExistente.get().equals(veiculo)) {
			throw new NegocioException(
					String.format("Já existe um veículo cadastrado com a placa %s", veiculo.getPlaca()));
		}
	}
	
	private void verificaPlacaAtualizada(Veiculo veiculo) {
		
		Veiculo veiculoExistente = buscarOuFalhar(veiculo.getId());
		
		if(!veiculoExistente.getPlaca().equals(veiculo.getPlaca())) {
			
			throw new NegocioException(
					String.format("A placa do veículo não pode ser atualizada"));
			
		}
	}
	
	private void verificaModelo(Veiculo veiculo) {
		modeloRepository.findById(veiculo.getModelo().getId()).orElseThrow(() -> new ModeloNaoEncontradoException(veiculo.getModelo().getId()));
	}


}
