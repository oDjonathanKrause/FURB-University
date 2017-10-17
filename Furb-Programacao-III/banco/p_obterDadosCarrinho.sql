delimiter $$

create procedure p_obterDadosCarrinho(in p_idPessoa int,
									  out p_pessoa varchar(250),
                                      out p_produto varchar(250),
                                      out p_quantidade int,
                                      out p_carrinho int)
begin
	declare v_pessoa varchar(250);
    declare v_produto varchar(250);
    declare v_quantidade int;
    declare v_carrinho int;
	
    select pes.nm_pessoa as Pessoa,
		   prd.ds_produto as Produto,
		   count(ica.idProduto) as Quantidade,
		   car.idCarrinho as Carrinho
	from pessoa pes, 
		 carrinho car,
		 itens_carrinho ica,
		 produtos prd
	where pes.idPessoa = car.idPessoa
	and ica.idCarrinho = car.idCarrinho
	and prd.idProduto = ica.idProduto
	and pes.idPessoa = p_idPessoa
	group by pes.nm_pessoa,
		   prd.ds_produto,
		   car.idCarrinho
	into v_pessoa, v_produto, v_quantidade, v_carrinho;    
    
    set p_pessoa = v_pessoa;
    set p_produto = v_produto;
    set p_quantidade = v_quantidade;
    set p_carrinho = v_carrinho;
    
end $$

delimiter ;
-- Chamada: call p_obterDadosCarrinho(10, @pessoa, @produto, @quantidade, @carrinho);





