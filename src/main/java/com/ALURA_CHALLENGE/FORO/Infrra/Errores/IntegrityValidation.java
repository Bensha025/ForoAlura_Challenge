package com.ALURA_CHALLENGE.FORO.Infrra.Errores;

public class IntegrityValidation extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public IntegrityValidation(String s){
        super(s);
    }
}


