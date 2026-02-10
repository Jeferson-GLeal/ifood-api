package com.ifood.domain.repository;

import java.util.List;

import com.ifood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante adicionar(Restaurante restaurante);
	void remover(Long id);
	
}
