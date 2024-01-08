
-- Criar Database
CREATE DATABASE neki_processo_api;

-- Criar Sequences
CREATE SEQUENCE usuario_id_seq;
CREATE SEQUENCE skill_id_seq;
CREATE SEQUENCE perfil_skill_id_seq;
CREATE SEQUENCE log_id_seq;

-------------------
-- CRIAR TABELAS --
-------------------

-- Usuario
CREATE TABLE public.usuario (
    id INTEGER NOT NULL DEFAULT nextval('usuario_id_seq') PRIMARY KEY,
    data_cadastro timestamp without time zone NOT NULL,
    email character varying(255) NOT NULL,
    imagem_url character varying(255),
    nome character varying(255) NOT NULL,
    perfil integer NOT NULL,
    senha character varying(255) NOT NULL,
    telefone character varying(255)
);

-- Skill
CREATE TABLE public.skill (
    skill_id INTEGER NOT NULL DEFAULT nextval('skill_id_seq') PRIMARY KEY,
    skill_descricao character varying(255) NOT NULL,
    skill_nome character varying(255) NOT NULL,
    skill_url character varying(255)
);

-- PerfilSkill
CREATE TABLE public.perfil_skill (
    perfil_skill_id INTEGER NOT NULL DEFAULT nextval('perfil_skill_id_seq') PRIMARY KEY,
    perfil_skill_versao character varying(255) NOT NULL,
    skill_id integer REFERENCES public.skill(skill_id),
    id_usuario bigint REFERENCES public.usuario(id)
);

-- Log
CREATE TABLE public.log (
    id_log INTEGER NOT NULL DEFAULT nextval('log_id_seq') PRIMARY KEY,
    data_acao timestamp without time zone NOT NULL,
    tipo_acao integer NOT NULL,
    tipo_entidade integer NOT NULL,
    valor_atual text,
    valor_original text,
    id_usuario bigint NOT NULL REFERENCES public.usuario(id)
);

-- Alter Sequences
ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;
ALTER SEQUENCE skill_id_seq OWNED BY skill.skill_id;
ALTER SEQUENCE perfil_skill_id_seq OWNED BY perfil_skill.perfil_skill_id;
ALTER SEQUENCE log_id_seq OWNED BY log.id_log;
