package br.com.neki.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.project.dto.PerfilSkill.PerfilSkillRequestDto;
import br.com.neki.project.dto.PerfilSkill.PerfilSkillResponseDto;
import br.com.neki.project.dto.log.LogRequestDTO;
import br.com.neki.project.model.PerfilSkill;
import br.com.neki.project.model.Enum.EnumLog;
import br.com.neki.project.model.Enum.EnumTipoEntidade;
import br.com.neki.project.repository.PerfilSkillRepository;
import javax.transaction.Transactional;

@Service
public class PerfilSkillService {
    
    @Autowired
    private PerfilSkillRepository perfilSkillRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private ModelMapper mapper;
    
    // CRUD
 
    // Create
    @Transactional
    public PerfilSkillResponseDto create(PerfilSkillRequestDto perfilSkillRequest) {
        PerfilSkill perfilSkillModel = mapper.map(perfilSkillRequest, PerfilSkill.class);

        perfilSkillModel = perfilSkillRepository.save(perfilSkillModel);

        // Fazer Auditoria
        LogRequestDTO logRequestDTO = new LogRequestDTO();
        logService.adicionar(logService.verificarUsuarioLogado(), logRequestDTO, EnumLog.CREATE,
                EnumTipoEntidade.SKILL, "",
                logService.mapearObjetoParaString(perfilSkillModel));

        return mapper.map(perfilSkillModel, PerfilSkillResponseDto.class);
    }

    // FindAll
    public List<PerfilSkillResponseDto> findAll() {
        List<PerfilSkill> perfilSkills = perfilSkillRepository.findAll();

        return perfilSkills.stream().map(perfilSkill -> mapper.map(perfilSkill, PerfilSkillResponseDto.class))
                .collect(Collectors.toList());
    }

    public PerfilSkillResponseDto findById(Integer id) {
        Optional<PerfilSkill> optPerfilSkill = perfilSkillRepository.findById(id);

        return mapper.map(optPerfilSkill.get(), PerfilSkillResponseDto.class);
    }

    // Update
    @Transactional
    public PerfilSkillResponseDto update(Integer id, PerfilSkillRequestDto perfilSkillRequest) {

        PerfilSkill perfilSkillBase = mapper.map(findById(id), PerfilSkill.class);
        PerfilSkill perfilSkillModel = mapper.map(perfilSkillRequest, PerfilSkill.class);

        perfilSkillModel.setPerfilSkillId(id);
        if (perfilSkillModel.getPerfilSkillVersao() == null) {
            perfilSkillModel.setPerfilSkillVersao(perfilSkillBase.getPerfilSkillVersao());
        }

        perfilSkillModel = perfilSkillRepository.save(perfilSkillModel);

        // Fazer Auditoria
        LogRequestDTO logRequestDTO = new LogRequestDTO();
        logService.adicionar(logService.verificarUsuarioLogado(), logRequestDTO, EnumLog.UPDATE,
                EnumTipoEntidade.SKILL,
                logService.mapearObjetoParaString(perfilSkillBase),
                logService.mapearObjetoParaString(perfilSkillModel));

        return mapper.map(perfilSkillModel, PerfilSkillResponseDto.class);
    }

    // Delete
    public void delete(Integer id) {
        findById(id);
        perfilSkillRepository.deleteById(id);
    }
    
}
