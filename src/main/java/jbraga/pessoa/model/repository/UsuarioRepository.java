package jbraga.pessoa.model.repository;

import jbraga.pessoa.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByCpf(String cpf);

    Optional<Usuario> findByCpf(String cpf);
}
/*jc*/
