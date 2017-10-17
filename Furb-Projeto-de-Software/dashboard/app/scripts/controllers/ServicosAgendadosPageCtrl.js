'use strict';

/**
 * @ngdoc function
 * @name ServicosAgendadosPageCtrl.js
 * @description
 * #Controler da página horarios_agendados.html
 */


angular.module('yapp')
  .controller('ServicosAgendadosPageCtrl', function($http, $scope, $state, ListAgendamentosService, ListEstabelecimentosService) {

    /**
     * Controller que lista os estabelecimentos
     */
    ListEstabelecimentosService.listarEstabelecimentos().then(function(estabs){
        $scope.listaDeEstabelecimentos = estabs;
    });

    /**
     * Quais status são possíveis selecionar
     * A	Agendado
     * C	Cancelado
     * P	Pendente
     */
    $scope.listaDeStatus = ["Agendado", "Concluído", "Pendente", "Cancelado"];

    /**
     * Controller do dropdown que escolhe o estabelecimento
     */
    $scope.passEstab = function(estab_id){
        $scope.estab_id = estab_id;
    } 

    /**
     * Controller do dropdown que escolhe o status do serviço agendado
     */
      $scope.passdata = function(status_servico){
       // Verifica qual o status informado e seta como o char correspondente 
       if(status_servico == "Agendado")
            status_servico = "A";
        else if(status_servico == "Concluído")
            status_servico = "C";
        else if(status_servico == "Pendente")
            status_servico = "P";
        else if(status_servico == "Cancelado")
            status_servico = "X";    

        // Chama o métodod listarAgendamentos do service     
        ListAgendamentosService.listarAgendamentos($scope.estab_id, status_servico).then(function(dados){
          $scope.listaDeAgendamentos = dados;
        });
      }

      /*
       * Concluir serviço 
       */
        $scope.concluirServico = function(hora_servico_id) {
            console.log("Concluiu " + hora_servico_id);
            $scope.mensagem = '';

            $http.post('http://dppwear-io.umbler.net/concluirServico', hora_servico_id)
            .success(function ()  {
                $scope.mensagem = 'Servico concluido';
            })
            .error(function (erro) {
                $scope.mensagem = 'Nao foi possivel concluir o serviço';
            })  
        };

       /*
        * Cancela serviço 
        */
        $scope.cancelarServico = function(hora_servico_id) {
            console.log("Cancelou " + hora_servico_id);
            $scope.mensagem = '';

            $http.post('http://dppwear-io.umbler.net/cancelarServico', hora_servico_id)
            .success(function ()  {
                $scope.mensagem = 'Servico concluido';
            })
            .error(function (erro) {
                $scope.mensagem = 'Nao foi possivel concluir o serviço';
            })  
        };

  });