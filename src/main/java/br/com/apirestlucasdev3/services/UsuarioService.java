package br.com.apirestlucasdev3.services;

import br.com.apirestlucasdev3.entities.Usuario;
import br.com.apirestlucasdev3.entities.dto.UsuarioSaveAndUpdateDTO;
import br.com.apirestlucasdev3.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> salvar(UsuarioSaveAndUpdateDTO saveDTO) {
        try {
            if (saveDTO == null) {
                return ResponseEntity.noContent().build();
            }
            if (existeUsuarioNoBanco(saveDTO.getLogin())) {
                return ResponseEntity.badRequest().body("Usuario já cadastrado no banco!");
            }
            Usuario usuario = new Usuario(saveDTO);
            repository.save(usuario);
            return ResponseEntity.ok().body("Usuario cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<?> atualizar(UsuarioSaveAndUpdateDTO saveAndUpdateDTO, Long id) {
        try {
            if (saveAndUpdateDTO == null) {
                return ResponseEntity.noContent().build();
            }
            Usuario usuarioEncontrado = repository.findById(id).orElse(null);
            if (usuarioEncontrado == null) {
                return ResponseEntity.notFound().build();
            }
            if (existeUsuarioNoBanco(saveAndUpdateDTO.getLogin())) {
                return ResponseEntity.badRequest().body("Falha ao atualizar usuario. Login já em uso!");
            }
            usuarioEncontrado.update(saveAndUpdateDTO);
            repository.save(usuarioEncontrado);
            return ResponseEntity.ok().body("Usuario cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }


    public Boolean existeUsuarioNoBanco(String login) {
        return repository.existsByLogin(login);
    }

}
