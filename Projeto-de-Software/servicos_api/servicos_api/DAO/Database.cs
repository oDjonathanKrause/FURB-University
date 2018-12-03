using System.Collections.Generic;
using MySql.Data.MySqlClient;
using System.Data.SqlClient;
using System;
using Newtonsoft.Json;

namespace servicos_api.DAO
{
    public class Database
    {
        private static MySqlConnection conn;
        private static string database = "servicos_db";
        private static string data_source = "mysql857.umbler.com";
        private static string port = "41890";
        private static string user_id = "servicos_db";
        private static string password = "2|b/K/rzI6T7";

        /* Conexão com banco de dados local (localhost) 
        private static MySqlConnection conn;
        private static string database = "servicos_db";
        private static string data_source = "localhost"; //localhost
        private static string user_id = "root";
        private static string password = "";
        private static string port = "3306";*/

        /// <summary> Método que efetua a conexão com o banco de dados </summary>
        /// <returns> Conexão com o banco "conn" no tipo MySqlConnection </returns>
        public static MySqlConnection connectDB()
        {
            // Atribui valores para conexão
            string ConnectString;
            ConnectString = "Database=" + database
                         + ";Data Source=" + data_source
                         + ";Port=" + port
                         + ";User Id=" + user_id
                         + ";Password=" + password
                         + ";convert zero datetime=True"; // Corrige formato de data (https://goo.gl/9DDBPE) 

            // Faz a conexão
            conn = new MySqlConnection(ConnectString);
            try
            {
                //conn.Open();
                return conn;
            }
            catch (MySqlException ex)
            {
                Console.WriteLine("Failed to connect" + ex.Message);
                return conn;
            }
        }

        /// <summary> Método que testa a conexão com o banco de dados </summary>
        /// <returns> Retorna a String "Conexao OK" ou "Erro na conexao + erro"</returns>
        public static String testDatabase()
        {
            // Atribui valores para conexão
            string ConnectString;
            ConnectString = "Database=" + database
                       + ";Data Source=" + data_source
                       + ";Port=" + port
                       + ";User Id=" + user_id
                       + ";Password=" + password;

            //ConnectString = "Server = mysql857.umbler.com; Port = 41890; Database = servicos_db; Uid = servicos_db_user;password=8D{KiPN8n|;";

            // Faz a conexão
            conn = new MySqlConnection(ConnectString);
            try
            {
                conn.Open();
                return "Conexao OK";
            }
            catch (MySqlException ex)
            {
                return "Erro na conexao: " + ex.Message;
            }
        }

    }
}