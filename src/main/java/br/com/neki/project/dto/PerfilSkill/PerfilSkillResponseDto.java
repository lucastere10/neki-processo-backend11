package br.com.neki.project.dto.PerfilSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.neki.project.dto.usuario.UsuarioResponseDTO;
import br.com.neki.project.dto.SkillDto.SkillResponseDto;

import javax.validation.constraints.NotNull;

public class PerfilSkillResponseDto extends PerfilSkillBaseDto {

    @NotNull
    private UsuarioResponseDTO usuario;

    @NotNull
    @JsonBackReference
    private SkillResponseDto skill;

}
