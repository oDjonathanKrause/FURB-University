using MySql.Data.MySqlClient;
using servicos_api.DAO;
using servicos_api.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.CRUD
{
    public class AgendamentoCRUD
    {
        public static Boolean agendar(HoraServico horaServico)
        {

            /*Convert.ToDateTime(servico.Hora_disp_inicio).ToString("HH:mm:ss");
            Convert.ToDateTime(servico.Hora_disp_fim).ToString("HH:mm:ss");
            Convert.ToDateTime(servico.Tempo_medio_servico).ToString("HH:mm:ss");*/

            MySqlConnection conn = Database.connectDB();
            conn.Open();

            MySqlCommand command = new MySqlCommand("insert into t_hora_servico ("
               + "cliente_id,"
               + "dia_inicio,"
               + "hora_inicio,"
               + "dia_fim,"
               + "hora_fim,"
               + "status_servico,"
               + "servico_id,"
               + "preco_servico)"
               + "values ("
               + "'" + horaServico.Cliente_id + "'"
               + ", '" + horaServico.Dia_inicio.ToString("yyyy-MM-dd") + "'"
               + ", '" + horaServico.Hora_inicio.ToString("HH:mm:ss") + "'"
               + ", '" + horaServico.Dia_fim.ToString("yyyy-MM-dd") + "'"
               + ", '" + horaServico.Hora_fim.ToString("HH:mm:ss") + "'"
               + ", '" + 'A' + "'"
               + ", '" + horaServico.Servico_id + "'"
               + ", '" + horaServico.Preco_servico + "')", conn);
               


            command.ExecuteNonQuery();

            conn.Close();
            return true;
        }

        public static Boolean concluirServico(int hora_servico_id)
        {
            try
            {
                // Conecta no banco
                MySqlConnection conn = Database.connectDB();
                conn.Open();

                MySqlCommand command = new MySqlCommand(" update t_hora_servico " +
                                                        " set status_servico = 'C'" +
                                                        " where hora_servico_id = " + hora_servico_id + " ; ", conn);

                command.ExecuteNonQuery();
                conn.Close();
                return true;
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return false;
            }
        }

        public static Boolean cancelarServico(int hora_servico_id)
        {
            try
            {
                // Conecta no banco
                MySqlConnection conn = Database.connectDB();
                conn.Open();

                MySqlCommand command = new MySqlCommand(" update t_hora_servico " +
                                                        " set status_servico = 'X'" +
                                                        " where hora_servico_id = " + hora_servico_id + " ; ", conn);

                command.ExecuteNonQuery();
                conn.Close();
                return true;
            }
            catch (MySqlException ex)
            {
                string MySQLresult = "Error:" + ex;
                return false;
            }
        }


    }
}