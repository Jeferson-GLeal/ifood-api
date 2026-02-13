package com.ifood.domain.repository;

import java.util.List;

import com.ifood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	List<Cozinha> consultarPorNome(String nome);
	Cozinha buscar(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Long id);
	
}
