alter table aniversario
    drop foreign key FK_ANIVERSARIO_PESSOA;

alter table falecido
    drop foreign key FK_FALECIDO_PESSOA;

alter table pessoa_grupos_sociais
    drop foreign key FK_GRUPOS_SOCIAIS_GRUPO_SOCIAL;

alter table pessoa_grupos_sociais
    drop foreign key FK_PESSOA_GRUPOS_SOCIAIS_PESSOA;

drop table pessoa;

create table pessoa(
    falecido bit not null,
    idade integer not null,
    maior_de_idade bit not null,
    data_criacao datetime not null,
    data_ultima_atualizacao datetime not null,
    nome varchar(255) not null,
    sexo enum ('FEMININO','MASCULINO') not null,
    descricao tinytext,
    aniversario_id bigint not null,

    primary key (aniversario_id)
) engine=InnoDB;

alter table falecido
    add constraint FK_FALECIDO_PESSOA foreign key (pessoa_id) references pessoa(aniversario_id);

alter table aniversario
    add constraint FK_ANIVERSARIO_PESSOA foreign key (pessoa_id) references pessoa(aniversario_id);

alter table pessoa_grupos_sociais
    add constraint FK_GRUPOS_SOCIAIS_GRUPO_SOCIAL foreign key (grupo_social_id) references grupo_social (id);

alter table pessoa_grupos_sociais
    add constraint FK_PESSOA_GRUPOS_SOCIAIS_PESSOA foreign key (pessoa_id) references pessoa (aniversario_id);