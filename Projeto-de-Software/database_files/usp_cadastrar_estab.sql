DELIMITER $$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_cadastrar_estab`(in p_nome_estab varchar(100)
								   , in p_pais_estab varchar(50)
                                   , in p_estado_estab varchar(80)
                                   , in p_cidade_estab varchar(80)
                                   , in p_bairro_estab varchar(80)
                                   , in p_rua_estab varchar(80)
                                   , in p_numero_estab varchar(10)
                                   , in p_cep_estab varchar(10)
                                   , in p_complemento_estab varchar(255)
                                   , in p_telefone_estab varchar(20)
                                   , in p_whatsapp_estab varchar(20)
                                   , in p_facebook_estab varchar(70)
                                   , in p_site_estab varchar(70)
                                   , in p_usuario int(11))
begin 

	-- Insere dados na tabela principal de estabelecimentos
	insert into t_estabelecimentos (estab_id
								  , nome_estab
								  , usuario_id) 
    values (0
		  , p_nome_estab
          , p_usuario);
          
    -- Atribui ultimo id inserido a variável
	select last_insert_id() 
	into @ultimo_estab_inserido;      
          
	-- Insere detalhes do estabelecimento
	insert into t_estabelecimentos_dtl (end_estab_id
									  , estab_id
									  , pais_estab
									  , estado_estab
									  , cidade_estab
									  , bairro_estab
									  , rua_estab
									  , numero_estab
									  , cep_estab
									  , complemento_estab
									  , telefone_estab
									  , whatsapp_estab
									  , facebook_estab
									  , site_estab)
    values(0
		, @ultimo_estab_inserido
        , p_pais_estab
		, p_estado_estab
		, p_cidade_estab
		, p_bairro_estab
		, p_rua_estab
		, p_numero_estab
		, p_cep_estab
		, p_complemento_estab
		, p_telefone_estab
		, p_whatsapp_estab
		, p_facebook_estab
		, p_site_estab);
        
	commit;

end$$
DELIMITER ;
