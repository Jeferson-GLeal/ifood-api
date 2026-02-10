package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cozinha;
import com.ifood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> listar() {
        return repository.listar();
    }

    public Cozinha buscar(long id) {
        Cozinha cozinha = repository.buscar(id);
        return cozinha;
    }

    public void adicionar(Cozinha cozinha) {
        repository.salvar(cozinha);
    }

    public void excluir(Long cozinhaId) {
        try {
            repository.remover(cozinhaId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Cadastro de cozinha com o codigo %d inexistente!", cozinhaId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Cozinha de codigo %d nao pode ser removida, pois esta em uso!", cozinhaId));
        }
    }


}
