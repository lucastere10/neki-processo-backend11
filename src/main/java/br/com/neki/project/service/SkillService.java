package br.com.neki.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.project.dto.SkillDto.SkillRequestDto;
import br.com.neki.project.dto.SkillDto.SkillResponseDto;
import br.com.neki.project.dto.log.LogRequestDTO;
import br.com.neki.project.model.Skill;
import br.com.neki.project.model.Enum.EnumLog;
import br.com.neki.project.model.Enum.EnumTipoEntidade;
import br.com.neki.project.repository.SkillRepository;
import javax.transaction.Transactional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private ModelMapper mapper;

// CRUD

    // Create
    @Transactional
    public SkillResponseDto create(SkillRequestDto skillRequest) {
        Skill skillModel = mapper.map(skillRequest, Skill.class);

        skillModel = skillRepository.save(skillModel);

        // Fazer Auditoria
        LogRequestDTO logRequestDTO = new LogRequestDTO();
        logService.adicionar(logService.verificarUsuarioLogado(), logRequestDTO, EnumLog.CREATE,
                EnumTipoEntidade.SKILL, "",
                logService.mapearObjetoParaString(skillModel));

        return mapper.map(skillModel, SkillResponseDto.class);
    }

    // FindAll
    public List<SkillResponseDto> findAll() {
        List<Skill> skills = skillRepository.findAll();

        return skills.stream().map(skill -> mapper.map(skill, SkillResponseDto.class))
                .collect(Collectors.toList());
    }

    public SkillResponseDto findById(Integer id) {
        Optional<Skill> optSkill = skillRepository.findById(id);

        return mapper.map(optSkill.get(), SkillResponseDto.class);
    }

    // Update
    @Transactional
    public SkillResponseDto update(Integer id, SkillRequestDto skillRequest) {

        Skill skillBase = mapper.map(findById(id), Skill.class);
        Skill skillModel = mapper.map(skillRequest, Skill.class);

        skillModel.setSkillId(id);

        if (skillModel.getSkillNome() == null) {
            skillModel.setSkillNome(skillBase.getSkillNome());
        }

        if (skillModel.getSkillDescricao() == null) {
            skillModel.setSkillDescricao(skillBase.getSkillDescricao());
        }

        if (skillModel.getSkillUrl() == null) {
            skillModel.setSkillUrl(skillBase.getSkillUrl());
        }

        skillModel = skillRepository.save(skillModel);

        // Fazer Auditoria
        LogRequestDTO logRequestDTO = new LogRequestDTO();
        logService.adicionar(logService.verificarUsuarioLogado(), logRequestDTO, EnumLog.UPDATE,
                EnumTipoEntidade.SKILL,
                logService.mapearObjetoParaString(skillBase),
                logService.mapearObjetoParaString(skillModel));

        return mapper.map(skillModel, SkillResponseDto.class);
    }

    // Delete
    public void delete(Integer id) {
        findById(id);
        skillRepository.deleteById(id);

        // Fazer Auditoria
        LogRequestDTO logRequestDTO = new LogRequestDTO();
        logService.adicionar(logService.verificarUsuarioLogado(), logRequestDTO, EnumLog.DELETE,
                EnumTipoEntidade.SKILL, "", "");

    }

}
