package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Test
	void deveAtribuir_QuandoCadastrarCozinhaComDadosCorreto() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

		assertNotNull(novaCozinha);
		assertNotNull(novaCozinha.getId());
	}

	@Test
	void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		assertThrows(ConstraintViolationException.class, ()-> cadastroCozinhaService.salvar(novaCozinha));
	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaEmUso() {
		EntidadeEmUsoException erroEsperado = assertThrows(EntidadeEmUsoException.class,
				() -> cadastroCozinhaService.excluir(1L));

		assertNotNull(erroEsperado);
	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaInexistente() {
		CozinhaNaoEncontradaException erroEsperado = assertThrows(CozinhaNaoEncontradaException.class,
				() -> cadastroCozinhaService.excluir(100L));
		
		assertNotNull(erroEsperado);
	}
}
