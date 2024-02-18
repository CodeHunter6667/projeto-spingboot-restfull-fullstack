package br.com.rafelehlert.projeto_spring_restfull.dtos;

import br.com.rafelehlert.projeto_spring_restfull.model.Telefone;

public class TelefoneDTO {
    
    private Long id;
    private String numero;

    public TelefoneDTO(Telefone entity){
        id = entity.getId();
        numero = entity.getNumero();
    }
    
    public TelefoneDTO(Long id, String numero) {
        this.id = id;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
