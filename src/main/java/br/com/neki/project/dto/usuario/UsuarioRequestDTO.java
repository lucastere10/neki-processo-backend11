package br.com.neki.project.dto.usuario;

import java.util.List;

import br.com.neki.project.dto.log.LogRequestDTO;

public class UsuarioRequestDTO extends UsuarioBaseDTO {

    private List<LogRequestDTO> logs;
    private String senha;

    public UsuarioRequestDTO() {
    }

    // public UsuarioRequestDTO(List<LogRequestDTO> logs, List<PedidoRequestDTO> pedidos) {
    //     this.logs = logs;
    //     this.pedidos = pedidos;
    // }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<LogRequestDTO> getLogs() {
        return logs;
    }

    public void setLogs(List<LogRequestDTO> logs) {
        this.logs = logs;
    }

}