package br.com.neki.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.project.dto.SkillDto.SkillRequestDto;
import br.com.neki.project.dto.SkillDto.SkillResponseDto;
import br.com.neki.project.service.SkillService;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin("*")
public class SkillController {
    

    @Autowired
    private SkillService skillService;

    // CREATE
    @PostMapping
    public ResponseEntity<SkillResponseDto> create(@RequestBody SkillRequestDto skill) {

        return ResponseEntity
                .status(201)
                .body(skillService.create(skill));
    }

    // FIND ALL
    @GetMapping
    public ResponseEntity<List<SkillResponseDto>> findAll() {
        return ResponseEntity
                .status(200)
                .body(skillService.findAll());
    }

    // FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity
                .status(200)
                .body(skillService.findById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDto> putMethodName(@PathVariable Integer id,
            @RequestBody SkillRequestDto skill) {
        return ResponseEntity
                .status(200)
                .body(skillService.update(id, skill));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        skillService.delete(id);
        return ResponseEntity
            .status(204)
            .build();
    }

}
