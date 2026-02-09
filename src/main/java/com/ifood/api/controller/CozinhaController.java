package com.ifood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifood.domain.model.Cozinha;
import com.ifood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ResponseEntity<List<Cozinha>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(repository.listar());
	}

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = repository.buscar(cozinhaId);
		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
	}

}
