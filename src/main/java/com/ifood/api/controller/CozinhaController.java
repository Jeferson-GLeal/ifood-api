package com.ifood.api.controller;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cozinha;
import com.ifood.domain.repository.CozinhaRepository;
import com.ifood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;

	@Autowired
	private CadastroCozinhaService cadastro;

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
		Cozinha cozinhaSalva = cadastro.salvar(cozinha);
		ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable long cozinhaId, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = repository.buscar(cozinhaId);

		if (cozinhaAtual != null) {
			/**
			 * O BeanUtils faz a copia das propriedades de um objeto para outro, ignorando o campo id.
			 */
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			repository.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> excluir(@PathVariable long cozinhaId) {

		try {
			cadastro.excluir(cozinhaId);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
