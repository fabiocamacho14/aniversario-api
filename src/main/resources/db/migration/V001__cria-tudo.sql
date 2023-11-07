create table aniversario (
    data_aniversario datetime,
    data_criacao datetime not null,
    data_ultima_atualizacao datetime not null,
    pessoa_id bigint not null,

    primary key (pessoa_id)
) engine=InnoDB;


create table falecido (
    idade_que_faleceu integer not null,
    data_falecimento datetime not null,
    pessoa_id bigint not null,
    periodo_falecimento datetime not null,

    primary key (pessoa_id)
) engine=InnoDB;


create table grupo_social (
    data_criacao datetime not null,
    data_ultima_atualizacao datetime not null,
    id bigint not null auto_increment,
    nivel_importancia enum ('IMPORTANTE','IRRELEVANTE','MUITO_IMPORTANTE','OK') not null,
    descricao tinytext,

    primary key (id)
) engine=InnoDB;

create table pessoa (
    falecido bit not null,
    idade integer not null,
    maior_de_idade bit not null,
    data_criacao datetime not null,
    data_ultima_atualizacao datetime not null,
    id bigint not null auto_increment,
    nome varchar(255) not null,
    sexo enum ('FEMININO','MASCULINO') not null,
    proximo_aniversario_em varbinary(255) not null,
    descricao tinytext,
    desejo tinytext,

    primary key (id)
) engine=InnoDB;

create table pessoa_grupos_sociais (
    grupo_social_id bigint not null,
    pessoa_id bigint not null,

    primary key (grupo_social_id, pessoa_id)
) engine=InnoDB;

alter table aniversario add constraint FK_ANIVERSARIO_PESSOA
    foreign key (pessoa_id) references pessoa (id);
alter table falecido add constraint FK_FALECIDO_PESSOA
    foreign key (pessoa_id) references pessoa (id);
alter table pessoa_grupos_sociais
    add constraint FK_GRUPOS_SOCIAIS_GRUPO_SOCIAL foreign key (grupo_social_id) references grupo_social (id);
alter table pessoa_grupos_sociais
    add constraint FK_PESSOA_GRUPOS_SOCIAIS_PESSOA foreign key (pessoa_id) references pessoa (id);