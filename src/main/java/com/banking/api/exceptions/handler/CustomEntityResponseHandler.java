package com.banking.api.exceptions.handler;

import com.banking.api.enums.TransactionType;
import com.banking.api.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomEntityResponseHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ProblemDetail handlerValidationError(MethodArgumentNotValidException ex){
//        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "um ou mais campos estão invalidos ou nulos, Digite corretamente e envie novamente!");
//        problem.setTitle("Error de validação de campos!");
//
//        Map<String, String> invalidFields = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> invalidFields.put(
//                error.getField(), error.getDefaultMessage()));
//
//        problem.setProperty("InvalidFields", invalidFields);
//
//        return problem;
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail problem(ResourceNotFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problem.setTitle("Recurso não encontrado!");

        problem.setProperty("timestamp",  java.time.OffsetDateTime.now());
        problem.setProperty("resourseName", ex.getResourceName());
        problem.setProperty("InvalidId", ex.getResourceId());
        return problem;
    }


    @ExceptionHandler(AccountValueException.class)
    public ProblemDetail problem(AccountValueException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());

        problem.setTitle("Não foi possivel concluir essa operação!");
        problem.setProperty("Account", ex.getAccountId());
        problem.setProperty("Required_value", ex.getRequiredValue());
        if (ex.getType() != TransactionType.DEPOSIT) {
            problem.setProperty("Actual_balance", ex.getBalance());
        }
        return problem;
    }

    @ExceptionHandler(AccountStatusException.class)
    public ProblemDetail problem(AccountStatusException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());

        problem.setTitle("Sua conta não tem autorização para realizar essa transferencia");

        problem.setProperty("Account_Number", ex.getAccountNumber());
        problem.setProperty("Status_Account", ex.getStatus());

        return problem;
    }

    @ExceptionHandler(DeleteExceptions.class)
    public ProblemDetail problem(DeleteExceptions ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());

        problem.setTitle(ex.getDescription());

        if (!(ex.getBalance() == null)) {
            problem.setProperty("Account", ex.getNumberAccount());
            problem.setProperty("Balance", ex.getBalance());
        }
        else {
            problem.setProperty("Accounts", ex.getPendingAccounts());
        }
        return problem;
    }
    @ExceptionHandler(TransferAccountExceptions.class)
    public ProblemDetail problem(TransferAccountExceptions ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());

        problem.setTitle("Não foi possível realizar essa operação!");
        problem.setProperty("destination_id", ex.getDestinationId());
        problem.setProperty("source_id", ex.getSourceId());
        problem.setProperty("transferType", ex.getType());

        return problem;

    }

    @ExceptionHandler(NotInsertKayValueExceptions.class)
    public ProblemDetail problemDetail(NotInsertKayValueExceptions ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());


        problem.setTitle("Não foi possivel inserir essa chave pix!");
        problem.setProperty("pix_key", ex.getKeyValue());
        problem.setProperty("pix_key_type", ex.getType());

        return problem;
    }
}
