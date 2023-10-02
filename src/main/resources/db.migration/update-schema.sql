CREATE
SEQUENCE IF NOT EXISTS admin_entity_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS curso_estudante_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS estudante_entity_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS opcoes_votacao_seq START
WITH 1 INCREMENT BY 50;

CREATE
SEQUENCE IF NOT EXISTS voto_entity_seq START
WITH 1 INCREMENT BY 50;

CREATE TABLE admin_entity
(
    id             BIGINT NOT NULL,
    nome_entity    VARCHAR(255),
    senha_entity   VARCHAR(255),
    sexo_entity    VARCHAR(255),
    email_entity   VARCHAR(255),
    data_entity    VARCHAR(255),
    estado_entity  BOOLEAN,
    username_admin VARCHAR(255),
    cargo_admin    VARCHAR(255),
    CONSTRAINT pk_admin_entity PRIMARY KEY (id)
);

CREATE TABLE curso_estudante
(
    id              BIGINT NOT NULL,
    nome_curso      VARCHAR(255),
    turno_estudante VARCHAR(255),
    ano_estudante   INTEGER,
    ano_atual       VARCHAR(255),
    final_ano       VARCHAR(255),
    CONSTRAINT pk_curso_estudante PRIMARY KEY (id)
);

CREATE TABLE estudante_entity
(
    id                 BIGINT NOT NULL,
    nome_entity        VARCHAR(255),
    senha_entity       VARCHAR(255),
    sexo_entity        VARCHAR(255),
    email_entity       VARCHAR(255),
    data_entity        VARCHAR(255),
    estado_entity      BOOLEAN,
    curso_estudante_fk BIGINT,
    role               VARCHAR(255),
    CONSTRAINT pk_estudante_entity PRIMARY KEY (id)
);

CREATE TABLE opcoes_votacao
(
    id                   BIGINT NOT NULL,
    opcoes_voto          VARCHAR(255),
    vote_count           BIGINT DEFAULT 0,
    id_estudante_voto_fk BIGINT,
    voto_opcoes_fk       BIGINT,
    CONSTRAINT pk_opcoesvotacao PRIMARY KEY (id)
);

CREATE TABLE voto_entity
(
    id                        BIGINT NOT NULL,
    estudante_criador_voto_fk BIGINT,
    titulo_votacao            VARCHAR(255),
    estado_votacao            BOOLEAN,
    CONSTRAINT pk_voto_entity PRIMARY KEY (id)
);

ALTER TABLE voto_entity
    ADD CONSTRAINT uc_voto_entity_titulo_votacao UNIQUE (titulo_votacao);

ALTER TABLE estudante_entity
    ADD CONSTRAINT FK_ESTUDANTE_ENTITY_ON_CURSO_ESTUDANTE_FK FOREIGN KEY (curso_estudante_fk) REFERENCES curso_estudante (id);

ALTER TABLE opcoes_votacao
    ADD CONSTRAINT FK_OPCOESVOTACAO_ON_VOTO_OPCOES_FK FOREIGN KEY (voto_opcoes_fk) REFERENCES voto_entity (id);

ALTER TABLE voto_entity
    ADD CONSTRAINT FK_VOTO_ENTITY_ON_ESTUDANTE_CRIADOR_VOTOFK FOREIGN KEY (estudante_criador_voto_fk) REFERENCES estudante_entity (id);