-- select @@version;

delimiter $$

create procedure p_finalizaCompra(in p_idPessoa int,
								  in p_idCarrinho int,
                                  in p_vl_total double)

begin

    insert into compras(idCompras, idPedido, idPessoa, vl_total)
    values (null, p_idCarrinho, p_idPessoa, p_vl_total);
    
    delete from itens_carrinho
    where idCarrinho = p_idCarrinho;
    
    delete from carrinho
    where idCarrinho = p_idCarrinho;

end $$
