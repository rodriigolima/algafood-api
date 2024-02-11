package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.model.ProdutoDTO;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Autowired
	private AlgaLinks algaLinks;

	@GetMapping
	public CollectionModel<ProdutoDTO> listar(@PathVariable Long restauranteId, @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		List<Produto> todosProdutos = null;

		if (incluirInativos)
			todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		else
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);

		return produtoModelAssembler.toCollectionModel(todosProdutos).add(algaLinks.linkToProdutos(restauranteId));
	}

	@GetMapping("/{produtoId}")
	public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

		return produtoModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);

		produto = cadastroProduto.salvar(produto);

		return produtoModelAssembler.toModel(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {

		Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

		produtoAtual = cadastroProduto.salvar(produtoAtual);

		return produtoModelAssembler.toModel(produtoAtual);
	}
}
