package br.com.neki.project.dto.usuario;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.neki.project.dto.log.LogResponseDTO;

public class UsuarioResponseDTO extends UsuarioBaseDTO {

    @JsonBackReference
    private List<LogResponseDTO> logs;
    
    private Date dataCadastro;

    public UsuarioResponseDTO() {
    }

    // public UsuarioResponseDTO(List<LogResponseDTO> logs, List<PedidoResponseDTO> pedidos) {
    //     this.logs = logs;
    //     this.pedidos = pedidos;
    // }

    public List<LogResponseDTO> getLogs() {
        return logs;
    }

    public void setLogs(List<LogResponseDTO> logs) {
        this.logs = logs;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}