DELIMITER $$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_agendar_horario`(in p_estab_id int
                                   , in p_servico_id int
                                   , in p_cliente_id int
                                   , in p_dia_inicio date
                                   , in p_hora_inicio time
                                   , in p_funcionario_id int)
begin
	-- Declara variaveis
	declare v_servico_ok int default 0;
    declare v_funcionario_ok int default 0;
    declare v_hora_fim_serv time default 0;
    declare v_cliente_associado int default 0;
    declare v_preco_servico float default 0;
    
	/* Verifica se o serviço esta disponível */
    select count(1) 
    into v_servico_ok
    from t_servicos
    where status_servico = 'D'
    and servico_id = p_servico_id;
    
    -- Se nao estiver disponivel, lança exception e retorna false
    if (v_servico_ok = 0) then
		-- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , categoria_id
                             , cliente_id
                             , estab_id
                             , end_estab_id)
        values(0
			 , 300 -- t_lookup
             , 'Servico nao disponivel'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , p_servico_id
             , (select categoria_id from t_servicos where servico_id = p_servico_id)
             , p_cliente_id
             , p_estab_id
             , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id));
        
        commit;
        
        -- Tratamento de erro
        set v_servico_ok = 0;
        signal sqlstate '45000' 
        set message_text = "Servico nao disponivel";
    end if;
    
    /* Verifica funcionario escolhido */
    -- Caso algum funcionario foi escolhido
	if (nullif(p_funcionario_id, '') is not null) then
		
        -- Verifica se ele esta disponivel para o servico
		select count(1)
		into v_funcionario_ok
		from t_servico_funcionario
		where func_id = p_funcionario_id -- parametro de funcionario escolhido
        and status_funcionario = 'D'; -- status D disponivel
		                     
		-- Se o count não retornar nenhuma linha, o funcionario nao esta disponivel, loga erro
		if (v_funcionario_ok = 0) then
        -- Insere transação
			insert into t_tran_log(log_id
								 , tran_type
								 , descricao
								 , dia_inicio_tran
								 , hora_inicio_tran
								 , dia_fim_tran
								 , hora_fim_tran
								 , servico_id
								 , categoria_id
								 , cliente_id
								 , estab_id
								 , end_estab_id
                                 , funcionario_id)
			values(0
				 , 500 -- t_lookup
				 , 'Funcionario nao disponivel para este horario'
				 , curdate() -- dia_inicio_tran
				 , curtime() -- hora_inicio_tran
				 , curdate() -- dia_fim_tran
				 , curtime() -- hora_fim_tran
				 , p_servico_id
				 , (select categoria_id from t_servicos where servico_id = p_servico_id)
				 , p_cliente_id
				 , p_estab_id
				 , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id)
                 , p_funcionario_id);
				 
			commit;
			
			-- Tratamento de erro
			set v_servico_ok = 0;
			signal sqlstate '45000' set message_text = "Funcionario nao disponivel para este horario";
        end if;
	end if;
    
    /* Verifica se o horario esta disponivel */                                               
	if (select usf_verificar_horario(p_dia_inicio, p_hora_inicio, p_servico_id, p_estab_id, p_cliente_id)) then
        
        -- Verifica o horario de termino do servico
        select usf_verificar_termino_serv(p_hora_inicio, p_servico_id)
        into v_hora_fim_serv;
        
        -- Verifica se o cliente ja agendou algum sevico neste dia e horario
        select count(*) 
        into v_cliente_associado
        from t_hora_servico
        where cliente_id = p_cliente_id
        and dia_inicio = p_dia_inicio
        and (p_hora_inicio between hora_inicio and date_sub(hora_fim, interval 1 minute) 
		  or v_hora_fim_serv < hora_fim);
	
		-- Se ja estiver associado, loga erro
        if(v_cliente_associado > 0) then
			-- Insere transação
			insert into t_tran_log(log_id
								 , tran_type
								 , descricao
								 , dia_inicio_tran
								 , hora_inicio_tran
								 , dia_fim_tran
								 , hora_fim_tran
								 , servico_id
								 , categoria_id
								 , cliente_id
								 , estab_id
								 , end_estab_id)
			values(0
				 , 400 -- t_lookup
				 , 'Servico ja agendado para este horario'
				 , curdate() -- dia_inicio_tran
				 , curtime() -- hora_inicio_tran
				 , curdate() -- dia_fim_tran
				 , curtime() -- hora_fim_tran
				 , p_servico_id
				 , (select categoria_id from t_servicos where servico_id = p_servico_id)
				 , p_cliente_id
				 , p_estab_id
				 , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id));
				 
			commit;
			
			-- Tratamento de erro
			set v_servico_ok = 0;
			signal sqlstate '45000' set message_text = "Servico ja agendado para este horario";
        end if;
        
        /* Se passou por todos os IFs, agenda o servico */
        -- Caso algum funcionario foi escolhido
		if (nullif(p_funcionario_id, '') is not null) then
			-- Seta status do funcionario pra ocupado
            update t_servico_funcionario
            set status_funcionario = 'O'
            where func_id = p_funcionario_id;
            
            -- Verifica o preco para o servico com o funcionario escolhido
            select preco_servico
            into v_preco_servico
            from t_servico_funcionario
            where func_id = p_funcionario_id;
        -- Senao verifica o preco default do servico 
        else 
			select preco_servico
            into v_preco_servico
            from t_servicos
            where servico_id = p_servico_id;
        end if;
        
		-- Insere na t_hora_servico
		insert into t_hora_servico(hora_servico_id
								 , cliente_id
                                 , servico_id
                                 , dia_inicio
                                 , hora_inicio
                                 , dia_fim
                                 , hora_fim
                                 , status_servico
                                 , preco_servico
                                 , funcionario_id)
        values(0
			 , p_cliente_id
             , p_servico_id
             , p_dia_inicio
             , p_hora_inicio
             , p_dia_inicio -- VERIFICAR
             , v_hora_fim_serv
             , 'A'
             , v_preco_servico
             , p_funcionario_id);
             
        -- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , categoria_id
                             , cliente_id
                             , estab_id
                             , end_estab_id
                             , hora_servico_id
                             , valor_controle
                             , valor_controle_2
                             , funcionario_id) -- funcionario
        values(0
			 , 100 -- t_lookup
             , 'Agendamento de Servico'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , p_servico_id
             , (select categoria_id from t_servicos where servico_id = p_servico_id)
             , p_cliente_id
             , p_estab_id
             , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id)
             , last_insert_id() 
             , null
             , null
             , p_funcionario_id);
	else
       -- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , categoria_id
                             , cliente_id
                             , estab_id
                             , end_estab_id)
        values(0
			 , 200 -- t_lookup
             , 'Hora nao disponivel'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , p_servico_id
             , (select categoria_id from t_servicos where servico_id = p_servico_id)
             , p_cliente_id
             , p_estab_id
             , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id));
             
		commit;
        
        -- Tratamento de erro
        set v_servico_ok = 0;
        signal sqlstate '45000' set message_text = "Hora nao disponivel";
	end if;
    
    commit;
end$$
DELIMITER ;
