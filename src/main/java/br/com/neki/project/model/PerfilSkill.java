package br.com.neki.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PerfilSkill {
    
    //@SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence", allocationSize = 1, initialValue = 10000)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfilSkill_id")
    private Integer perfilSkillId;


    @Column(nullable = false)
    private String perfilSkillVersao;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    @JsonBackReference
    private Skill skill;

}
 