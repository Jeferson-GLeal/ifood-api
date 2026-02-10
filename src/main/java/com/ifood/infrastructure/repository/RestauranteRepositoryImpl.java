package com.ifood.infrastructure.repository;

import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ifood.domain.model.Restaurante;
import com.ifood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class)
				.getResultList();
	}
	
	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Transactional
	@Override
	public Restaurante adicionar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		Restaurante restaurante = buscar(id);
		if (restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(restaurante.getId());
	}
}
