alter table aniversario
    drop foreign key FK_ANIVERSARIO_PESSOA;

ALTER TABLE aniversario MODIFY COLUMN id bigint auto_increment;

alter table aniversario
    add constraint FK_ANIVERSARIO_PESSOA foreign key (id) references pessoa(aniversario_id);