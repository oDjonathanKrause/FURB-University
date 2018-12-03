CREATE DATABASE  IF NOT EXISTS `servicos_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `servicos_db`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: servicos_db
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_alias`
--

DROP TABLE IF EXISTS `t_alias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_alias` (
  `alias_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tabela` varchar(80) NOT NULL,
  `alias_tabela` varchar(5) NOT NULL,
  `desc_tabela` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`alias_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_alias`
--

LOCK TABLES `t_alias` WRITE;
/*!40000 ALTER TABLE `t_alias` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_alias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_categorias`
--

DROP TABLE IF EXISTS `t_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_categorias` (
  `categoria_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_categoria` varchar(80) DEFAULT NULL,
  `desc_categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_categorias`
--

LOCK TABLES `t_categorias` WRITE;
/*!40000 ALTER TABLE `t_categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_clientes`
--

DROP TABLE IF EXISTS `t_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_clientes` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(80) NOT NULL,
  `cpf_cliente` varchar(20) DEFAULT NULL,
  `endereco_cliente` varchar(255) DEFAULT NULL,
  `email_cliente` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_clientes`
--

LOCK TABLES `t_clientes` WRITE;
/*!40000 ALTER TABLE `t_clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_estabelecimentos`
--

DROP TABLE IF EXISTS `t_estabelecimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_estabelecimentos` (
  `estab_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_estab` varchar(100) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`estab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_estabelecimentos`
--

LOCK TABLES `t_estabelecimentos` WRITE;
/*!40000 ALTER TABLE `t_estabelecimentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_estabelecimentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_estabelecimentos_dtl`
--

DROP TABLE IF EXISTS `t_estabelecimentos_dtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_estabelecimentos_dtl` (
  `end_estab_id` int(11) NOT NULL AUTO_INCREMENT,
  `estab_id` int(11) DEFAULT NULL,
  `pais_estab` varchar(80) DEFAULT NULL,
  `estado_estab` varchar(80) DEFAULT NULL,
  `cidade_estab` varchar(80) DEFAULT NULL,
  `bairro_estab` varchar(80) DEFAULT NULL,
  `rua_estab` varchar(80) DEFAULT NULL,
  `numero_estab` varchar(10) DEFAULT NULL,
  `cep_estab` varchar(10) DEFAULT NULL,
  `complemento_estab` varchar(255) DEFAULT NULL,
  `telefone_estab` varchar(20) DEFAULT NULL,
  `whatsapp_estab` varchar(20) DEFAULT NULL,
  `facebook_estab` varchar(70) DEFAULT NULL,
  `site_estab` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`end_estab_id`),
  KEY `estab_id` (`estab_id`),
  CONSTRAINT `t_estabelecimentos_dtl_ibfk_1` FOREIGN KEY (`estab_id`) REFERENCES `t_estabelecimentos` (`estab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_estabelecimentos_dtl`
--

LOCK TABLES `t_estabelecimentos_dtl` WRITE;
/*!40000 ALTER TABLE `t_estabelecimentos_dtl` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_estabelecimentos_dtl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_funcionarios`
--

DROP TABLE IF EXISTS `t_funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_funcionarios` (
  `funcionario_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_funcionario` varchar(80) NOT NULL,
  `estab_id` int(11) NOT NULL,
  `fone_funcionario` varchar(20) DEFAULT NULL,
  `whatsapp_funcionario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`funcionario_id`),
  KEY `estab_id` (`estab_id`),
  CONSTRAINT `t_funcionarios_ibfk_1` FOREIGN KEY (`estab_id`) REFERENCES `t_estabelecimentos` (`estab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_funcionarios`
--

LOCK TABLES `t_funcionarios` WRITE;
/*!40000 ALTER TABLE `t_funcionarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_funcionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_hora_servico`
--

DROP TABLE IF EXISTS `t_hora_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_hora_servico` (
  `hora_servico_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) NOT NULL,
  `dia_inicio` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `dia_fim` date NOT NULL,
  `hora_fim` time NOT NULL,
  `status_servico` char(1) DEFAULT NULL,
  `servico_id` int(11) NOT NULL,
  `preco_servico` float DEFAULT '0',
  `funcionario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`hora_servico_id`),
  KEY `id_cliente` (`cliente_id`),
  KEY `t_hora_servico_ibfk_2_idx` (`servico_id`),
  KEY `t_hora_servico_ibfk_3_idx` (`funcionario_id`),
  CONSTRAINT `t_hora_servico_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `t_clientes` (`cliente_id`),
  CONSTRAINT `t_hora_servico_ibfk_2` FOREIGN KEY (`servico_id`) REFERENCES `t_servicos` (`servico_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_hora_servico_ibfk_3` FOREIGN KEY (`funcionario_id`) REFERENCES `t_servico_funcionario` (`func_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_hora_servico`
--

LOCK TABLES `t_hora_servico` WRITE;
/*!40000 ALTER TABLE `t_hora_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_hora_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_lookup`
--

DROP TABLE IF EXISTS `t_lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_lookup` (
  `lookup_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tabela` varchar(45) NOT NULL,
  `nome_coluna` varchar(45) DEFAULT NULL,
  `valor` varchar(45) DEFAULT NULL,
  `descricao` varchar(80) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lookup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_lookup`
--

LOCK TABLES `t_lookup` WRITE;
/*!40000 ALTER TABLE `t_lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_servico_estabelecimento`
--

DROP TABLE IF EXISTS `t_servico_estabelecimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_servico_estabelecimento` (
  `serv_estab_id` int(11) NOT NULL AUTO_INCREMENT,
  `servico_id` int(11) NOT NULL,
  `estab_id` int(11) NOT NULL,
  `qtd_simultanea` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`serv_estab_id`),
  KEY `servico_id` (`servico_id`),
  KEY `estab_id` (`estab_id`),
  CONSTRAINT `t_servico_estabelecimento_ibfk_1` FOREIGN KEY (`servico_id`) REFERENCES `t_servicos` (`servico_id`),
  CONSTRAINT `t_servico_estabelecimento_ibfk_2` FOREIGN KEY (`estab_id`) REFERENCES `t_estabelecimentos` (`estab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_servico_estabelecimento`
--

LOCK TABLES `t_servico_estabelecimento` WRITE;
/*!40000 ALTER TABLE `t_servico_estabelecimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_servico_estabelecimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_servico_funcionario`
--

DROP TABLE IF EXISTS `t_servico_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_servico_funcionario` (
  `serv_func_id` int(11) NOT NULL AUTO_INCREMENT,
  `serv_estab_id` int(11) NOT NULL,
  `func_id` int(11) NOT NULL,
  `preco_servico` float DEFAULT NULL,
  `status_funcionario` char(1) DEFAULT 'D',
  PRIMARY KEY (`serv_func_id`),
  KEY `serv_estab_id` (`serv_estab_id`),
  KEY `func_id` (`func_id`),
  CONSTRAINT `t_servico_funcionario_ibfk_1` FOREIGN KEY (`serv_estab_id`) REFERENCES `t_servico_estabelecimento` (`serv_estab_id`),
  CONSTRAINT `t_servico_funcionario_ibfk_2` FOREIGN KEY (`func_id`) REFERENCES `t_funcionarios` (`funcionario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_servico_funcionario`
--

LOCK TABLES `t_servico_funcionario` WRITE;
/*!40000 ALTER TABLE `t_servico_funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_servico_funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_servicos`
--

DROP TABLE IF EXISTS `t_servicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_servicos` (
  `servico_id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria_id` int(11) DEFAULT NULL,
  `nome_servico` varchar(80) NOT NULL,
  `desc_servico` varchar(255) DEFAULT NULL,
  `hora_disp_inicio` time NOT NULL,
  `hora_disp_fim` time NOT NULL,
  `preco_servico` float DEFAULT NULL,
  `preco_hora_servico` float DEFAULT NULL,
  `tempo_medio_servico` time NOT NULL,
  `status_servico` char(1) NOT NULL,
  `taxa_cancelamento` float NOT NULL DEFAULT '0',
  `cancelavel_flag` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`servico_id`),
  KEY `categoria_id` (`categoria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_servicos`
--

LOCK TABLES `t_servicos` WRITE;
/*!40000 ALTER TABLE `t_servicos` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_servicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tran_log`
--

DROP TABLE IF EXISTS `t_tran_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_tran_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `tran_type` int(11) NOT NULL,
  `descricao` varchar(85) NOT NULL,
  `dia_inicio_tran` date DEFAULT NULL,
  `hora_inicio_tran` time DEFAULT NULL,
  `dia_fim_tran` date DEFAULT NULL,
  `hora_fim_tran` time DEFAULT NULL,
  `servico_id` int(11) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `estab_id` int(11) DEFAULT NULL,
  `end_estab_id` int(11) DEFAULT NULL,
  `funcionario_id` int(11) DEFAULT NULL,
  `hora_servico_id` int(11) DEFAULT NULL,
  `valor_controle` varchar(85) DEFAULT NULL,
  `valor_controle_2` varchar(85) DEFAULT NULL,
  `valor_generico_1` varchar(85) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tran_log`
--

LOCK TABLES `t_tran_log` WRITE;
/*!40000 ALTER TABLE `t_tran_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_tran_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_servico_estab_info`
--

DROP TABLE IF EXISTS `v_servico_estab_info`;
/*!50001 DROP VIEW IF EXISTS `v_servico_estab_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_servico_estab_info` AS SELECT 
 1 AS `serv_estab_id`,
 1 AS `Servico`,
 1 AS `Servico ID`,
 1 AS `Estabelecimento`,
 1 AS `Estabelecimento ID`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'servicos_db'
--

--
-- Dumping routines for database 'servicos_db'
--
/*!50003 DROP FUNCTION IF EXISTS `usf_verificar_horario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `usf_verificar_servicos_concluidos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `usf_verificar_termino_serv` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_agendar_horario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_associar_funcionario_servico` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_cadastrar_estab` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_cadastrar_funcionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
		
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_cadastrar_servico_estab` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_cancelar_hora_servico` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `usp_fecha_servicos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `v_servico_estab_info`
--

/*!50001 DROP VIEW IF EXISTS `v_servico_estab_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`servicos_db`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_servico_estab_info` AS select `seb`.`serv_estab_id` AS `serv_estab_id`,(select `servicos_db`.`t_servicos`.`nome_servico` from `t_servicos` where (`servicos_db`.`t_servicos`.`servico_id` = `seb`.`servico_id`)) AS `Servico`,`seb`.`servico_id` AS `Servico ID`,(select `servicos_db`.`t_estabelecimentos`.`nome_estab` from `t_estabelecimentos` where (`servicos_db`.`t_estabelecimentos`.`estab_id` = `seb`.`estab_id`)) AS `Estabelecimento`,`seb`.`estab_id` AS `Estabelecimento ID` from `t_servico_estabelecimento` `seb` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-19 20:37:35
