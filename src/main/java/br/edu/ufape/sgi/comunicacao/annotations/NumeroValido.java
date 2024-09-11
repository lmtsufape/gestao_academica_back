package br.edu.ufape.sgi.comunicacao.annotations;

import br.edu.ufape.sgi.comunicacao.validacoes.NumeroValidoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = NumeroValidoValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface NumeroValido {
    String message() default "Número de telefone inválido. O formato deve ser (XX) XXXXX-XXXX"; // Mensagem de erro
    Class<?>[] groups() default {}; // Usado para validações em grupos
    Class<? extends Payload>[] payload() default {}; // Informações adicionais sobre a validação
}
