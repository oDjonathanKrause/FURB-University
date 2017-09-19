using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.Model
{
    public class Agendamento
    {
        private int hora_servico_id;
        private int cliente_id;
        private int servico_id;
        private int funcionario_id;
        private int estab_id;
        private float preco_servico;
        private DateTime dia_inicio;
        private DateTime dia_fim;
        private TimeSpan hora_inicio;
        private TimeSpan hora_fim;
        private String nome_servico;
        private String nome_funcionario;
        private String status_servico;
        public string Dia_inicio_exibicao { get; set; }
        public string Dia_fim_exibicao { get; set; }

        public int Hora_servico_id {
            get {
                return hora_servico_id;
            }

            set {
                hora_servico_id = value;
            }
        }

        public int Cliente_id {
            get {
                return cliente_id;
            }

            set {
                cliente_id = value;
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

        public int Funcionario_id {
            get {
                return funcionario_id;
            }

            set {
                funcionario_id = value;
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

        public float Preco_servico {
            get {
                return preco_servico;
            }

            set {
                preco_servico = value;
            }
        }

        public DateTime Dia_inicio {
            get {
                return dia_inicio;
            }

            set {
                dia_inicio = value;
            }
        }

        public DateTime Dia_fim {
            get {
                return dia_fim;
            }

            set {
                dia_fim = value;
            }
        }

        public TimeSpan Hora_inicio {
            get {
                return hora_inicio;
            }

            set {
                hora_inicio = value;
            }
        }

        public TimeSpan Hora_fim {
            get {
                return hora_fim;
            }

            set {
                hora_fim = value;
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

        public string Nome_funcionario {
            get {
                return nome_funcionario;
            }

            set {
                nome_funcionario = value;
            }
        }

        public string Status_servico {
            get {
                return status_servico;
            }

            set {
                status_servico = value;
            }
        }

        // Gets e sets



    }
}


