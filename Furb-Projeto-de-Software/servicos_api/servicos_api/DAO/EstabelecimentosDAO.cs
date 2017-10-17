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
    public class EstabelecimentosDAO
    {
        public static List<Model.Estabelecimento> estabelecimentos = new List<Model.Estabelecimento>();
        public static Estabelecimento estabelecimento;

        /// <summary> Método que lista estabelecimentos </summary>
        /// <returns> Lista de estabelecimentos no tipo Model.Estabelecimento </returns>
        public static string listarEstabelecimentos()
        {
            // Limpa lista
            estabelecimentos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select estab_id, nome_estab from t_estabelecimentos;";

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
                        // Cria um objeto de Estabelecimento com os dados retornados no select
                        estabelecimentos.Add(new Model.Estabelecimento
                        {
                            Estab_id = reader.GetInt16(0),
                            Nome_estab = reader.GetString(1)
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
            return JsonConvert.SerializeObject(estabelecimentos);
        }

        public static string listarEstabelecimentosPorCidade(string cidade)
        {
            // Limpa lista
            estabelecimentos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select t_estabelecimentos.estab_id, nome_estab from t_estabelecimentos, t_estabelecimentos_dtl where t_estabelecimentos_dtl.cidade_estab = '" + cidade + "' and t_estabelecimentos_dtl.estab_id = t_estabelecimentos.estab_id;";


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
                        // Cria um objeto de Estabelecimento com os dados retornados no select
                        estabelecimentos.Add(new Model.Estabelecimento
                        {
                            Estab_id = reader.GetInt16(0),
                            Nome_estab = reader.GetString(1)
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
            return JsonConvert.SerializeObject(estabelecimentos);
        }

        public static string listarEstabelecimentoId(int estab_id)
        {
            estabelecimento = null;

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select nome_estab from t_estabelecimentos where estab_id = " + estab_id + ";";


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
                        // Cria um objeto de Estabelecimento com os dados retornados no select
                        estabelecimento = new Model.Estabelecimento()
                        {
                            Nome_estab = reader.GetString(0)
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
            return JsonConvert.SerializeObject(estabelecimento);
        }


    }
}