DELIMITER $$

DROP PROCEDURE IF EXISTS editarProduto;

create PROCEDURE editarProduto( in p_idProduto int,
    							in p_nm_produto varchar(250),
                                in p_qtd_estoque int,
                                in p_vl_valor float,
                                in p_ds_produto varchar(250)) 
BEGIN

UPDATE produtos
    set nm_produto = p_nm_produto,
    	qtd_estoque = p_qtd_estoque,
        vl_valor = p_vl_valor,
        ds_produto = p_ds_produto
    WHERE idProduto = p_idProduto;
    
END $$

DELIMITER ;





