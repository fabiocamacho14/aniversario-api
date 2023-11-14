alter table aniversario
add column desejo tinytext;

alter table aniversario
add column proximo_aniversario_em varbinary(255) not null;