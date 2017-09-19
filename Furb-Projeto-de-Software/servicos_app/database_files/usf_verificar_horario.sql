DELIMITER $$
CREATE DEFINER=`servicos_db`@`%` FUNCTION `usf_verificar_horario`(p_dia_inicio date
								    , p_hora_inicio datetime
                                    , p_servico_id int
                                    , p_estab_id int
                                    , p_cliente_id int) RETURNS tinyint(1)
begin
    declare v_servicos_agendados int default 0;
    declare v_qtd_simultanea int default 1;
    declare v_periodo_disp int default 0;
    
    -- Verifica quantos servicos estao agendados neste dia e horario
	select count(*)
    into v_servicos_agendados
    from t_hora_servico
    where dia_inicio = p_dia_inicio
    and hora_inicio = p_hora_inicio
    and status_servico = 'A' -- servico agendado
    and cliente_id <> p_cliente_id
    and servico_id = p_servico_id;
    
    -- Verifica quantos servicos podem ocorrer simultaneamente neste estabelecimento
    select count(qtd_simultanea)
    into v_qtd_simultanea
    from t_servico_estabelecimento
    where estab_id = p_estab_id
    and servico_id = p_servico_id;
    
    -- Verifica o período em que o serviço fica diponível
    select count(1)
    into v_periodo_disp
    from t_servicos
    where p_hora_inicio >= hora_disp_inicio 
    and (select usf_verificar_termino_serv(p_hora_inicio, p_servico_id)) <= hora_disp_fim;
    
    -- Se a quantidade de servicos ja agendados for maior ou igual a quantidade permitida simultaneamente, retorna false
    if (v_servicos_agendados >= v_qtd_simultanea) then
		return false;
    end if;
    
    -- Se o periodo estiver fora do estabelecido para o servico, retorna false
	if (v_periodo_disp = 0) then
		return false;
    end if;
    
    -- Se não retornou false até aqui, retorna true
    return true;
    
end$$
DELIMITER ;
