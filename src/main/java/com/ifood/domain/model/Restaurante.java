package com.ifood.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({ "id", "nome", "taxaFrete", "cozinha" })
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
}
