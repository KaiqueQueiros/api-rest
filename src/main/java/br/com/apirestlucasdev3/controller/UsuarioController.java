package br.com.apirestlucasdev3.controller;

import br.com.apirestlucasdev3.entities.dto.UsuarioSaveAndUpdateDTO;
import br.com.apirestlucasdev3.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public String test() {
        return "OK";
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody UsuarioSaveAndUpdateDTO saveAndUpdateDTO) {
        return service.salvar(saveAndUpdateDTO);
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody UsuarioSaveAndUpdateDTO saveAndUpdateDTO, @PathVariable Long id) {
        return service.atualizar(saveAndUpdateDTO, id);
    }

}
