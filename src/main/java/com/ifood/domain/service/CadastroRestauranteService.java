package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cozinha;
import com.ifood.domain.model.Restaurante;
import com.ifood.domain.repository.CozinhaRepository;
import com.ifood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(long id) {
         Restaurante restaurante = restauranteRepository.buscar(id);
         return restaurante;
    }

    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntityNotFoundException(String.format("Nao existe cadastro de cozinha com o codigo %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.adicionar(restaurante);
    }

    public void excluir(Long id) {
        try {
            restauranteRepository.buscar(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("Cadastro de Restaurante de codigo %d inexistente", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de codigo %d nao pode ser removido, pois esta em uso!", id));
        }
    }
}
