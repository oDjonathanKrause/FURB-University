delimiter $$

CREATE PROCEDURE p_produtos_venda()

begin
	declare v_produto int;
    declare v_quantidade int;
    
	select prd.idProduto,
		   count(ica.idProduto)
	from pessoa pes, 
		 carrinho car,
		 itens_carrinho ica,
		 produtos prd
	where pes.idPessoa = car.idPessoa
	and ica.idCarrinho = car.idCarrinho
	and prd.idProduto = ica.idProduto
	group by prd.ds_produto
    into v_produto, v_quantidade;
				   
	insert into produto_venda(idProduto, quantidade, data)
	values(v_produto, v_quantidade, now());
	   
end $$