package br.com.neki.project.dto.SkillDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SkillBaseDto {

    @NotNull
    @Size(max = 255)
    private String skillNome;

    private String skillDescricao;

    private String skillUrl;
}

