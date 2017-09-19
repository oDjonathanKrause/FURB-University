-- use local_servicos_db;

-- Seta delimitador
delimiter $
drop procedure if exists usp_cadastrar_funcionario $

create procedure usp_cadastrar_funcionario (in p_nome_funcionario varchar(80),
											in p_estab_id int,
											in p_fone_funcionario varchar(20),
											in p_whatsapp_funcionario varchar(20))
begin
	declare v_func_exists int default 0;
    declare v_estab_exists int default 0;
    
    -- Verifica se esse funcionario já esta cadastrado no estabelecimento (nome, estabelecimento, fone + whats)
	select count(1)
    into v_func_exists
    from t_funcionarios
    where upper(nome_funcionario) = upper(p_nome_funcionario)
    and estab_id = p_estab_id
    and (fone_funcionario = p_fone_funcionario or whatsapp_funcionario = p_whatsapp_funcionario);
    
    -- Verifica se o estabelecimento existe
    select count(1)
    into v_estab_exists
    from t_estabelecimentos
    where estab_id = p_estab_id;

    -- Se o funcionário já estiver cadastrado, tratamento de erro
    if(v_func_exists > 0) then 
        signal sqlstate '45000' 
        set message_text = "Funcionario ja cadastrado";
	-- Se o estabelecimento não existir, tratamento de erro
    elseif (v_estab_exists = 0) then    
		signal sqlstate '45000' 
        set message_text = "Estabelecimento nao cadastrado";
    end if;
	
    -- Insere funcionário
    insert into t_funcionarios (funcionario_id
							  , nome_funcionario
                              , estab_id
                              , fone_funcionario
                              , whatsapp_funcionario)
    values (0
		  , p_nome_funcionario
          , p_estab_id
          , p_fone_funcionario
          , p_whatsapp_funcionario);
          
    commit;      
		
end $
delimiter ;
