package com.ifood.domain.service;

import com.ifood.domain.exception.EntityInUseException;
import com.ifood.domain.exception.EntityNotFoundException;
import com.ifood.domain.model.Cozinha;
import com.ifood.domain.model.Restaurante;
import com.ifood.domain.repository.CozinhaRepository;
import com.ifood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return repository.findAll();
    }

    public Restaurante buscar(Long id) {
        Optional<Restaurante> restaurante = repository.findById(id);
        return restaurante.get();
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Nao existe cadastro de cozinha com o codigo %d", cozinhaId)));

        restaurante.setCozinha(cozinha);

        return repository.save(restaurante);
    }

    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Não existe um cadastro de Restaurante com código %d", id)
                ));

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de codigo %d nao pode ser removido, pois esta em uso!", id));
        }
    }
}
