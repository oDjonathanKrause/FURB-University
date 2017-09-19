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
    public class CategoriasDAO
    {
        public static List<Model.Categoria> categorias = new List<Model.Categoria>();

        /// <summary> Método que lista categorias </summary>
        /// <returns>Lista de categorias no tipo Model.Categoria </returns>
        public static string listarCategorias()
        {
            // Limpa lista
            categorias.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = "select categoria_id, nome_categoria, desc_categoria from t_categorias;";
            
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
                        // Cria um objeto de Categoria com os dados retornados no select
                        categorias.Add(new Model.Categoria
                        {
                            Categoria_id = reader.GetInt16(0),
                            Nome_categoria = reader.GetString(1),
                            Desc_categoria = reader.GetString(2)
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
            return JsonConvert.SerializeObject(categorias);
        }


    }
}