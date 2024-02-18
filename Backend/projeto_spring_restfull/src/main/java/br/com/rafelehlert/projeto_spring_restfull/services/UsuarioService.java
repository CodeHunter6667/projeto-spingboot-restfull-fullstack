package br.com.rafelehlert.projeto_spring_restfull.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafelehlert.projeto_spring_restfull.dtos.UsuarioDto;
import br.com.rafelehlert.projeto_spring_restfull.model.Usuario;
import br.com.rafelehlert.projeto_spring_restfull.repositories.UsuarioRepository;
import br.com.rafelehlert.projeto_spring_restfull.services.UsuarioNotFoundException.UsuarioNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public List<UsuarioDto> getAll(){
        List<Usuario> result = repository.findAll();
        return result.stream().map(x -> new UsuarioDto(x)).toList();
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDto> procuraPorNome(String nome, Pageable pageable){
        Page<Usuario> result = repository.procurarPorNome(nome, pageable);
        return result.map(x -> new UsuarioDto(x));
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDto> procuraPorLogin(String login, Pageable pageable){
        Page<Usuario> result = repository.procurarPorLogin(login, pageable);
        return result.map(x -> new UsuarioDto(x));
    }

    @Transactional
    public UsuarioDto novoUsuario(UsuarioDto dto){
        Usuario usuario = new Usuario();
        copyDtoToEntity(dto, usuario);
        repository.save(usuario);
        return new UsuarioDto(usuario);
    }

    @Transactional
    public UsuarioDto atualizaUsuario(Long id, UsuarioDto dto){
        try{
        Usuario usuario = repository.getReferenceById(id);
        copyDtoToEntity(dto, usuario);
        repository.save(usuario);
        return new UsuarioDto(usuario);
        } catch(EntityNotFoundException e){
            throw new UsuarioNotFoundException("Usuario não encontrado");
        }
    }

    public void deletaUsuario(Long id){
        if (!repository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuario não encontrado");
        }
        repository.deleteById(id);
    }

    private static void copyDtoToEntity(UsuarioDto dto, Usuario entity){
        entity.setId(dto.getId());
        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
        entity.setNome(dto.getNome());
    }
}
