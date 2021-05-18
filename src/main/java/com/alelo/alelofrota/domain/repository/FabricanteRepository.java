package com.alelo.alelofrota.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alelo.alelofrota.domain.model.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

}
