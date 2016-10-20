DELIMITER $$

CREATE FUNCTION f_comprasPromocao()
    RETURNS CHAR(250)
    
	BEGIN
        DECLARE int(10);
        
		select count(*)
		into v_compras_promocao
		from promocoes prm
		where exists (select 1 from compras cmp
					  where cmp.idPromocao = prm.idPromocao)
        
		RETURN v_compras_promocao;
    END $$

DELIMITER ;