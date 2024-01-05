package br.com.neki.project.dto.PerfilSkill;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilSkillBaseDto {

    private Integer PerfilSkillId;

    @NotNull
    private String skillNome; 

    @NotNull
    private String PerfilSkillVersao;
}
