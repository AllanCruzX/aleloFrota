package com.alelo.alelofrota.core.modelmapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alelo.alelofrota.api.model.input.VeiculoInput;
import com.alelo.alelofrota.domain.model.Modelo;
import com.alelo.alelofrota.domain.model.Veiculo;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		Converter<VeiculoInput,  Veiculo> coverterEmVeiculo = new Converter<VeiculoInput,  Veiculo>() {
			  public Veiculo convert(MappingContext<VeiculoInput, Veiculo> context) {
				  if (context.getSource() == null) {
						return null;
					}
				  Veiculo veiculo = new Veiculo();
				  if (context.getDestination() != null && context.getDestination().getId() != null) {

					  veiculo = context.getDestination();

					  veiculo.setId(context.getDestination().getId());

					}
				  
				  veiculo.setPlaca(context.getSource().getPlaca());
				  veiculo.setStatus(context.getSource().isStatus());
				  veiculo.setModelo(new Modelo());
				  veiculo.getModelo().setId(context.getSource().getModelo());
				  
			    return veiculo;
			  }
			};
		
			modelMapper.addConverter(coverterEmVeiculo);
		
		return modelMapper;
	}
	
}
