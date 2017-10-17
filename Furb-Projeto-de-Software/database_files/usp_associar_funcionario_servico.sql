DELIMITER $$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_associar_funcionario_servico`(in p_funcionario_id int
												 , in p_estab_id int
                                                 , in p_servico_id int
												 , in p_preco_servico float)
begin
	declare v_servico_funcionario_exists int default 0;
    declare v_serv_estab_id int default null;
    
    -- Verifica o serviço associado ao estabelecimento
    select serv_estab_id
    into v_serv_estab_id
    from t_servico_estabelecimento
    where servico_id = p_servico_id
    and estab_id = p_estab_id;
    
    -- Verifica se o usuário já esta associado a este serviço X estabelecimento
    select count(1)
    into v_servico_funcionario_exists
    from t_servico_funcionario
    where serv_estab_id = v_serv_estab_id
    and func_id = p_funcionario_id;

	-- Se o serviço esta disponível para o estabelecimento
	if (v_serv_estab_id is null) then
		signal sqlstate '45000' 
        set message_text = "Servico nao disponivel para o estabelecimento";
	-- Se o funcionario já estiver associado a este serviço do estabelecimento, tratamento de erro		
	elseif (v_servico_funcionario_exists > 0) then
		signal sqlstate '45000' 
        set message_text = "Funcionario ja associado a este servico do estabelecimento";
    end if;
    
    
       
    -- Associa funcionário ao serviço
    insert into t_servico_funcionario(serv_func_id
									, serv_estab_id
                                    , func_id
                                    , preco_servico)
    values(0
		 , v_serv_estab_id
         , p_funcionario_id
         , p_preco_servico);
         
    commit;     

end$$
DELIMITER ;
