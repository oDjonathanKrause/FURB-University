DELIMITER $$

CREATE PROCEDURE cadastrarFornecedor(in p_nome varchar(30),
									 in p_cpf varchar(11),
									 in p_cnpj varchar(12),
									 in p_contato varchar(300),
                                     in p_descricao varchar(300))

BEGIN

INSERT INTO pessoa(idPessoa, nm_pessoa, cpf, ie_tipo, login, senha, email)
VALUES(null, 
	   p_nome, 
	   p_cpf, 
	   3, -- ie_tipo
	   null, -- login
	   null, -- senha
	   null); -- email

INSERT INTO fornecedores(idFornecedor, Pessoa_idPessoa, ds_nome, cnpj, contato, descricao)
VALUES(null, -- pk
	   LAST_INSERT_ID(), -- fk de pessoa
	   p_nome, 
	   p_cnpj,
	   p_contato,
       p_descricao); 

 
END $$

DELIMITER ;





