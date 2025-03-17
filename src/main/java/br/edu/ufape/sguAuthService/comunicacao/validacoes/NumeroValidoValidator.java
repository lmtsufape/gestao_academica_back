package br.edu.ufape.sguAuthService.comunicacao.validacoes;

import br.edu.ufape.sguAuthService.comunicacao.annotations.NumeroValido;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroValidoValidator implements ConstraintValidator<NumeroValido, String> {

    // Expressão regular para validar o formato (XX)XXXXX-XXXX
    private static final String PHONE_NUMBER_PATTERN = "^\\(\\d{2}\\) \\d{5}-\\d{4}$";


    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false; // Número de telefone não pode ser nulo ou vazio
        }
        // Verifica se o número de telefone corresponde ao padrão
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }

}
