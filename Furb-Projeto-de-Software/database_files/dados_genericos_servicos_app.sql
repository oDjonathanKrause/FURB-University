use servicos_db;


select * from t_estabelecimentos;
select * from t_enderecos_estab;
select * from t_categorias;
select * from t_servicos;
select * from t_hora_servico;
select * from t_clientes;
select * from v_servico_estab_info;
select * from t_alias;



INSERT INTO `t_alias` (`alias_id`,`nome_tabela`,`alias_tabela`,`desc_tabela`) VALUES (0,'t_servicos','serv',NULL);
INSERT INTO `t_alias` (`alias_id`,`nome_tabela`,`alias_tabela`,`desc_tabela`) VALUES (0,'t_categorias','cate',NULL);
INSERT INTO `t_alias` (`alias_id`,`nome_tabela`,`alias_tabela`,`desc_tabela`) VALUES (0,'t_clientes','clie',NULL);
INSERT INTO `t_alias` (`alias_id`,`nome_tabela`,`alias_tabela`,`desc_tabela`) VALUES (0,'t_enderecos_estab','ende',NULL);
INSERT INTO `t_alias` (`alias_id`,`nome_tabela`,`alias_tabela`,`desc_tabela`) VALUES (0,'t_estabelecimentos','estb',NULL);
INSERT INTO `t_alias` (`alias_id`,`nome_tabela`,`alias_tabela`,`desc_tabela`) VALUES (0,'t_hora_servico','hser',NULL);
INSERT INTO `t_alias` (`alias_id`,`nome_tabela`,`alias_tabela`,`desc_tabela`) VALUES (0,'t_servico_estabelecimento','sest',NULL);

INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_hora_servico','status_servico','A','Agendado',NULL);
INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_hora_servico','status_servico','C','Cancelado',NULL);
INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_hora_servico','status_servico','P','Pendente',NULL);
INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_servicos','status_servico','D','Disponível',NULL);
INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_tran_log','tran_type','100','Agendamento de Servico','usp_agendar_horario');
INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_tran_log','tran_type','200','Hora nao disponivel','usp_agendar_horario');
INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_tran_log','tran_type','300','Servico nao disponivel','usp_agendar_horario');
INSERT INTO `t_lookup` (`lookup_id`,`nome_tabela`,`nome_coluna`,`valor`,`descricao`,`obs`) VALUES (0,'t_tran_log','tran_type','400','Servico ja agendado para este horario','usp_agendar_horario');
insert into t_lookup values (0, 't_tran_log', 'tran_type', '110', 'Cancelamento de servico', 'usp_cancelar_hora_servico');


-- nome_estab, pais_estab, estado_estab, cidade_estab, bairro_estab, rua_estab, numbero_estab, cep_estab, complemento_estab, telefone_estab, whatsapp_estab, facebook_estab, site_estab, usuario 
call usp_cadastrar_estab('Au Au Pet-Shop', 'Brasil', 'Santa Catarina', 'Indaial', 'Encano Baixo' ,'Rua José Morales', '132', '89130-000', 'Au au', '47 3132-2532', '47 99115-4623', 'www.facebook.com.br/paginaDoEstabelecimento', 'www.site-do-estabelecimento', 2);
/*insert into t_estabelecimentos (estab_id, nome_estab) values (0, 'PetShop HappyDog');
insert into t_estabelecimentos (estab_id, nome_estab) values (0, 'Salão de Beleza da Tereza');
insert into t_estabelecimentos (estab_id, nome_estab) values (0, 'Barbearia do Antonio');
insert into t_estabelecimentos (estab_id, nome_estab) values (0, 'Cabeleireiros da XV');
insert into t_enderecos_estab values(0, (select estab_id from t_estabelecimentos where nome_estab = 'PetShop HappyDog'), 'Brasil', 'Santa Catarina', 'Blumenau', 'Victor Konder' ,'Rua Antonio da Veiga', '222', null, '47 3312-2431', '47 9155-4623', 'www.facebook.com.br/paginaDoEstabelecimento', 'www.site-do-estabelecimento');
insert into t_enderecos_estab values(0, (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), 'Brasil', 'Santa Catarina', 'Blumenau', 'Velha' ,'Rua Geosório Nunes', '642', null, '47 3132-2532', '47 9155-4623', 'www.facebook.com.br/paginaDoEstabelecimento', 'www.site-do-estabelecimento');
insert into t_enderecos_estab values(0, (select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), 'Brasil', 'Santa Catarina', 'Blumenau', 'Velha Antiga' ,'Rua Otto Gernunes', '876', null, '47 3132-8831', '47 9155-4623', 'www.facebook.com.br/paginaDoEstabelecimento', 'www.site-do-estabelecimento');
insert into t_enderecos_estab values(0, (select estab_id from t_estabelecimentos where nome_estab = 'Cabeleireiros da XV'), 'Brasil', 'Santa Catarina', 'Blumenau', 'Centro' ,'Rua XV', '876', null, '47 3132-8831', '47 9155-4623', 'www.facebook.com.br/paginaDoEstabelecimento', 'www.site-do-estabelecimento');*//

-- p_nome_funcionario, p_estab_id, p_fone_funcionario, p_whatsapp_funcionario
call usp_cadastrar_funcionario('Antonio', (select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), '47 9155-4623', '');
call usp_cadastrar_funcionario('José Carlos', (select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), null, null);
call usp_cadastrar_funcionario('Tereza', (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), null, '47 9155-4623');
call usp_cadastrar_funcionario('Manuela', (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), null, '47 9155-4623');
call usp_cadastrar_funcionario('Josi', (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), '47 9155-4623', '47 9155-4623');

insert into t_categorias values(0, 'Barbearia', 'Barbearias');
insert into t_categorias values(0, 'Salão de Beleza', 'Salões de Beleza');
insert into t_categorias values(0, 'Petshop', 'Petshops');

-- p_estabelecimento_id, p_categoria_id, p_nome_servico, p_desc_servico, p_preco_servico, p_preco_hora_servico, p_tempo_medio_servico, p_status_servico, p_qtd_simultanea 
call usp_cadastrar_servico_estab((select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), (select categoria_id from t_categorias where nome_categoria = 'Barbearia'), 'Corte de cabelo', 'Corte de cabelo', 30, null, '00:30:00', 'D', 1);
call usp_cadastrar_servico_estab((select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), (select categoria_id from t_categorias where nome_categoria = 'Salão de Beleza'), 'Corte de cabelo', 'Corte de cabelo', 35, null, '00:35:00', 'D', 2);
call usp_cadastrar_servico_estab((select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), (select categoria_id from t_categorias where nome_categoria = 'Barbearia'), 'Barba feita', 'Corte de barba', 30, null, '00:20:00', 'D', 1);
call usp_cadastrar_servico_estab((select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), (select categoria_id from t_categorias where nome_categoria = 'Salão de Beleza'), 'Pé e Mão', 'Pintura de pé e mão', 80, null, '01:00:00', 'D', 3);
call usp_cadastrar_servico_estab((select estab_id from t_estabelecimentos where nome_estab = 'Cabeleireiros da XV'), (select categoria_id from t_categorias where nome_categoria = 'Salão de Beleza'), 'Corte de cabelo', 'Corte de cabelo', 50, null,  '00:50:00', 'D', 2);
call usp_cadastrar_servico_estab((select estab_id from t_estabelecimentos where nome_estab = 'PetShop HappyDog'), (select categoria_id from t_categorias where nome_categoria = 'Petshop'), 'Tosa', 'Tosa de cachorros de pequeno e médio porte', 37, null,'00:45:00', 'D', 4);

-- p_funcionario_id, p_estab_id, p_servico_id, p_preco_servico
call usp_associar_funcionario_servico((select funcionario_id from t_funcionarios where nome_funcionario = 'Antonio'), (select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), 8, null);
call usp_associar_funcionario_servico((select funcionario_id from t_funcionarios where nome_funcionario = 'José Carlos'), (select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), 8, null);
call usp_associar_funcionario_servico((select funcionario_id from t_funcionarios where nome_funcionario = 'Tereza'), (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), 9, 55);
call usp_associar_funcionario_servico((select funcionario_id from t_funcionarios where nome_funcionario = 'Manuela'), (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), 9, null);
call usp_associar_funcionario_servico((select funcionario_id from t_funcionarios where nome_funcionario = 'Josi'), (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), 9, null);
call usp_associar_funcionario_servico((select funcionario_id from t_funcionarios where nome_funcionario = 'Josi'), (select estab_id from t_estabelecimentos where nome_estab = 'Salão de Beleza da Tereza'), 11, 60);



insert into t_clientes values (0, 'Djonathan', '090.123.321-55', 'Rua Antonio José', 'odjonathankrause@gmail.com');
insert into t_clientes values (0, 'Rodrigo', '090.123.321-55', 'Rua José Antonio, 123', 'email@rodrigo.com');



-- p_estab_id int, p_servico_id int, p_cliente_id int, p_dia_inicio date, p_hora_inicio time, p_funcionario_id int
call usp_agendar_horario((select estab_id from t_estabelecimentos where nome_estab = 'Barbearia do Antonio'), 8, 2, curdate(), '14:00:00', null);

