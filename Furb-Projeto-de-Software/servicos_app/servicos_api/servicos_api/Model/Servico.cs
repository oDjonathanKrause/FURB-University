using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.Model
{
    public class Servico
    {
        private int categoria_id;
        private int servico_id;
        private String nome_servico;
        private String desc_servico;
        private DateTime hora_disp_inicio;
        private DateTime hora_disp_fim;
        private float preco_servico;
        private float preco_hora_servico;
        private DateTime tempo_medio_servico;
        private char status_servico;
        private float taxa_cancelamento;
        private char cancelavel_flag;
        private int estab_id;
        private String nome_categoria; // Atributo não é do modelo de serviço, usado para listar serviços por categoria
        private String cidade; // Atributo não é do modelo de serviço, usado para listar serviços por categoria
        private TimeSpan string_time;

        public TimeSpan String_time { get; set; }

        public int Categoria_id {
            get {
                return categoria_id;
            }

            set {
                categoria_id = value;
            }
        }

        public int Servico_id {
            get {
                return servico_id;
            }

            set {
                servico_id = value;
            }
        }

        public string Nome_servico {
            get {
                return nome_servico;
            }

            set {
                nome_servico = value;
            }
        }

        public string Desc_servico {
            get {
                return desc_servico;
            }

            set {
                desc_servico = value;
            }
        }

        public DateTime Hora_disp_inicio {
            get {
                return hora_disp_inicio;
            }

            set {
                hora_disp_inicio = value;
            }
        }

        public DateTime Hora_disp_fim {
            get {
                return hora_disp_fim;
            }

            set {
                hora_disp_fim = value;
            }
        }

        public float Preco_servico {
            get {
                return preco_servico;
            }

            set {
                preco_servico = value;
            }
        }

        public float Preco_hora_servico {
            get {
                return preco_hora_servico;
            }

            set {
                preco_hora_servico = value;
            }
        }

        public DateTime Tempo_medio_servico {
            get {
                return tempo_medio_servico;
            }

            set {
                tempo_medio_servico = value;
            }
        }

        public char Status_servico {
            get {
                return status_servico;
            }

            set {
                status_servico = value;
            }
        }

        public float Taxa_cancelamento {
            get {
                return taxa_cancelamento;
            }

            set {
                taxa_cancelamento = value;
            }
        }

        public char Cancelavel_flag {
            get {
                return cancelavel_flag;
            }

            set {
                cancelavel_flag = value;
            }
        }

        public int Estab_id {
            get {
                return estab_id;
            }

            set {
                estab_id = value;
            }
        }

        public string Nome_categoria {
            get {
                return nome_categoria;
            }

            set {
                nome_categoria = value;
            }
        }

        public string Cidade {
            get {
                return cidade;
            }

            set {
                cidade = value;
            }
        }

        // Gets e sets



        /// <summary> Método que corrige o formato de data informado pelo usuáio para ser aceito no banco de dados </summary>
        /// <returns> Retorna a hora no formato hh:mm:ss </returns>
        public String getHoraFormatada(DateTime hora)
        {
            return Convert.ToDateTime(hora).ToString("HH:mm:ss");
        }
      
        //Retorna a hora inicial formatada para HH:mm:ss
        public String getHoraInicioSTR()
        {
            return Hora_disp_inicio.ToString("HH:mm:ss");
        }

      
        
    }
}