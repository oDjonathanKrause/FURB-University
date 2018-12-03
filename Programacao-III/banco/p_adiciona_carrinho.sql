CREATE DEFINER=`root`@`localhost` PROCEDURE `p_adiciona_carrinho`(in  p_idProduto int,
									 in p_idPessoa int)
BEGIN
	DECLARE v_id_carrinho int;
    
    select IFNULL(MAX(idCarrinho), 0)
	from carrinho
	where idPessoa = p_idPessoa
	into v_id_carrinho;
	
	if (v_id_carrinho = 0)	 then
		select IFNULL(MAX(idCarrinho), 0) + 1
		from carrinho
		into v_id_carrinho;
		
		insert into carrinho (idCarrinho, idPessoa) 
		values (v_id_carrinho, p_idPessoa);
	end if;
	
	insert into itens_carrinho(idProduto, quantidade, idCarrinho)
	values (p_idProduto, 1, v_id_carrinho);

END