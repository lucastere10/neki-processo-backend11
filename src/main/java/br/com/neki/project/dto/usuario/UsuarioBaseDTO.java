package br.com.neki.project.dto.usuario;

import br.com.neki.project.model.Enum.EnumTipoUsuario;

public abstract class UsuarioBaseDTO {

    protected Long id;
    protected String nome;
    protected String telefone;
    protected String email;
    protected EnumTipoUsuario perfil;
    protected String imagemUrl;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumTipoUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(EnumTipoUsuario perfil) {
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

}