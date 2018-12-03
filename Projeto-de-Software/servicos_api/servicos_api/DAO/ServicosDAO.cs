using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using servicos_api.DAO;
using MySql.Data.MySqlClient;
using servicos_api.Model;
using Newtonsoft.Json;
using System.Data.SqlClient;
using System.Data;

namespace servicos_api.DAO
{
    public class ServicosDAO
    {
        // Lista de servicos
        public static List<Model.Servico> servicos = new List<Model.Servico>();
        public static Servico servico;

        public static void teste()
        {
            Console.WriteLine("ola");
        }

        /// <summary> Método que procura nome, descrição e preço de servicos disponíveis.</summary>
        /// <returns>Lista de servicos disponíveis no tipo Model.Servico</returns>
        public static string listarServicos()
        {
            // Limpa lista
            servicos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select nome_servico, desc_servico, preco_servico "
                              + "from t_servicos "
                              + "where status_servico = 'D'; ";

            // Faz o select
            MySqlDataReader reader = null;
            MySqlCommand command = new MySqlCommand(MySQLQuery, conn);
            try
            {
                reader = command.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        servicos.Add(new Model.Servico
                        {
                            Nome_servico = reader.GetString(0),
                            Desc_servico = reader.GetString(1),
                            Preco_servico = reader.GetFloat(2)
                        });
                    }
                }
                reader.Close();
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return MySQLresult;
            }

            // Fecha a conexão com o banco
            conn.Close();

            // Retorna a lista de servicos em json
            return JsonConvert.SerializeObject(servicos);
        }


        /// <summary> Método que procura nome, descrição e preço de servicos disponíveis, ordenado por preço.</summary>
        /// <returns>Lista de servicos disponíveis no tipo Model.Servico ordenado por preço</returns>
        public static string listarServicosOrderPreco()
        {
            // Limpa lista
            servicos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select nome_servico, desc_servico, preco_servico "
                              + "from t_servicos "
                              + "where status_servico = 'D' "
                              + "order by preco_servico; ";

            // Faz o select
            MySqlDataReader reader = null;
            MySqlCommand command = new MySqlCommand(MySQLQuery, conn);
            try
            {
                reader = command.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        servicos.Add(new Model.Servico
                        {
                            Nome_servico = reader.GetString(0),
                            Desc_servico = reader.GetString(1),
                            Preco_servico = reader.GetFloat(2)
                        });
                    }
                }
                reader.Close();
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return MySQLresult;
            }

            // Fecha a conexão com o banco
            conn.Close();

            // Retorna a lista de servicos em json
            return JsonConvert.SerializeObject(servicos);
        }

        /// <summary> Método que procura nome, descrição e preço de servicos disponíveis, ordenado por preço com filtro de categoria.</summary>
        /// <returns>Lista de servicos disponíveis no tipo Model.Servico ordenado por preço onde a categoria é a escolhida</returns>
        /// <param name="categoria_id"></param>
        public static string listarServicosComCategoriaOrderPreco(int categoria_id)
        {
            // Limpa lista
            servicos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select nome_servico, desc_servico, preco_servico "
                              + "from t_servicos serv "
                              + "where serv.status_servico = 'D' "
                              + "and serv.categoria_id = '" + categoria_id + "'; ";

            // Faz o select
            MySqlDataReader reader = null;
            MySqlCommand command = new MySqlCommand(MySQLQuery, conn);
            try
            {
                reader = command.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        servicos.Add(new Model.Servico
                        {
                            Nome_servico = reader.GetString(0),
                            Desc_servico = reader.GetString(1),
                            Preco_servico = reader.GetFloat(2)
                        });
                    }
                }
                reader.Close();
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return MySQLresult;
            }

            // Fecha a conexão com o banco
            conn.Close();

            // Retorna a lista de servicos em json
            return JsonConvert.SerializeObject(servicos);
        }


        /// <summary> Método que lista nome, descrição e preço de servicos disponíveis por estabelecimento ordenado por preço.</summary>
        /// <returns>Lista de servicos disponíveis no estabelecimento X no tipo Model.Servico ordenado por preço</returns>
        public static string listarServicosByEstab(int estab_id)
        {
            // Limpa lista
            servicos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select serv.servico_id "
                                + "   , serv.nome_servico "
                                + "   , serv.desc_servico "
                                + "   , serv.preco_servico "
                                + "   , serv.status_servico "
                                /*   + "   , serv.tempo_medio_servico " */
                                + "from t_servicos serv "
                                + "	inner join t_servico_estabelecimento sest "
                                + "      on serv.servico_id = sest.servico_id "
                                + "where serv.status_servico = 'D' "
                                + "and sest.estab_id = " + estab_id 
                                + " order by serv.preco_servico; ";

            // Faz o select
            MySqlDataReader reader = null;
            MySqlCommand command = new MySqlCommand(MySQLQuery, conn);
            try
            {
                reader = command.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        servicos.Add(new Model.Servico
                        {
                            Servico_id = reader.GetInt32(0),
                            Nome_servico = reader.GetString(1),
                            Desc_servico = reader.GetString(2),
                            Preco_servico = reader.GetFloat(3),
                            Status_servico = reader.GetChar(4),
                            /*   tempoMedioServico = reader.GetDateTime(3) */
                        });
                    }
                }
                reader.Close();
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return MySQLresult;
            }

            // Fecha a conexão com o banco
            conn.Close();

            // Retorna a lista de servicos em json
            return JsonConvert.SerializeObject(servicos);
        }

        /// <summary> Método que lista serviços por categoria </summary>
        /// <returns> Lista de serviços por categoria </returns>
        public static string listarServicosPorCategorias(string cidade)
        {
            // Limpa lista
            servicos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = " select *  "
                                + " from ( "
                                + " 	select cate.nome_categoria "
                                + " 		 , serv.* "
                                + " 		 , (select cidade_estab "
                                + " 			from t_estabelecimentos_dtl "
                                + " 			where estab_id = sest.estab_id) as Cidade "
                                + " 	from t_categorias cate "
                                + " 	inner join t_servicos serv "
                                + " 		on serv.categoria_id = cate.categoria_id "
                                + " 	inner join t_servico_estabelecimento sest     "
                                + " 		on sest.servico_id = serv.servico_id "
                                + " 	where serv.status_servico = 'D') select1 "
                                + " where select1.Cidade = '" + cidade + "'; "; // Constante!

            // Faz o select
            MySqlDataReader reader = null;
            MySqlCommand command = new MySqlCommand(MySQLQuery, conn);
            try
            {
                reader = command.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        // Cria um objeto de Seviço com os dados retornados no select
                        servicos.Add(new Model.Servico
                        {
                            Nome_categoria = reader.GetString(0),
                            Servico_id = reader.GetInt16(1),
                            Categoria_id = reader.GetInt16(2),
                            Nome_servico = reader.GetString(3),
                            Desc_servico = reader.GetString(4),
                            Hora_disp_inicio = reader.GetDateTime(5),
                            Hora_disp_fim = reader.GetDateTime(6),
                            Preco_servico = reader.GetFloat(7),
                            Preco_hora_servico = reader.GetFloat(8),
                            Tempo_medio_servico = reader.GetDateTime(9),
                            Status_servico = reader.GetChar(10),
                            Taxa_cancelamento = reader.GetFloat(11),
                            Cancelavel_flag = reader.GetChar(12),
                            Cidade = reader.GetString(13),
                        });
                    }
                }
                reader.Close();
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return MySQLresult;
            }

            // Fecha a conexão com o banco
            conn.Close();

            // Retorna a lista de estabelecimentos em json
            return JsonConvert.SerializeObject(servicos);
        }

        public static string listarDetalhesServico(int servico_id)
        {
            // Limpa lista
            servico = null;

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select nome_servico, desc_servico, preco_servico, tempo_medio_servico, taxa_cancelamento from t_servicos WHERE servico_id = " + servico_id + ";";
            DateTime dt = new DateTime(0001, 01, 01);
            // Faz o select
            MySqlDataReader reader = null;
            MySqlCommand command = new MySqlCommand(MySQLQuery, conn);
            try
            {
                reader = command.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {

                        servico = new Model.Servico
                        {
                            Nome_servico = reader.GetString(0),
                            Desc_servico = reader.GetString(1),
                            Preco_servico = reader.GetFloat(2),
                            Tempo_medio_servico = dt + reader.GetTimeSpan(3),
                            Taxa_cancelamento = reader.GetFloat(4)
                        };
                    }
                }
                reader.Close();
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return MySQLresult;
            }

            // Fecha a conexão com o banco
            conn.Close();

            // Retorna a lista de estabelecimentos em json
            return JsonConvert.SerializeObject(servico);
        }
    }
}