create table fabricante (
	id bigint not null auto_increment, 
	nome varchar(100) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table modelo (
	id bigint not null auto_increment, 
	nome varchar(100) not null, 
	fabricante_id bigint, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table veiculo (
	id bigint not null auto_increment, 
	placa varchar(10) not null, 
	status bit not null, 
	modelo_id bigint, 
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table modelo add constraint 
fk_modelo_fabricante foreign key (fabricante_id) references fabricante (id);

alter table veiculo add constraint 
fk_veiculo_modelo foreign key (modelo_id) references modelo (id);

