package com.ifood.domain.repository;

import java.util.List;

import com.ifood.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade adicionar(Cidade cidade);
	void remover(Long id);
	
}
