package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Restaurante;
import com.ifood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public List<Restaurante> listar() {
        return repository.listar();
    }

    public Restaurante buscar(long id) {
         Restaurante restaurante = repository.buscar(id);
         return restaurante;
    }

    public void adicionar(Restaurante restaurante) {
        repository.adicionar(restaurante);
    }

    public void excluir(Long id) {
        try {
            repository.buscar(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Cadastro de Restaurante de codigo %d inexistente", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de codigo %d nao pode ser removido, pois esta em uso!", id));
        }
    }
}
