using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.Model
{
    public class Estabelecimento
    {

        private int estab_id;
        private String nome_estab;
        private String pais_estab;
        private String estado_estab;
        private String cidade_estab;
        private String bairro_estab;
        private String rua_estab;
        private String numero_estab;
        private String cep_estab;
        private String complemento_estab;
        private String telefone_estab;
        private String whatsapp_estab;
        private String facebook_estab;
        private String site_estab;

        public int Estab_id {
            get {
                return estab_id;
            }

            set {
                estab_id = value;
            }
        }

        public string Nome_estab {
            get {
                return nome_estab;
            }

            set {
                nome_estab = value;
            }
        }

        public string Pais_estab {
            get {
                return pais_estab;
            }

            set {
                pais_estab = value;
            }
        }

        public string Estado_estab {
            get {
                return estado_estab;
            }

            set {
                estado_estab = value;
            }
        }

        public string Cidade_estab {
            get {
                return cidade_estab;
            }

            set {
                cidade_estab = value;
            }
        }

        public string Bairro_estab {
            get {
                return bairro_estab;
            }

            set {
                bairro_estab = value;
            }
        }

        public string Rua_estab {
            get {
                return rua_estab;
            }

            set {
                rua_estab = value;
            }
        }

        public string Numero_estab {
            get {
                return numero_estab;
            }

            set {
                numero_estab = value;
            }
        }

        public string Cep_estab {
            get {
                return cep_estab;
            }

            set {
                cep_estab = value;
            }
        }

        public string Complemento_estab {
            get {
                return complemento_estab;
            }

            set {
                complemento_estab = value;
            }
        }

        public string Telefone_estab {
            get {
                return telefone_estab;
            }

            set {
                telefone_estab = value;
            }
        }

        public string Whatsapp_estab {
            get {
                return whatsapp_estab;
            }

            set {
                whatsapp_estab = value;
            }
        }

        public string Facebook_estab {
            get {
                return facebook_estab;
            }

            set {
                facebook_estab = value;
            }
        }

        public string Site_estab {
            get {
                return site_estab;
            }

            set {
                site_estab = value;
            }
        }

        //cadastrarEstab()
        //editarEstab()
    }
}