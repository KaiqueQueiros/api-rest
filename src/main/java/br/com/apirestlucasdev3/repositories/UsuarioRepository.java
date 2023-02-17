package br.com.apirestlucasdev3.repositories;

import br.com.apirestlucasdev3.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Boolean existsByLogin(String login);

}
