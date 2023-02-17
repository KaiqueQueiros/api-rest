package br.com.apirestlucasdev3.entities;

import br.com.apirestlucasdev3.entities.dto.UsuarioSaveAndUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    private String senha;

    public Usuario(UsuarioSaveAndUpdateDTO saveAndUpdateDTO) {
        this.login = saveAndUpdateDTO.getLogin();
        this.senha = saveAndUpdateDTO.getSenha();
    }

    public void update(UsuarioSaveAndUpdateDTO saveAndUpdateDTO) {
        this.login = saveAndUpdateDTO.getLogin();
        this.senha = saveAndUpdateDTO.getSenha();
    }

}
