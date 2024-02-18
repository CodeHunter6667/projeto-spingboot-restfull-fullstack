package br.com.rafelehlert.projeto_spring_restfull.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rafelehlert.projeto_spring_restfull.dtos.UsuarioDto;
import br.com.rafelehlert.projeto_spring_restfull.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> listaTodos(Pageable pageable){
        Page<UsuarioDto> dto= service.getAll(pageable);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<UsuarioDto> listaPorLogin(@RequestParam(name = "login", defaultValue = "") String login){
        UsuarioDto dto = service.procuraPorLogin(login);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/nome")
    public ResponseEntity<Page<UsuarioDto>> listaPorNome(@RequestParam(name = "nome", defaultValue = "") String nome, Pageable pageable){
        Page<UsuarioDto> dto = service.procuraPorNome(nome, pageable);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> novoUsuario(@RequestBody UsuarioDto dto){
        dto = service.novoUsuario(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> atualizaUsuario(@PathVariable Long id, @RequestBody UsuarioDto dto){
        dto = service.atualizaUsuario(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaUsuario(Long id){
        service.deletaUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
