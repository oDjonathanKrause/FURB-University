using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace servicos_api.Model
{
    public class Categoria
    {
        private int categoria_id;
        private String nome_categoria;
        private String desc_categoria;

        //Gets e sets
        public int Categoria_id {
            get { return categoria_id; }
            set { categoria_id = value; }
        }

        public String Nome_categoria {
            get { return nome_categoria; }
            set { nome_categoria = value; }
        }

        public String Desc_categoria {
            get { return desc_categoria; }
            set { desc_categoria = value; }
        }

    }
}