DELIMITER $$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_cancelar_hora_servico`(in p_hora_servico_id int
										 , in p_motivo_cancelamento varchar(150))
begin
	declare v_estab_id int;
    declare v_servico_id int;
    declare v_cliente_id int;
    declare v_dia_inicio int;
    declare v_hora_inicio int;
    
    -- Encontra dados da hora agendada
    select (select estab_id from t_servico_estabelecimento where servico_id = hser.servico_id) -- estab_id
		 , hser.servico_id
         , hser.cliente_id
         , hser.hora_inicio
         , hser.dia_inicio
    into v_estab_id
       , v_servico_id
       , v_cliente_id
       , v_dia_inicio
       , v_hora_inicio
    from t_hora_servico hser
    where hser.hora_servico_id = p_hora_servico_id;
    
    -- Se as variaveis estiverem nulas, a hora nao foi encontrada
    if (v_estab_id is null or v_servico_id is null) then
        -- Tratamento de erro
        signal sqlstate '45000' 
        set message_text = "Hora ja cancelada";
    
    -- Senao, efetua o cancelamento da hora
    else
		delete from t_hora_servico
        where hora_servico_id = p_hora_servico_id;
        
        -- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , cliente_id
                             , estab_id
                             , end_estab_id
                             , valor_controle -- dia inicio servico
                             , valor_controle_2 -- hora inicio servico
                             , valor_generico_1) -- motivo cancelamento
        values(0
			 , 110 -- t_lookup
             , 'Cancelamento de servico'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , v_servico_id
             , v_cliente_id
             , v_estab_id
             , v_estab_id
             , v_dia_inicio
             , v_hora_inicio
             , p_motivo_cancelamento);
        
        commit;	
    
    end if;

end$$
DELIMITER ;
