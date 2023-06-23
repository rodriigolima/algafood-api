package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.FotoProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto, InputStream dadosArquivo) {
        Long restauranteId = fotoProduto.getRestauranteId();
        Long produtoId = fotoProduto.getProduto().getId();
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(fotoProduto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }



        fotoProduto.setNomeArquivo(nomeNovoArquivo);
        fotoProduto = produtoRepository.save(fotoProduto);

        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(fotoProduto.getNomeArquivo()).inputStream(dadosArquivo).build();


        fotoStorage.substituir(nomeArquivoExistente, novaFoto);

        return fotoProduto;
    }
    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {
        FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);

        produtoRepository.delete(foto);
        produtoRepository.flush();

        fotoStorage.remover(foto.getNomeArquivo());
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
    }


}
