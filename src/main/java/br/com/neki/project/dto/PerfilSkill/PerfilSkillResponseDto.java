package br.com.neki.project.dto.PerfilSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.neki.project.dto.usuario.UsuarioBaseDTO;
import br.com.neki.project.dto.SkillDto.SkillBaseDto;
import javax.validation.constraints.NotNull;

public class PerfilSkillResponseDto extends PerfilSkillBaseDto {

    @NotNull
    private UsuarioBaseDTO usuario;

    @NotNull
    @JsonBackReference
    private SkillBaseDto skill;

}
