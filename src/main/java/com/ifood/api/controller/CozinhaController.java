package com.ifood.api.controller;

import com.ifood.domain.model.Cozinha;
import com.ifood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

		if (cozinha != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public void adicionar(@RequestBody	 Cozinha cozinha) {
		Cozinha cozinhaSalva = repository.salvar(cozinha);
		ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
	}
}
