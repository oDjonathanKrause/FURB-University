DELIMITER $$
CREATE DEFINER=`servicos_db`@`%` FUNCTION `usf_verificar_servicos_concluidos`() RETURNS int(11)
    DETERMINISTIC
begin
	declare v_retorno int default 0;
    
    select count(1) 
    into v_retorno
    from t_hora_servico 
    where status_servico = 'A'
	and dia_fim < curdate()
	  or (hora_fim <= curtime() and dia_fim <= curdate());
	
    return v_retorno;
end$$
DELIMITER ;
