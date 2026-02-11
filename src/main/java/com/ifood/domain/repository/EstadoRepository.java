package com.ifood.domain.repository;

import java.util.List;

import com.ifood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();
	Estado buscar(Long id);
	Estado adicionar(Estado estado);
	void remover(Long id);
	
}
