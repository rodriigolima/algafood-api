package com.algaworks.algafood.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.io.Serial;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException{


    private BindingResult bindingResult;
}
