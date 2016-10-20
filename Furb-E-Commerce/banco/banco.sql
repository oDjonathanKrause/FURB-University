use bancoonline;
use ecommercefurb;


create table Pessoa(idPessoa int not null auto_increment,
					nm_pessoa varchar(30) not null,
                    cpf varchar(11) not null,
                    ie_tipo int,
                    email varchar(50),
                    login varchar(15),
                    senha varchar(30),
                    PRIMARY KEY(idPessoa));

create table TelefonesPessoa(Pessoa_idPessoa int,
							 idPessoa int not null auto_increment,
                             telefone varchar(10),
                             ds_tipo varchar(30),
                             PRIMARY KEY(idPessoa),
                             FOREIGN KEY(Pessoa_idPessoa) REFERENCES Pessoa(idPessoa));
                             
create table EnderecosPessoa(idPessoa int not null auto_increment,
							 Pessoa_idPessoa int,
                             ds_endereco varchar(30),
                             numero int,
                             PRIMARY KEY(idPessoa),
                             FOREIGN KEY(Pessoa_idPessoa) REFERENCES Pessoa(idPessoa));                             
                             
                             
create table Compras(idCompras int not null auto_increment,
					 idPedido int,
                     ie_finalizada boolean,
                     idPromocao int,
                     vl_total double,
                     PRIMARY KEY(idCompras));       
                     
create table Promocoes(idPromocao int not null auto_increment,
					   Compras_idCompras int,
                       dt_validade date,
                       dt_inicio date,
                       qt_porcent double,
                       ie_tipo int,
                       PRIMARY KEY(idPromocao),
                       FOREIGN KEY(Compras_idCompras) REFERENCES Compras(idCompras));                     
                       
create table TiposPromocao(idTiposPromocao int not null auto_increment,
						   Promocoes_idPromocao int,
                           ds_tipo varchar(30),
                           FOREIGN KEY(Promocoes_idPromocao) REFERENCES Promocoes(idPromocao),
						   PRIMARY KEY(idTiposPromocao));                       
                             
create table Pedidos(idPedidos int not null auto_increment,
					 Compras_idCompras int,
                     id_pessoa int,
                     PRIMARY KEY(idPedidos),
                     FOREIGN KEY(Compras_idCompras) REFERENCES Compras(idCompras));

create table Produtos(idProdutos int not null auto_increment,
					  ds_produto varchar(50),
                      qtd_estoque int,
                      vl_valor double,
					  PRIMARY KEY(idProdutos));

create table ProdutosPedido(Pedidos_idPedidos int,
							idPedido int,
                            idProduto int,
							FOREIGN KEY(Pedidos_idPedidos) REFERENCES Pedidos(idPedidos));

create table PedidosPessoa(Pedidos_idPedidos int,
						   Pessoa_idPessoa int,
                           idPessoa int,
                           idPedido int,
                           FOREIGN KEY(Pessoa_idPessoa) REFERENCES Pessoa(idPessoa),
                           FOREIGN KEY(Pedidos_idPedidos) REFERENCES Pedidos(idPedidos));        
                           
create table Fornecedores(idFornecedores int not null auto_increment,
						  Pessoa_idPessoa int,
                          cnpj varchar(14),
                          ds_nome varchar(30),
                          idPessoa int,
                          FOREIGN KEY(Pessoa_idPessoa) REFERENCES Pessoa(idPessoa),
                          PRIMARY KEY(idFornecedores));                           
	





/*
create procedure addPessoa(p_nm_pessoa, p_cpf, p_login, p_senha, p_email, p_idPessoa)
begin

	insert into pessoa(nm_pessoa, cpf, login, senha, email, ie_tipo)
	values (p_nm_pessoa, p_cpf, p_login, p_senha, p_email);
    
    select idPessoa 
    into p_idPessoa
    from pessoa
    where cpf = p_cpf;
    
    insert into enderecospessoa(idPessoa, Pessoa_idPessoa, ds_endereco, numero)
    values ();


end;
*/





                         
                         
                         