package jbraga.pessoa.service;

import jbraga.pessoa.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario salvarUsuario(Usuario usuario);

    Optional<Usuario> obterPorId(Long id);

    void validarCpf(String cpf);
    /*jc*/
}
