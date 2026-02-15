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

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Nao existe cadastro de cozinha com o codigo %d", cozinhaId)));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public void excluir(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Não existe um cadastro de Restaurante com código %d", id)
                ));

        try {
            restauranteRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurante de codigo %d nao pode ser removido, pois esta em uso!", id));
        }
    }
}
