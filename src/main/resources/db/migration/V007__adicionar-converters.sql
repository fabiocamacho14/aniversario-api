alter table  aniversario
    drop column proximo_aniversario_em;

alter table falecido
    drop column periodo_falecimento;

alter table aniversario
    add proximo_aniversario_em varchar(9);

alter table falecido
    add periodo_falecimento varchar(9);