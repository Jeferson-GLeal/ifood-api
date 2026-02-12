package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cidade;
import com.ifood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> listar() { return repository.listar(); }

    public Cidade buscar(Long id) {
        Cidade cidade = repository.buscar(id);
        return cidade;
    }

    public Cidade adicionar(Cidade cidade) { return repository.adicionar(cidade); }

    public void excluir(Long id) {
        try {
            repository.remover(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("A Cidade de codigo %d nao foi encontrada!", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("A Cidade de codigo %d esta em uso!", id));
        }
    }
}
