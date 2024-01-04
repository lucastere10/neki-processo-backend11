package br.com.neki.project.dto.PerfilSkill;

import br.com.neki.project.dto.usuario.UsuarioBaseDTO;
import br.com.neki.project.dto.SkillDto.SkillBaseDto;
import javax.validation.constraints.NotNull;

public class PerfilSkillRequestDto extends PerfilSkillBaseDto {

    @NotNull
    private UsuarioBaseDTO usuario;

    @NotNull
    private SkillBaseDto skill;

}
