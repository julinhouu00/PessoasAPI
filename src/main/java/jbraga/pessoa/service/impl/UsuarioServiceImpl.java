package jbraga.pessoa.service.impl;

import java.util.Optional;

import jbraga.pessoa.exception.RegraNegocioException;
import jbraga.pessoa.model.entity.Usuario;
import jbraga.pessoa.model.repository.UsuarioRepository;
import jbraga.pessoa.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarCpf(usuario.getCpf());
        validarIdade(usuario.getIdade());
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void validarCpf(String cpf) {
        boolean existe = repository.existsByCpf(cpf);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este CPF.");
        }
    }

    private void validarIdade(Integer idade) {
        if (idade < 18) {
            throw new RegraNegocioException("O usuário deve ter pelo menos 18 anos.");
        }
    }
    /*jc*/
}
