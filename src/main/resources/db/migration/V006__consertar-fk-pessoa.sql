alter table aniversario
    drop foreign key FK_ANIVERSARIO_PESSOA;

alter table pessoa
    add constraint FK_PESSOA_ANIVERSARIO foreign key (aniversario_id) references aniversario(id);