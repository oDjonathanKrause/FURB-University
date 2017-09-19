'use strict';

/**
 * @ngdoc function
 * @name ServicosPorEstabPageCtrl.js
 * @description
 * #Controler da página estabelecimentos.html
 */


angular.module('yapp')
  .controller('ServicosPorEstabPageCtrl', function($scope, $state, ListServicoService, ListEstabelecimentosService) {

    /**
     * Controller do dropdown que escolhe o estabelecimento
     */
      $scope.passdata = function(id){
        console.log("ctrl: " + id);

        ListServicoService.listarServicos(id).then(function(dados){
          $scope.listaDeServicos = dados;

          console.log($scope.listaDeServicos);
        });
      } 

    /**
     * Controller que lista os estabelecimentos
     */
    ListEstabelecimentosService.listarEstabelecimentos().then(function(estabs){
        $scope.listaDeEstabelecimentos = estabs;
    });


  });