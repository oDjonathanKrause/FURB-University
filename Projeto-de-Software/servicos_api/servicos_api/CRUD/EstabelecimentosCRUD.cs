using MySql.Data.MySqlClient;
using servicos_api.DAO;
using servicos_api.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.CRUD
{
    public class EstabelecimentosCRUD
    {
        public static Boolean cadastrarEstabelecimento(Estabelecimento estab)
        {
            MySqlConnection conn = Database.connectDB();
            conn.Open();
            
            //Insere o estabelecimento chamado a usp_cadastrar_estab
            MySqlCommand command = new MySqlCommand("call usp_cadastrar_estab('" +
                estab.Nome_estab + "','" +
                estab.Pais_estab + "','" +
                estab.Estado_estab + "','" +
                estab.Cidade_estab + "','" +
                estab.Bairro_estab + "','" +
                estab.Rua_estab + "','" +
                estab.Numero_estab + "','" +
                estab.Cep_estab + "','" +
                estab.Complemento_estab + "','" + 
                estab.Telefone_estab + "','" +
                estab.Whatsapp_estab + "','" + 
                estab.Facebook_estab + "','" + 
                estab.Site_estab + "','" + 
                estab.Nome_estab + "')", conn);
      
            command.ExecuteNonQuery();
            conn.Close();
            return true;

        }
    }
}