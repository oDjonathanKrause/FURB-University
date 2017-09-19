using MySql.Data.MySqlClient;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.DAO
{
    public class AgendamentosDAO
    {
        // Lista de servicos agendados
        public static List<Model.Agendamento> agendamentos = new List<Model.Agendamento>();
        public static Model.Agendamento agendamento;

        /// <summary> Método que procura os dados dos serviços agendados.</summary>
        /// <param name="estab_id" estabelecimento></param>
        /// <param name="status_servico" status do servico></param>
        /// <returns>Lista serviços agendados de acordo com o estab e status escolhidos </returns>
        public static string listarAgendamentos(int estab_id, String status_servico)
        {
            // Limpa lista
            agendamentos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = " select hser.Hora_servico_id, hser.Cliente_id, hser.Dia_inicio, hser.Dia_fim, hser.Hora_inicio, hser.Hora_fim, hser.Status_servico, hser.Servico_id, hser.Preco_servico, hser.Funcionario_id, serv.Nome_servico, func.Nome_funcionario, sest.Estab_id "
                              + " from t_hora_servico hser                                             "
                              + "   inner join t_servicos serv                                         "
                              + "     on hser.servico_id = serv.servico_id                             "
                              + "   left join t_funcionarios func                                     "
                              + "     on hser.funcionario_id = func.funcionario_id                     "
                              + "   inner join t_servico_estabelecimento sest                          "
                              + "     on hser.servico_id = sest.servico_id                             "
                              + " where sest.estab_id = " + estab_id
                              + " and hser.status_servico = '" + status_servico + "'; ";

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
                        agendamentos.Add(new Model.Agendamento
                        {
                            Hora_servico_id = reader.IsDBNull(0) ? 0 : reader.GetInt32(0),
                            Cliente_id = reader.IsDBNull(1) ? 0 : reader.GetInt32(1),
                            Dia_inicio = reader.IsDBNull(2) ? DateTime.MinValue : reader.GetDateTime(2),
                            Dia_fim = reader.IsDBNull(3) ? DateTime.MinValue : reader.GetDateTime(3),
                            Hora_inicio = reader.IsDBNull(4) ? TimeSpan.MinValue : reader.GetTimeSpan(4),
                            Hora_fim = reader.IsDBNull(5) ? TimeSpan.MinValue : reader.GetTimeSpan(5),
                            Status_servico = reader.IsDBNull(6) ? "" : reader.GetString(6),
                            Servico_id = reader.IsDBNull(7) ? 0 : reader.GetInt32(7),
                            Preco_servico = reader.IsDBNull(8) ? 0 : reader.GetFloat(8),
                            Funcionario_id = reader.IsDBNull(9) ? 0 : reader.GetInt32(9),
                            Nome_servico = reader.IsDBNull(10) ? "" : reader.GetString(10),
                            Nome_funcionario = reader.IsDBNull(11) ? "" : reader.GetString(11),
                            Estab_id = reader.IsDBNull(12) ? 0 : reader.GetInt32(12)
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
            return JsonConvert.SerializeObject(agendamentos);
        }




        public static string listarAgendamentosCliente(int cliente_id)
        {
            // Limpa lista
            agendamentos.Clear();

            // Conecta no banco
            MySqlConnection conn = Database.connectDB();
            conn.Open();

            // Atribui a query
            string MySQLQuery = " select hser.Hora_servico_id, hser.Cliente_id, hser.Dia_inicio, hser.Dia_fim, hser.Hora_inicio, hser.Hora_fim, hser.Status_servico, hser.Servico_id, hser.Preco_servico, hser.Funcionario_id, serv.Nome_servico, func.Nome_funcionario, sest.Estab_id "
                              + " from t_hora_servico hser                                             "
                              + "   inner join t_servicos serv                                         "
                              + "     on hser.servico_id = serv.servico_id                             "
                              + "   left join t_funcionarios func                                     "
                              + "     on hser.funcionario_id = func.funcionario_id                     "
                              + "   inner join t_servico_estabelecimento sest                          "
                              + "     on hser.servico_id = sest.servico_id                             "
                              + " where hser.Cliente_id = " + cliente_id + "; ";

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
                        agendamentos.Add(new Model.Agendamento
                        {
                            Hora_servico_id = reader.IsDBNull(0) ? 0 : reader.GetInt32(0),
                            Cliente_id = reader.IsDBNull(1) ? 0 : reader.GetInt32(1),
                            Dia_inicio_exibicao = reader.IsDBNull(2) ? "" : reader.GetDateTime(2).ToString("dd/MM/yyyy"),
                            Dia_fim_exibicao = reader.IsDBNull(3) ? "" : reader.GetDateTime(3).ToString("dd/MM/yyyy"),
                            Hora_inicio = reader.IsDBNull(4) ? TimeSpan.MinValue : reader.GetTimeSpan(4),
                            Hora_fim = reader.IsDBNull(5) ? TimeSpan.MinValue : reader.GetTimeSpan(5),
                            Status_servico = reader.IsDBNull(6) ? "" : reader.GetString(6),
                            Servico_id = reader.IsDBNull(7) ? 0 : reader.GetInt32(7),
                            Preco_servico = reader.IsDBNull(8) ? 0 : reader.GetFloat(8),
                            Funcionario_id = reader.IsDBNull(9) ? 0 : reader.GetInt32(9),
                            Nome_servico = reader.IsDBNull(10) ? "" : reader.GetString(10),
                            Nome_funcionario = reader.IsDBNull(11) ? "" : reader.GetString(11),
                            Estab_id = reader.IsDBNull(12) ? 0 : reader.GetInt32(12)
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
            return JsonConvert.SerializeObject(agendamentos);
        }

    }
}