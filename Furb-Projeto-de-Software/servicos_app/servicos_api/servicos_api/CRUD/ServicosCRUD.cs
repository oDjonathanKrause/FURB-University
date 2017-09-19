using MySql.Data.MySqlClient;
using servicos_api.DAO;
using servicos_api.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.CRUD
{
    public class ServicosCRUD
    {
        public static Boolean cadastrarServico(Servico servico)
        {
            
            /*Convert.ToDateTime(servico.Hora_disp_inicio).ToString("HH:mm:ss");
            Convert.ToDateTime(servico.Hora_disp_fim).ToString("HH:mm:ss");
            Convert.ToDateTime(servico.Tempo_medio_servico).ToString("HH:mm:ss");*/


            MySqlConnection conn = Database.connectDB();
            conn.Open();

            //Insere o servico chamando a usp_cadastrar_servico_estab
            MySqlCommand command = new MySqlCommand("call usp_cadastrar_servico_estab('" +
                servico.Estab_id + "','" +
                servico.Categoria_id + "','" +
                servico.Nome_servico + "','" +
                servico.Desc_servico + "','" +
                servico.Preco_servico + "','" +
                servico.Preco_hora_servico + "','" +
                servico.getHoraFormatada(servico.Tempo_medio_servico) + "','" + 
                servico.Status_servico + "','" +
                1 + "','" +  // Multiplos atendimentos simultaneos, 1 por default para versão 1
                servico.Taxa_cancelamento + "','" +
                servico.Cancelavel_flag + "','" +
                servico.getHoraFormatada(servico.Hora_disp_inicio) + "','" +
                servico.getHoraFormatada(servico.Hora_disp_fim) + "')", conn); 


            command.ExecuteNonQuery();
            conn.Close();
            return true;

        }
    }
}