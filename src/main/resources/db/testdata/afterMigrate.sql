set foreign_key_checks = 0;

delete from falecido;
delete from grupo_social;
delete from pessoa;
delete from aniversario;
delete from pessoa_grupos_sociais;

set foreign_key_checks = 1;

alter table falecido auto_increment = 1;
alter table aniversario auto_increment = 1;
alter table pessoa auto_increment = 1;
alter table grupo_social auto_increment = 1;

