package jbraga.pessoa.controller;

import jbraga.pessoa.dto.UsuarioDTO;
import jbraga.pessoa.exception.RegraNegocioException;
import jbraga.pessoa.model.entity.Usuario;
import jbraga.pessoa.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = Usuario.builder()
                    .nome(dto.getNome())
                    .cpf(dto.getCpf())
                    .idade(dto.getIdade())
                    .build();
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.obterPorId(id);

        if (!usuario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(usuario.get());
    }
    /*jc*/
}
