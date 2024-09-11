package br.edu.ufape.sgi.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

public class ExceptionUtil {

    public static void handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException constraintViolationException) {
                String message= constraintViolationException.getMessage();
                if(message.contains("usuario_cpf_key")){throw new UniqueConstraintViolationException("cpf", "CPF já cadastrado!");}
                else if (message.contains("usuario_email_key")){throw new UniqueConstraintViolationException("email", "Email já cadastrado!");}
                else if (message.contains("aluno_matricula_key")){throw new UniqueConstraintViolationException("matricula", "Matrícula já cadastrada!");}
                else throw new UniqueConstraintViolationException("desconhecido", "Violação de restrição única desconhecida");

                }
        throw new IllegalArgumentException("Erro de integridade de dados!");
            }

    }
