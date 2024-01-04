package br.com.neki.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Skill {

    // @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence", allocationSize = 1, initialValue = 10000)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;

    @Column(nullable = false, length = 255, unique = true)
    private String skillNome;

    @Column(nullable = false, unique = true)
    private String skillDescricao;
    
    @Column()
    private String skillUrl;

    @OneToMany(mappedBy = "skill")
    private List<PerfilSkill> perfilSkills;

}
