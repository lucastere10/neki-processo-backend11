package br.com.neki.project.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.neki.project.dto.PerfilSkill.PerfilSkillRequestDto;
import br.com.neki.project.dto.PerfilSkill.PerfilSkillResponseDto;
import br.com.neki.project.service.PerfilSkillService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/perfilskills")
@CrossOrigin("*")
public class PerfilSkillController {

    @Autowired
    private PerfilSkillService perfilSkillService;

    // CREATE
    @PostMapping
    public ResponseEntity<PerfilSkillResponseDto> create(@RequestBody PerfilSkillRequestDto perfilSkillRequestDto) {
        PerfilSkillResponseDto perfilSkillResponseDto = perfilSkillService.create(perfilSkillRequestDto,
                perfilSkillRequestDto.getSkillNome());
        return new ResponseEntity<>(perfilSkillResponseDto, HttpStatus.CREATED);
    }

    // FIND ALL
    @GetMapping
    public ResponseEntity<List<PerfilSkillResponseDto>> findAll() {
        return ResponseEntity
                .status(200)
                .body(perfilSkillService.findAll());
    }

    // FIND ALL
    @GetMapping("/user")
    public ResponseEntity<List<PerfilSkillResponseDto>> findAllByUser() {
        return ResponseEntity
                .status(200)
                .body(perfilSkillService.findAllPerfilSkillsForLoggedInUser());
    }

    
    // FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PerfilSkillResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(perfilSkillService.findById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PerfilSkillResponseDto> putMethodName(@PathVariable Integer id,
            @RequestBody PerfilSkillRequestDto skill) {
        return ResponseEntity
                .status(200)
                .body(perfilSkillService.update(id, skill));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        perfilSkillService.delete(id);
        return ResponseEntity
                .status(204)
                .build();
    }

}
