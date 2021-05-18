package com.alelo.alelofrota.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alelo.alelofrota.domain.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
	
	@Query("SELECT m FROM Modelo m INNER JOIN FETCH  m.fabricante mf WHERE mf.id = :fabricanteId ORDER BY m.nome")
	List<Modelo> buscarModelosPorFabricante(Long fabricanteId);

}
