package br.com.rafelehlert.projeto_spring_restfull.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.rafelehlert.projeto_spring_restfull.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT obj FROM Usuario obj WHERE UPPER(obj.login) LIKE UPPER(CONCAT('%', :login, '%'))")
    Usuario procurarPorLogin(String login);

    @Query("SELECT obj FROM Usuario obj WHERE UPPER(obj.nome) LIKE UPPER(CONCAT('%', :nome, '%'))")
    Page<Usuario> procurarPorNome(String nome, Pageable pageable);

    @Query("SELECT u FROM Usuario u WHERE u.login = ?1")
    Usuario procuraUserPorLogin(String login);

    UserDetails findByLogin(String login);
}
