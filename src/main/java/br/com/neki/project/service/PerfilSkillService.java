package br.com.neki.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.neki.project.dto.PerfilSkill.PerfilSkillRequestDto;
import br.com.neki.project.dto.PerfilSkill.PerfilSkillResponseDto;
import br.com.neki.project.dto.log.LogRequestDTO;
import br.com.neki.project.dto.usuario.UsuarioRequestDTO;
import br.com.neki.project.model.PerfilSkill;
import br.com.neki.project.model.Skill;
import br.com.neki.project.model.Usuario;
import br.com.neki.project.model.Enum.EnumLog;
import br.com.neki.project.model.Enum.EnumTipoEntidade;
import br.com.neki.project.model.exceptions.ResourceBadRequest;
import br.com.neki.project.repository.PerfilSkillRepository;
import br.com.neki.project.repository.SkillRepository;

import javax.transaction.Transactional;

@Service
public class PerfilSkillService {

    @Autowired
    private PerfilSkillRepository perfilSkillRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper mapper;

    // CRUD
    // Create
    @Transactional
    public PerfilSkillResponseDto create(PerfilSkillRequestDto perfilSkillRequest, String skillNome) {
        PerfilSkill perfilSkillModel = mapper.map(perfilSkillRequest, PerfilSkill.class);

        // Definir usuario usando verificarUsuarioLogado
        Usuario loggedUser = logService.verificarUsuarioLogado();
        perfilSkillModel.setUsuario(loggedUser);

        // Get the email from the logged in user
        String email = loggedUser.getEmail();

        // verificar se a combinação de skillNome, PerfilSkillVersao e email já existe
        Optional<PerfilSkill> existingEntry = perfilSkillRepository
                .findBySkill_SkillNomeAndPerfilSkillVersaoAndUsuario_Email(skillNome,
                        perfilSkillRequest.getPerfilSkillVersao(), email);
        if (existingEntry.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A combinação de Habilidade e Versão para esse usuário já existe");
        }

        // Definir skill com skillNome
        Optional<Skill> skillOptional = skillRepository.findBySkillNome(skillNome);
        if (skillOptional.isPresent()) {
            Skill skill = skillOptional.get();
            perfilSkillModel.setSkill(skill);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Essa habilidade não existe");
        }

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

    public List<PerfilSkillResponseDto> findAllPerfilSkillsForLoggedInUser() {
        Usuario loggedUser = logService.verificarUsuarioLogado();
        List<PerfilSkill> perfilSkills = perfilSkillRepository.findByUsuarioEmail(loggedUser.getEmail());
        return perfilSkills.stream()
                .map(perfilSkill -> mapper.map(perfilSkill, PerfilSkillResponseDto.class))
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

        // verificar se a combinação de skillNome e PerfilSkillVersao já existe
        Optional<PerfilSkill> existingEntry = perfilSkillRepository.findBySkill_SkillNomeAndPerfilSkillVersao(
                perfilSkillRequest.getSkillNome(),
                perfilSkillRequest.getPerfilSkillVersao());
        if (existingEntry.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A combinação de Habilidade e Versão já existe");
        }

        perfilSkillModel.setPerfilSkillId(id);
        if (perfilSkillModel.getPerfilSkillVersao() == null) {
            perfilSkillModel.setPerfilSkillVersao(perfilSkillBase.getPerfilSkillVersao());
        }

        // definir habilidade
        perfilSkillModel.setSkill(perfilSkillBase.getSkill());

        // Definir usuario usando verificarUsuarioLogado
        Usuario loggedUser = logService.verificarUsuarioLogado();
        perfilSkillModel.setUsuario(loggedUser);

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
