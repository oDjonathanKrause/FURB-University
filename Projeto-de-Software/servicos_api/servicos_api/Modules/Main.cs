using Nancy;
using servicos_api.Model;
using Nancy.ModelBinding;
using Nancy.Extensions;
using System;


/*
	- Os módulos definem o comportamento das aplicações Nancy;
	- O Nancy escaneia todos os módulos e cacheia depois, não é custoso e os módulos são globais;
	- Utiliza HTTP, suporta DELETE, GET, HEAD, OPTIONS, POST, PUT e PATCH;
    - O DynamicDictionary permite acessar os valores como index ou propriedade (ver em ProductsModule)
*/

namespace servicos_api.Modules
{
    public class Main : NancyModule
    {
        public Main()
        {
            Get["/"] = parameters => "API no ar!";

            //Cadastra estabelecimento
            Post["/cadastrarEstab"] = _ =>
            {
                var request = this.Bind<Estabelecimento>();
                CRUD.EstabelecimentosCRUD.cadastrarEstabelecimento(request);
                return request;
            };

            //Cadastra o servico
            Post["/cadastrarServico"] = _ =>
            {
                var request = this.Bind<Servico>();
                CRUD.ServicosCRUD.cadastrarServico(request);
                return request;

            };

            //Agendar o servico
            Post["/agendar"] = _ =>
            {
                var request = this.Bind<HoraServico>();
                CRUD.AgendamentoCRUD.agendar(request);
                return "Agendamento feito com sucesso";

            };
            
            //Conclui serviço
            Post["/concluirServico"] = _ =>
            {
                var request = Context.Request.Body.AsString();
                int.TryParse(request, out int hora_servico_id);

                if (CRUD.AgendamentoCRUD.concluirServico(hora_servico_id))
                    return true;
                else
                    return false;
            };

            //Cancelar serviço
            Post["/cancelarServico"] = _ =>
            {
                var request = Context.Request.Body.AsString();
                int.TryParse(request, out int hora_servico_id);

                if (CRUD.AgendamentoCRUD.cancelarServico(hora_servico_id))
                    return true;
                else
                    return false;
            };
                 

            // Lista os detalhes de um servico
            Get["listarDetalhesServico/{servico_id}"] = parameters => DAO.ServicosDAO.listarDetalhesServico(parameters.servico_id);
            
            // Lista os estabelecimentos da determinada cidade
            Get["listarEstabelecimentosCidade/{cidade}"] = parameters => DAO.EstabelecimentosDAO.listarEstabelecimentosPorCidade(parameters.cidade);

            // Lista servicos do estabelecimento
            Get["listarEstabelecimentoCompleto/{estab_id}"] = parameters => DAO.ServicosDAO.listarServicosByEstab(parameters.estab_id);

            // Nome do estabelecimento pelo ID
            Get["listarEstabelecimentoId/{Estab_id}"] = parameters => DAO.EstabelecimentosDAO.listarEstabelecimentoId(parameters.Estab_id);

            // Teste de conexão com o banco de dados
            Get["/testDatabase"] = parameters => DAO.Database.testDatabase();

            // Lista estabelecimentos
            Get["/listarEstabelecimentos"] = parameters => DAO.EstabelecimentosDAO.listarEstabelecimentos();

            // Lista Categorias
            Get["/listarCategorias"] = parameters => DAO.CategoriasDAO.listarCategorias();

            // Lista serviços disponíveis
            Get["/listarServicos"] = parameters => DAO.ServicosDAO.listarServicos();

            // Lista serviços disponíveis por categoria
            Get["/listarServicosPorCategorias/{cidade}"] = parameters => DAO.ServicosDAO.listarServicosPorCategorias(parameters.cidade);

            // Lista serviços disponíveis ordenado por preço
            Get["/listarServicosOrderPreco"] = parameters => DAO.ServicosDAO.listarServicosOrderPreco();

            // Lista serviços disponíveis ordenado por preço com filtro de categoria
            Get["/listarServicosComCategoriaOrderPreco/{categoria_id}"] = parameters => DAO.ServicosDAO.listarServicosComCategoriaOrderPreco(parameters.categoria_id);

            // Lista serviços disponíveis por estabelecimento ordenado por preço 
            Get["/listarServicosByEstab/{estab_id}"] = parameters => DAO.ServicosDAO.listarServicosByEstab(parameters.estab_id);

            // Lista os serviços agenfafos de acordo com o estab e status informados
            Get["listarAgendamentos/{estab_id}/{status_servico}"] = parameters => DAO.AgendamentosDAO.listarAgendamentos(parameters.estab_id, parameters.status_servico);

            // Lista os agendamentos do cliente
            Get["listarAgendamentosCliente/{cliente_Id}"] = parameters => DAO.AgendamentosDAO.listarAgendamentosCliente(parameters.Cliente_id);
        }
    }
}
