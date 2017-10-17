'use strict';

/**
 * @ngdoc function
 * @name CadastrarServicoPageCtrl.js
 * @description Controler da página cadastrar_servico.html
 */


angular.module('yapp')
  .controller('CadastrarServicoPageCtrl', function($scope, $http) {
    $scope.servico = {};
    
    // Add segundos nos parametros de hora para ficar no formato correto (Time)
    $scope.servico.Hora_disp_fim = $scope.servico.Hora_disp_fim + ":00";
    $scope.servico.Hora_disp_inicio = $scope.servico.Hora_disp_inicio + ":00";
    $scope.servico.Tempo_medio_servico = $scope.servico.Tempo_medio_servico + ":00";

    $scope.mensagem = '';
    $scope.cadastrarServico = function() {
        $http.post('http://dppwear-io.umbler.net/cadastrarServico', $scope.servico)
        .success(function ()  {
            $scope.servico = {};
            $scope.mensagem = 'Servico cadastrado';
        })
        .error(function (erro) {
            $scope.mensagem = 'Nao foi possivel cadastrar';
        })
    };
  });