package com.ifood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifood.domain.model.Cozinha;
import com.ifood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;
	
	@GetMapping
	public List<Cozinha> listar() {
		return repository.listar();
	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {
		return repository.buscar(cozinhaId);
	}

}
