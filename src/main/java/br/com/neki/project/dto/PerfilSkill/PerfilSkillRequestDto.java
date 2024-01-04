package br.com.neki.project.dto.PerfilSkill;

import br.com.neki.project.dto.usuario.UsuarioRequestDTO;
import br.com.neki.project.dto.SkillDto.SkillRequestDto;

import javax.validation.constraints.NotNull;

public class PerfilSkillRequestDto extends PerfilSkillBaseDto {

    @NotNull
    private UsuarioRequestDTO usuario;

    @NotNull
    private SkillRequestDto skill;

}
