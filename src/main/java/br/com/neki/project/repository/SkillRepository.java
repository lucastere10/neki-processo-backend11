package br.com.neki.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.project.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    
}
