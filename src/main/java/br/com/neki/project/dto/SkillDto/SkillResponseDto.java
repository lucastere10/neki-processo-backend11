package br.com.neki.project.dto.SkillDto;

import java.util.List;

import br.com.neki.project.dto.PerfilSkill.PerfilSkillBaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillResponseDto extends SkillBaseDto{
    
    private Integer skillId;

    //private List<PerfilSkillBaseDto> PerfilSkills;

}
