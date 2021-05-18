set foreign_key_checks = 0;

delete from fabricante;
delete from modelo; 
delete from veiculo; 

set foreign_key_checks = 1;

alter table fabricante auto_increment = 1;
alter table modelo auto_increment = 1;
alter table veiculo auto_increment = 1;

insert into fabricante (id, nome) VALUES ('1', 'Mercedes-Benz');
insert into fabricante (id, nome) VALUES ('2', 'Bmw');

insert into modelo (id, nome, fabricante_id) VALUES ('1', 'Class C 1.1 Avantgarde Turbo Flex', 1);
insert into modelo (id, nome, fabricante_id) VALUES ('2', 'Bmw i8', 2);
insert into modelo (id, nome, fabricante_id) VALUES ('3', 'Bmw 320i', 2);

insert into veiculo (id, placa, status, modelo_id) VALUES ('1', 'ABC1234', true, 1);





