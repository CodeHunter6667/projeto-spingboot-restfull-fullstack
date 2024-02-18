package br.com.rafelehlert.projeto_spring_restfull.services.UsuarioNotFoundException;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String msg){
        super(msg);
    }
}
