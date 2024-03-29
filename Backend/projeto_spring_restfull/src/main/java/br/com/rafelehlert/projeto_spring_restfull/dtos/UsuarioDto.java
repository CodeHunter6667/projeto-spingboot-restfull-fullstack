package br.com.rafelehlert.projeto_spring_restfull.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.rafelehlert.projeto_spring_restfull.model.Telefone;
import br.com.rafelehlert.projeto_spring_restfull.model.Usuario;

public class UsuarioDto {
    
    private Long id;
    private String login;
    private String senha;
    private String nome;
    private List<Telefone> telefones;

    public UsuarioDto(Usuario entity){
        id = entity.getId();
        login = entity.getLogin();
        senha = entity.getSenha();
        nome = entity.getNome();
        telefones = entity.getTelefones();
    }

    public UsuarioDto(Long id, String login, String senha, String nome, List<Telefone> telefones) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getSenha() {
        return senha;
    }
    public String getNome() {
        return nome;
    }
    public List<Telefone> getTelefones() {
        return telefones;
    }
}
