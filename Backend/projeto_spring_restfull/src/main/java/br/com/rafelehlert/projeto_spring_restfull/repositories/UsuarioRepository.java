package br.com.rafelehlert.projeto_spring_restfull.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.rafelehlert.projeto_spring_restfull.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT obj FROM Usuario obj WHERE UPPER(obj.login) LIKE UPPER(CONCAT('%', :login, '%'))")
    Page<Usuario> procurarPorLogin(String login, Pageable pageable);

    @Query("SELECT obj FROM Usuario obj WHERE UPPER(obj.nome) LIKE UPPER(CONCAT('%', :login, '%'))")
    Page<Usuario> procurarPorNome(String nome, Pageable pageable);
}
