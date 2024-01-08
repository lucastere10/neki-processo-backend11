package br.com.neki.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.project.model.PerfilSkill;

public interface PerfilSkillRepository extends JpaRepository<PerfilSkill, Integer> {
    List<PerfilSkill> findByUsuarioEmail(String email);
    Optional<PerfilSkill> findBySkill_SkillNomeAndPerfilSkillVersao(String skillNome, String versao);
    Optional<PerfilSkill> findBySkill_SkillNomeAndPerfilSkillVersaoAndUsuario_Email(String skillNome, String versao, String email);
}

