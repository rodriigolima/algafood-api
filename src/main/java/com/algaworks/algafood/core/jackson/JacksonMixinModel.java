package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.api.mixin.CidadeMixin;
import com.algaworks.algafood.api.mixin.CozinhaMixin;
import com.algaworks.algafood.api.mixin.RestauranteMixin;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModel extends SimpleModule {
    public JacksonMixinModel() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
