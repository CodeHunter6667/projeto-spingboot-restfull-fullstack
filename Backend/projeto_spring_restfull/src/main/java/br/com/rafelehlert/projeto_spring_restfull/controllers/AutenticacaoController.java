package br.com.rafelehlert.projeto_spring_restfull.controllers;

import br.com.rafelehlert.projeto_spring_restfull.dtos.DadosAutenticacao;
import br.com.rafelehlert.projeto_spring_restfull.dtos.TokenDTO;
import br.com.rafelehlert.projeto_spring_restfull.model.Usuario;
import br.com.rafelehlert.projeto_spring_restfull.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados){
        var authenticationnToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
        var authentication = manager.authenticate(authenticationnToken);

        var tokenJWT = service.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }


}
