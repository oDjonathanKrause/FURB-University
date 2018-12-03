
-- Valor da compra por pessoa
select pes.nm_pessoa as Pessoa,
	   sum(com.vl_total) as Valor
from compras com, pessoa pes
where com.idPessoa = pes.idPessoa
group by com.idPessoa;

-- Quantidade de compras por pessoa e valor
select pes.nm_pessoa as Pessoa,
	   count(*) as Compras,
       sum(com.vl_total) as Valor
from compras com, pessoa pes
where com.idPessoa = pes.idPessoa
group by com.idPessoa;
