DELIMITER $$
CREATE DEFINER=`servicos_db`@`%` FUNCTION `usf_verificar_termino_serv`(p_hora_inicio time
										 , p_servico_id int) RETURNS time
begin
	declare v_tempo_servico time default 0;
    declare v_termino_servico time default 0;

	select tempo_medio_servico
    into v_tempo_servico
    from t_servicos
    where servico_id = p_servico_id;
    
    select addtime(p_hora_inicio, v_tempo_servico)
    into v_termino_servico;
    
    /*select date_add(p_hora_inicio, interval v_tempo_servico minute)
    into v_termino_servico; */
    
    return v_termino_servico;
end$$
DELIMITER ;
