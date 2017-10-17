using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.Model
{
    public class HoraServico
    {
        public int Hora_servico_id { get; set; }
        public int Cliente_id { get; set; }
        public DateTime Dia_inicio { get; set; }
        public DateTime Hora_inicio { get; set; }
        public DateTime Dia_fim { get; set; }
        public DateTime Hora_fim { get; set; }
        public char Status_servico { get; set; }
        public int Servico_id { get; set; }
        public float Preco_servico { get; set; }
        public int Funcionario_id { get; set; }


        //cadastrarHora()
        //editarHora()
        //gets sets
    }
}