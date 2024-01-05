package br.com.neki.project.dto.PerfilSkill;

import br.com.neki.project.dto.usuario.UsuarioRequestDTO;
import lombok.Getter;
import lombok.Setter;
import br.com.neki.project.dto.SkillDto.SkillRequestDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PerfilSkillRequestDto extends PerfilSkillBaseDto {

    @NotNull
    private UsuarioRequestDTO usuario;

    @NotNull
    private SkillRequestDto skill;

}
