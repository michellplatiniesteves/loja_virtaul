create table public.tabela_acesso_end_point(
	nome_end_point character varying,
	qtd_acesso_end_point integer);

insert into public.tabela_acesso_end_point (nome_end_point,qtd_acesso_end_point) values('END-POINT-NOME-PESSOA-FISICA',0);	

alter table tabela_acesso_end_point add constraint nome_end_point_unique UNIQUE (nome_end_point);