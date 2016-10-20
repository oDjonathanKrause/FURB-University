CREATE DEFINER=`root`@`localhost` PROCEDURE `p_obterDadosProduto`(in  p_idProduto int,
							         out p_nm_produto varchar(150),
								     out p_vl_valor double,
								     out p_ds_produto varchar(250),
								     out p_parcelas float,
									 out p_imagem blob)
BEGIN
	DECLARE v_nm_produto, v_ds_produto varchar(250);
	DECLARE v_vl_valor double;
	DECLARE v_parcelas float;
    DECLARE v_imagem blob;

	select nm_produto 
	from produtos
	where idProduto = p_idProduto
	into v_nm_produto;
    
    
	select vl_valor 
	from produtos
	where idProduto = p_idProduto
	into v_vl_valor;
    
    
	select ds_produto 
	from produtos
	where idProduto = p_idProduto
	into v_ds_produto;
    

	select parcelas 
	from produtos
	where idProduto = p_idProduto
	into v_parcelas;
    
    select imagem 
	from produtos
	where idProduto = p_idProduto
	into v_imagem;
    
    set p_nm_produto = v_nm_produto;
    set p_vl_valor = v_vl_valor;
    set p_ds_produto = v_ds_produto;
    set p_parcelas = v_parcelas;
    set p_imagem = v_imagem;

END