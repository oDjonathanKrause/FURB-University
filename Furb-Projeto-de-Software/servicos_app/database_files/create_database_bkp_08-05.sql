-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema servicos_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema servicos_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `servicos_db` DEFAULT CHARACTER SET latin1 ;
USE `servicos_db` ;

-- -----------------------------------------------------
-- Table `servicos_db`.`t_alias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_alias` (
  `alias_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_tabela` VARCHAR(80) NOT NULL,
  `alias_tabela` VARCHAR(5) NOT NULL,
  `desc_tabela` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`alias_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_categorias` (
  `categoria_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_categoria` VARCHAR(80) NULL DEFAULT NULL,
  `desc_categoria` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`categoria_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_clientes` (
  `cliente_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` VARCHAR(80) NOT NULL,
  `cpf_cliente` VARCHAR(20) NULL DEFAULT NULL,
  `endereco_cliente` VARCHAR(255) NULL DEFAULT NULL,
  `email_cliente` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`cliente_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_estabelecimentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_estabelecimentos` (
  `estab_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_estab` VARCHAR(100) NOT NULL,
  `usuario_id` INT(11) NOT NULL,
  PRIMARY KEY (`estab_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_estabelecimentos_dtl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_estabelecimentos_dtl` (
  `end_estab_id` INT(11) NOT NULL AUTO_INCREMENT,
  `estab_id` INT(11) NULL DEFAULT NULL,
  `pais_estab` VARCHAR(80) NULL DEFAULT NULL,
  `estado_estab` VARCHAR(80) NULL DEFAULT NULL,
  `cidade_estab` VARCHAR(80) NULL DEFAULT NULL,
  `bairro_estab` VARCHAR(80) NULL DEFAULT NULL,
  `rua_estab` VARCHAR(80) NULL DEFAULT NULL,
  `numero_estab` VARCHAR(10) NULL DEFAULT NULL,
  `cep_estab` VARCHAR(10) NULL DEFAULT NULL,
  `complemento_estab` VARCHAR(255) NULL DEFAULT NULL,
  `telefone_estab` VARCHAR(20) NULL DEFAULT NULL,
  `whatsapp_estab` VARCHAR(20) NULL DEFAULT NULL,
  `facebook_estab` VARCHAR(70) NULL DEFAULT NULL,
  `site_estab` VARCHAR(70) NULL DEFAULT NULL,
  PRIMARY KEY (`end_estab_id`),
  INDEX `estab_id` (`estab_id` ASC),
  CONSTRAINT `t_estabelecimentos_dtl_ibfk_1`
    FOREIGN KEY (`estab_id`)
    REFERENCES `servicos_db`.`t_estabelecimentos` (`estab_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_funcionarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_funcionarios` (
  `funcionario_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_funcionario` VARCHAR(80) NOT NULL,
  `estab_id` INT(11) NOT NULL,
  `fone_funcionario` VARCHAR(20) NULL DEFAULT NULL,
  `whatsapp_funcionario` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`funcionario_id`),
  INDEX `estab_id` (`estab_id` ASC),
  CONSTRAINT `t_funcionarios_ibfk_1`
    FOREIGN KEY (`estab_id`)
    REFERENCES `servicos_db`.`t_estabelecimentos` (`estab_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_servicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_servicos` (
  `servico_id` INT(11) NOT NULL AUTO_INCREMENT,
  `categoria_id` INT(11) NULL DEFAULT NULL,
  `nome_servico` VARCHAR(80) NOT NULL,
  `desc_servico` VARCHAR(255) NULL DEFAULT NULL,
  `hora_disp_inicio` TIME NOT NULL,
  `hora_disp_fim` TIME NOT NULL,
  `preco_servico` FLOAT NULL DEFAULT NULL,
  `preco_hora_servico` FLOAT NULL DEFAULT NULL,
  `tempo_medio_servico` TIME NOT NULL,
  `status_servico` CHAR(1) NOT NULL,
  `taxa_cancelamento` FLOAT NOT NULL DEFAULT '0',
  `cancelavel_flag` CHAR(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`servico_id`),
  INDEX `categoria_id` (`categoria_id` ASC),
  CONSTRAINT `t_servicos_ibfk_2`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `servicos_db`.`t_categorias` (`categoria_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_servico_estabelecimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_servico_estabelecimento` (
  `serv_estab_id` INT(11) NOT NULL AUTO_INCREMENT,
  `servico_id` INT(11) NOT NULL,
  `estab_id` INT(11) NOT NULL,
  `qtd_simultanea` INT(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`serv_estab_id`),
  INDEX `servico_id` (`servico_id` ASC),
  INDEX `estab_id` (`estab_id` ASC),
  CONSTRAINT `t_servico_estabelecimento_ibfk_1`
    FOREIGN KEY (`servico_id`)
    REFERENCES `servicos_db`.`t_servicos` (`servico_id`),
  CONSTRAINT `t_servico_estabelecimento_ibfk_2`
    FOREIGN KEY (`estab_id`)
    REFERENCES `servicos_db`.`t_estabelecimentos` (`estab_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_servico_funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_servico_funcionario` (
  `serv_func_id` INT(11) NOT NULL AUTO_INCREMENT,
  `serv_estab_id` INT(11) NOT NULL,
  `func_id` INT(11) NOT NULL,
  `preco_servico` FLOAT NULL DEFAULT NULL,
  `status_funcionario` CHAR(1) NULL DEFAULT 'D',
  PRIMARY KEY (`serv_func_id`),
  INDEX `serv_estab_id` (`serv_estab_id` ASC),
  INDEX `func_id` (`func_id` ASC),
  CONSTRAINT `t_servico_funcionario_ibfk_1`
    FOREIGN KEY (`serv_estab_id`)
    REFERENCES `servicos_db`.`t_servico_estabelecimento` (`serv_estab_id`),
  CONSTRAINT `t_servico_funcionario_ibfk_2`
    FOREIGN KEY (`func_id`)
    REFERENCES `servicos_db`.`t_funcionarios` (`funcionario_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_hora_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_hora_servico` (
  `hora_servico_id` INT(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) NOT NULL,
  `dia_inicio` DATE NOT NULL,
  `hora_inicio` TIME NOT NULL,
  `dia_fim` DATE NOT NULL,
  `hora_fim` TIME NOT NULL,
  `status_servico` CHAR(1) NULL DEFAULT NULL,
  `servico_id` INT(11) NOT NULL,
  `preco_servico` FLOAT NULL DEFAULT '0',
  `funcionario_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`hora_servico_id`),
  INDEX `id_cliente` (`cliente_id` ASC),
  INDEX `t_hora_servico_ibfk_2_idx` (`servico_id` ASC),
  INDEX `t_hora_servico_ibfk_3_idx` (`funcionario_id` ASC),
  CONSTRAINT `t_hora_servico_ibfk_1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `servicos_db`.`t_clientes` (`cliente_id`),
  CONSTRAINT `t_hora_servico_ibfk_2`
    FOREIGN KEY (`servico_id`)
    REFERENCES `servicos_db`.`t_servicos` (`servico_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `t_hora_servico_ibfk_3`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `servicos_db`.`t_servico_funcionario` (`func_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_lookup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_lookup` (
  `lookup_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_tabela` VARCHAR(45) NOT NULL,
  `nome_coluna` VARCHAR(45) NULL DEFAULT NULL,
  `valor` VARCHAR(45) NULL DEFAULT NULL,
  `descricao` VARCHAR(80) NULL DEFAULT NULL,
  `obs` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`lookup_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `servicos_db`.`t_tran_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`t_tran_log` (
  `log_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tran_type` INT(11) NOT NULL,
  `descricao` VARCHAR(85) NOT NULL,
  `dia_inicio_tran` DATE NULL DEFAULT NULL,
  `hora_inicio_tran` TIME NULL DEFAULT NULL,
  `dia_fim_tran` DATE NULL DEFAULT NULL,
  `hora_fim_tran` TIME NULL DEFAULT NULL,
  `servico_id` INT(11) NULL DEFAULT NULL,
  `categoria_id` INT(11) NULL DEFAULT NULL,
  `cliente_id` INT(11) NULL DEFAULT NULL,
  `estab_id` INT(11) NULL DEFAULT NULL,
  `end_estab_id` INT(11) NULL DEFAULT NULL,
  `funcionario_id` INT(11) NULL DEFAULT NULL,
  `hora_servico_id` INT(11) NULL DEFAULT NULL,
  `valor_controle` VARCHAR(85) NULL DEFAULT NULL,
  `valor_controle_2` VARCHAR(85) NULL DEFAULT NULL,
  `valor_generico_1` VARCHAR(85) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14078179
DEFAULT CHARACTER SET = latin1;

USE `servicos_db` ;

-- -----------------------------------------------------
-- Placeholder table for view `servicos_db`.`v_servico_estab_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicos_db`.`v_servico_estab_info` (`serv_estab_id` INT, `Servico` INT, `Servico ID` INT, `Estabelecimento` INT, `Estabelecimento ID` INT);

-- -----------------------------------------------------
-- function usf_verificar_horario
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` FUNCTION `usf_verificar_horario`(p_dia_inicio date
								    , p_hora_inicio datetime
                                    , p_servico_id int
                                    , p_estab_id int
                                    , p_cliente_id int) RETURNS tinyint(1)
begin
    declare v_servicos_agendados int default 0;
    declare v_qtd_simultanea int default 1;
    declare v_periodo_disp int default 0;
    
    -- Verifica quantos servicos estao agendados neste dia e horario
	select count(*)
    into v_servicos_agendados
    from t_hora_servico
    where dia_inicio = p_dia_inicio
    and hora_inicio = p_hora_inicio
    and status_servico = 'A' -- servico agendado
    and cliente_id <> p_cliente_id
    and servico_id = p_servico_id;
    
    -- Verifica quantos servicos podem ocorrer simultaneamente neste estabelecimento
    select count(qtd_simultanea)
    into v_qtd_simultanea
    from t_servico_estabelecimento
    where estab_id = p_estab_id
    and servico_id = p_servico_id;
    
    -- Verifica o período em que o serviço fica diponível
    select count(1)
    into v_periodo_disp
    from t_servicos
    where p_hora_inicio >= hora_disp_inicio 
    and (select usf_verificar_termino_serv(p_hora_inicio, p_servico_id)) <= hora_disp_fim;
    
    -- Se a quantidade de servicos ja agendados for maior ou igual a quantidade permitida simultaneamente, retorna false
    if (v_servicos_agendados >= v_qtd_simultanea) then
		return false;
    end if;
    
    -- Se o periodo estiver fora do estabelecido para o servico, retorna false
	if (v_periodo_disp = 0) then
		return false;
    end if;
    
    -- Se não retornou false até aqui, retorna true
    return true;
    
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function usf_verificar_servicos_concluidos
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` FUNCTION `usf_verificar_servicos_concluidos`() RETURNS int(11)
    DETERMINISTIC
begin
	declare v_retorno int default 0;
    
    select count(1) 
    into v_retorno
    from t_hora_servico 
    where status_servico = 'A'
	and dia_fim < curdate()
	  or (hora_fim <= curtime() and dia_fim <= curdate());
	
    return v_retorno;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- function usf_verificar_termino_serv
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` FUNCTION `usf_verificar_termino_serv`(p_hora_inicio time
										 , p_servico_id int) RETURNS time
begin
	declare v_tempo_servico time default 0;
    declare v_termino_servico time default 0;

	select tempo_medio_servico
    into v_tempo_servico
    from t_servicos
    where servico_id = p_servico_id;
    
    select addtime(p_hora_inicio, v_tempo_servico)
    into v_termino_servico;
    
    /*select date_add(p_hora_inicio, interval v_tempo_servico minute)
    into v_termino_servico; */
    
    return v_termino_servico;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure usp_agendar_horario
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_agendar_horario`(in p_estab_id int
                                   , in p_servico_id int
                                   , in p_cliente_id int
                                   , in p_dia_inicio date
                                   , in p_hora_inicio time
                                   , in p_funcionario_id int)
begin
	-- Declara variaveis
	declare v_servico_ok int default 0;
    declare v_funcionario_ok int default 0;
    declare v_hora_fim_serv time default 0;
    declare v_cliente_associado int default 0;
    declare v_preco_servico float default 0;
    
	/* Verifica se o serviço esta disponível */
    select count(1) 
    into v_servico_ok
    from t_servicos
    where status_servico = 'D'
    and servico_id = p_servico_id;
    
    -- Se nao estiver disponivel, lança exception e retorna false
    if (v_servico_ok = 0) then
		-- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , categoria_id
                             , cliente_id
                             , estab_id
                             , end_estab_id)
        values(0
			 , 300 -- t_lookup
             , 'Servico nao disponivel'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , p_servico_id
             , (select categoria_id from t_servicos where servico_id = p_servico_id)
             , p_cliente_id
             , p_estab_id
             , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id));
        
        commit;
        
        -- Tratamento de erro
        set v_servico_ok = 0;
        signal sqlstate '45000' 
        set message_text = "Servico nao disponivel";
    end if;
    
    /* Verifica funcionario escolhido */
    -- Caso algum funcionario foi escolhido
	if (nullif(p_funcionario_id, '') is not null) then
		
        -- Verifica se ele esta disponivel para o servico
		select count(1)
		into v_funcionario_ok
		from t_servico_funcionario
		where func_id = p_funcionario_id -- parametro de funcionario escolhido
        and status_funcionario = 'D'; -- status D disponivel
		                     
		-- Se o count não retornar nenhuma linha, o funcionario nao esta disponivel, loga erro
		if (v_funcionario_ok = 0) then
        -- Insere transação
			insert into t_tran_log(log_id
								 , tran_type
								 , descricao
								 , dia_inicio_tran
								 , hora_inicio_tran
								 , dia_fim_tran
								 , hora_fim_tran
								 , servico_id
								 , categoria_id
								 , cliente_id
								 , estab_id
								 , end_estab_id
                                 , funcionario_id)
			values(0
				 , 500 -- t_lookup
				 , 'Funcionario nao disponivel para este horario'
				 , curdate() -- dia_inicio_tran
				 , curtime() -- hora_inicio_tran
				 , curdate() -- dia_fim_tran
				 , curtime() -- hora_fim_tran
				 , p_servico_id
				 , (select categoria_id from t_servicos where servico_id = p_servico_id)
				 , p_cliente_id
				 , p_estab_id
				 , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id)
                 , p_funcionario_id);
				 
			commit;
			
			-- Tratamento de erro
			set v_servico_ok = 0;
			signal sqlstate '45000' set message_text = "Funcionario nao disponivel para este horario";
        end if;
	end if;
    
    /* Verifica se o horario esta disponivel */                                               
	if (select usf_verificar_horario(p_dia_inicio, p_hora_inicio, p_servico_id, p_estab_id, p_cliente_id)) then
        
        -- Verifica o horario de termino do servico
        select usf_verificar_termino_serv(p_hora_inicio, p_servico_id)
        into v_hora_fim_serv;
        
        -- Verifica se o cliente ja agendou algum sevico neste dia e horario
        select count(*) 
        into v_cliente_associado
        from t_hora_servico
        where cliente_id = p_cliente_id
        and dia_inicio = p_dia_inicio
        and (p_hora_inicio between hora_inicio and date_sub(hora_fim, interval 1 minute) 
		  or v_hora_fim_serv < hora_fim);
	
		-- Se ja estiver associado, loga erro
        if(v_cliente_associado > 0) then
			-- Insere transação
			insert into t_tran_log(log_id
								 , tran_type
								 , descricao
								 , dia_inicio_tran
								 , hora_inicio_tran
								 , dia_fim_tran
								 , hora_fim_tran
								 , servico_id
								 , categoria_id
								 , cliente_id
								 , estab_id
								 , end_estab_id)
			values(0
				 , 400 -- t_lookup
				 , 'Servico ja agendado para este horario'
				 , curdate() -- dia_inicio_tran
				 , curtime() -- hora_inicio_tran
				 , curdate() -- dia_fim_tran
				 , curtime() -- hora_fim_tran
				 , p_servico_id
				 , (select categoria_id from t_servicos where servico_id = p_servico_id)
				 , p_cliente_id
				 , p_estab_id
				 , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id));
				 
			commit;
			
			-- Tratamento de erro
			set v_servico_ok = 0;
			signal sqlstate '45000' set message_text = "Servico ja agendado para este horario";
        end if;
        
        /* Se passou por todos os IFs, agenda o servico */
        -- Caso algum funcionario foi escolhido
		if (nullif(p_funcionario_id, '') is not null) then
			-- Seta status do funcionario pra ocupado
            update t_servico_funcionario
            set status_funcionario = 'O'
            where func_id = p_funcionario_id;
            
            -- Verifica o preco para o servico com o funcionario escolhido
            select preco_servico
            into v_preco_servico
            from t_servico_funcionario
            where func_id = p_funcionario_id;
        -- Senao verifica o preco default do servico 
        else 
			select preco_servico
            into v_preco_servico
            from t_servicos
            where servico_id = p_servico_id;
        end if;
        
		-- Insere na t_hora_servico
		insert into t_hora_servico(hora_servico_id
								 , cliente_id
                                 , servico_id
                                 , dia_inicio
                                 , hora_inicio
                                 , dia_fim
                                 , hora_fim
                                 , status_servico
                                 , preco_servico
                                 , funcionario_id)
        values(0
			 , p_cliente_id
             , p_servico_id
             , p_dia_inicio
             , p_hora_inicio
             , p_dia_inicio -- VERIFICAR
             , v_hora_fim_serv
             , 'A'
             , v_preco_servico
             , p_funcionario_id);
             
        -- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , categoria_id
                             , cliente_id
                             , estab_id
                             , end_estab_id
                             , hora_servico_id
                             , valor_controle
                             , valor_controle_2
                             , funcionario_id) -- funcionario
        values(0
			 , 100 -- t_lookup
             , 'Agendamento de Servico'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , p_servico_id
             , (select categoria_id from t_servicos where servico_id = p_servico_id)
             , p_cliente_id
             , p_estab_id
             , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id)
             , last_insert_id() 
             , null
             , null
             , p_funcionario_id);
	else
       -- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , categoria_id
                             , cliente_id
                             , estab_id
                             , end_estab_id)
        values(0
			 , 200 -- t_lookup
             , 'Hora nao disponivel'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , p_servico_id
             , (select categoria_id from t_servicos where servico_id = p_servico_id)
             , p_cliente_id
             , p_estab_id
             , (select end_estab_id from t_estabelecimentos_dtl where estab_id = p_estab_id));
             
		commit;
        
        -- Tratamento de erro
        set v_servico_ok = 0;
        signal sqlstate '45000' set message_text = "Hora nao disponivel";
	end if;
    
    commit;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure usp_associar_funcionario_servico
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_associar_funcionario_servico`(in p_funcionario_id int
												 , in p_estab_id int
                                                 , in p_servico_id int
												 , in p_preco_servico float)
begin
	declare v_servico_funcionario_exists int default 0;
    declare v_serv_estab_id int default null;
    
    -- Verifica o serviço associado ao estabelecimento
    select serv_estab_id
    into v_serv_estab_id
    from t_servico_estabelecimento
    where servico_id = p_servico_id
    and estab_id = p_estab_id;
    
    -- Verifica se o usuário já esta associado a este serviço X estabelecimento
    select count(1)
    into v_servico_funcionario_exists
    from t_servico_funcionario
    where serv_estab_id = v_serv_estab_id
    and func_id = p_funcionario_id;

	-- Se o serviço esta disponível para o estabelecimento
	if (v_serv_estab_id is null) then
		signal sqlstate '45000' 
        set message_text = "Servico nao disponivel para o estabelecimento";
	-- Se o funcionario já estiver associado a este serviço do estabelecimento, tratamento de erro		
	elseif (v_servico_funcionario_exists > 0) then
		signal sqlstate '45000' 
        set message_text = "Funcionario ja associado a este servico do estabelecimento";
    end if;
    
    
       
    -- Associa funcionário ao serviço
    insert into t_servico_funcionario(serv_func_id
									, serv_estab_id
                                    , func_id
                                    , preco_servico)
    values(0
		 , v_serv_estab_id
         , p_funcionario_id
         , p_preco_servico);
         
    commit;     

end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure usp_cadastrar_estab
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_cadastrar_estab`(in p_nome_estab varchar(100)
								   , in p_pais_estab varchar(50)
                                   , in p_estado_estab varchar(80)
                                   , in p_cidade_estab varchar(80)
                                   , in p_bairro_estab varchar(80)
                                   , in p_rua_estab varchar(80)
                                   , in p_numero_estab varchar(10)
                                   , in p_cep_estab varchar(10)
                                   , in p_complemento_estab varchar(255)
                                   , in p_telefone_estab varchar(20)
                                   , in p_whatsapp_estab varchar(20)
                                   , in p_facebook_estab varchar(70)
                                   , in p_site_estab varchar(70)
                                   , in p_usuario int(11))
begin 

	-- Insere dados na tabela principal de estabelecimentos
	insert into t_estabelecimentos (estab_id
								  , nome_estab
								  , usuario_id) 
    values (0
		  , p_nome_estab
          , p_usuario);
          
    -- Atribui ultimo id inserido a variável
	select last_insert_id() 
	into @ultimo_estab_inserido;      
          
	-- Insere detalhes do estabelecimento
	insert into t_estabelecimentos_dtl (end_estab_id
									  , estab_id
									  , pais_estab
									  , estado_estab
									  , cidade_estab
									  , bairro_estab
									  , rua_estab
									  , numero_estab
									  , cep_estab
									  , complemento_estab
									  , telefone_estab
									  , whatsapp_estab
									  , facebook_estab
									  , site_estab)
    values(0
		, @ultimo_estab_inserido
        , p_pais_estab
		, p_estado_estab
		, p_cidade_estab
		, p_bairro_estab
		, p_rua_estab
		, p_numero_estab
		, p_cep_estab
		, p_complemento_estab
		, p_telefone_estab
		, p_whatsapp_estab
		, p_facebook_estab
		, p_site_estab);
        
	commit;

end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure usp_cadastrar_funcionario
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_cadastrar_funcionario`(in p_nome_funcionario varchar(80),
											in p_estab_id int,
											in p_fone_funcionario varchar(20),
											in p_whatsapp_funcionario varchar(20))
begin
	declare v_func_exists int default 0;
    declare v_estab_exists int default 0;
    
    -- Verifica se esse funcionario já esta cadastrado no estabelecimento (nome, estabelecimento, fone + whats)
	select count(1)
    into v_func_exists
    from t_funcionarios
    where upper(nome_funcionario) = upper(p_nome_funcionario)
    and estab_id = p_estab_id
    and (fone_funcionario = p_fone_funcionario or whatsapp_funcionario = p_whatsapp_funcionario);
    
    -- Verifica se o estabelecimento existe
    select count(1)
    into v_estab_exists
    from t_estabelecimentos
    where estab_id = p_estab_id;

    -- Se o funcionário já estiver cadastrado, tratamento de erro
    if(v_func_exists > 0) then 
        signal sqlstate '45000' 
        set message_text = "Funcionario ja cadastrado";
	-- Se o estabelecimento não existir, tratamento de erro
    elseif (v_estab_exists = 0) then    
		signal sqlstate '45000' 
        set message_text = "Estabelecimento nao cadastrado";
    end if;
	
    -- Insere funcionário
    insert into t_funcionarios (funcionario_id
							  , nome_funcionario
                              , estab_id
                              , fone_funcionario
                              , whatsapp_funcionario)
    values (0
		  , p_nome_funcionario
          , p_estab_id
          , p_fone_funcionario
          , p_whatsapp_funcionario);
          
    commit;      
		
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure usp_cadastrar_servico_estab
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_cadastrar_servico_estab`(in p_estabelecimento_id int
										   , in p_categoria_id int
										   , in p_nome_servico varchar(80)
                                           , in p_desc_servico varchar(255)
                                           , in p_preco_servico float
                                           , in p_preco_hora_servico float
                                           , in p_tempo_medio_servico time
                                           , in p_status_servico char(1)
                                           , in p_qtd_simultanea int
                                           , in p_taxa_cancelamento float
                                           , in p_cancelavel_flag char(1)
                                           , in p_hora_disp_inicio time
                                           , in p_hora_disp_fim time)
begin
	declare v_count int default 0;
    
    -- Verifica se existe um servico com o mesmo nome, categoria, preco, tempo e status já cadastrado 
    select count(1) 
    into v_count
    from t_servicos
    where p_categoria_id = categoria_id
	and upper(p_nome_servico) = upper(nome_servico)
	and ifnull(p_preco_servico, 0) = ifnull(preco_servico, 0)
	and ifnull(p_preco_hora_servico, 0) = ifnull(preco_hora_servico, 0)
	and p_tempo_medio_servico = tempo_medio_servico
	and p_status_servico = status_servico;

	-- Se tiver, nao insere servico duplicado
	if (v_count > 0) then
		-- Tratamento de erro
        signal sqlstate '45000' 
        set message_text = "Servico ja cadastrado";
    
    -- Senao, insere serivo
    else
		-- Insere servico
		insert into t_servicos(servico_id
							 , categoria_id
							 , nome_servico
							 , desc_servico
							 , preco_servico
							 , preco_hora_servico
							 , tempo_medio_servico
							 , status_servico
							 , taxa_cancelamento
							 , cancelavel_flag
                             , hora_disp_inicio
                             , hora_disp_fim)
						values(0
							 , p_categoria_id
							 , p_nome_servico
							 , p_desc_servico
							 , p_preco_servico
							 , p_preco_hora_servico
							 , p_tempo_medio_servico
							 , p_status_servico
							 , p_taxa_cancelamento
							 , p_cancelavel_flag
                             , p_hora_disp_inicio
                             , p_hora_disp_fim);
							 
		-- Atribui ultimo id inserido a variável
		select last_insert_id() 
		into @ultimo_servico_inserido;
		
		-- Insere na tabela de relação de serviços X estabelecimentos
		insert into t_servico_estabelecimento(serv_estab_id
											, servico_id
											, estab_id
											, qtd_simultanea)
									   values(0
											, @ultimo_servico_inserido
											, p_estabelecimento_id
											, p_qtd_simultanea);

		-- Commita transação
		commit;
     end if;   

end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure usp_cancelar_hora_servico
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_cancelar_hora_servico`(in p_hora_servico_id int
										 , in p_motivo_cancelamento varchar(150))
begin
	declare v_estab_id int;
    declare v_servico_id int;
    declare v_cliente_id int;
    declare v_dia_inicio int;
    declare v_hora_inicio int;
    
    -- Encontra dados da hora agendada
    select (select estab_id from t_servico_estabelecimento where servico_id = hser.servico_id) -- estab_id
		 , hser.servico_id
         , hser.cliente_id
         , hser.hora_inicio
         , hser.dia_inicio
    into v_estab_id
       , v_servico_id
       , v_cliente_id
       , v_dia_inicio
       , v_hora_inicio
    from t_hora_servico hser
    where hser.hora_servico_id = p_hora_servico_id;
    
    -- Se as variaveis estiverem nulas, a hora nao foi encontrada
    if (v_estab_id is null or v_servico_id is null) then
        -- Tratamento de erro
        signal sqlstate '45000' 
        set message_text = "Hora ja cancelada";
    
    -- Senao, efetua o cancelamento da hora
    else
		delete from t_hora_servico
        where hora_servico_id = p_hora_servico_id;
        
        -- Insere transação
        insert into t_tran_log(log_id
							 , tran_type
                             , descricao
                             , dia_inicio_tran
                             , hora_inicio_tran
                             , dia_fim_tran
                             , hora_fim_tran
                             , servico_id
                             , cliente_id
                             , estab_id
                             , end_estab_id
                             , valor_controle -- dia inicio servico
                             , valor_controle_2 -- hora inicio servico
                             , valor_generico_1) -- motivo cancelamento
        values(0
			 , 110 -- t_lookup
             , 'Cancelamento de servico'
             , curdate() -- dia_inicio_tran
             , curtime() -- hora_inicio_tran
             , curdate() -- dia_fim_tran
             , curtime() -- hora_fim_tran
             , v_servico_id
             , v_cliente_id
             , v_estab_id
             , v_estab_id
             , v_dia_inicio
             , v_hora_inicio
             , p_motivo_cancelamento);
        
        commit;	
    
    end if;

end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure usp_fecha_servicos
-- -----------------------------------------------------

DELIMITER $$
USE `servicos_db`$$
CREATE DEFINER=`servicos_db`@`%` PROCEDURE `usp_fecha_servicos`()
begin 
    declare v_servico_id int default 0;
    declare v_funcionario_id int default 0;
    declare v_cliente_id int default 0;
    declare v_hora_servico_id int default 0;
    
    -- Se tiver servicos ainda nao concluidos, Faz loop com as operações
    while (usf_verificar_servicos_concluidos() > 0) do		
		-- Pega o primeiro servico que pode ser fechado
		select servico_id, funcionario_id, cliente_id, hora_servico_id
		into v_servico_id, v_funcionario_id, v_cliente_id, v_hora_servico_id
		from t_hora_servico
		where status_servico = 'A'
		and dia_fim < curdate()
		  or (hora_fim <= curtime() and dia_fim <= curdate())
		limit 1;
		  
		-- Insere transação (para possiveis relatorios)
		insert into t_tran_log(log_id
							 , tran_type
							 , descricao
							 , dia_inicio_tran
							 , hora_inicio_tran
							 , dia_fim_tran
							 , hora_fim_tran
							 , servico_id
							 , cliente_id
							 , funcionario_id)
		values(0
			 , 600
			 , 'Conclusao/fechamento de servico agendado'
			 , curdate()
			 , curtime()
			 , curdate()
			 , curtime()
			 , v_servico_id
			 , v_cliente_id
			 , ifnull(v_funcionario_id, 'Funcionario nao especificado'));
	
		-- Seta o status do servico para C
		update t_hora_servico
		set status_servico = 'C'
		where status_servico = 'A'
		and dia_fim < curdate()
		  or (hora_fim <= curtime() and dia_fim <= curdate())
		and hora_servico_id = v_hora_servico_id;
	end while;			 
	
    commit;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- View `servicos_db`.`v_servico_estab_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servicos_db`.`v_servico_estab_info`;
USE `servicos_db`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`servicos_db`@`%` SQL SECURITY DEFINER VIEW `servicos_db`.`v_servico_estab_info` AS select `seb`.`serv_estab_id` AS `serv_estab_id`,(select `servicos_db`.`t_servicos`.`nome_servico` from `servicos_db`.`t_servicos` where (`servicos_db`.`t_servicos`.`servico_id` = `seb`.`servico_id`)) AS `Servico`,`seb`.`servico_id` AS `Servico ID`,(select `servicos_db`.`t_estabelecimentos`.`nome_estab` from `servicos_db`.`t_estabelecimentos` where (`servicos_db`.`t_estabelecimentos`.`estab_id` = `seb`.`estab_id`)) AS `Estabelecimento`,`seb`.`estab_id` AS `Estabelecimento ID` from `servicos_db`.`t_servico_estabelecimento` `seb`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
