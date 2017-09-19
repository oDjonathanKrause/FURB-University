'use strict';

/**
 * @ngdoc function
 * @name estabs_dropdown.js
 * @description
 * # Retorna os dados do serviço para popular dropdowns
 */

angular.module('yapp')
  .controller('CadastrarEstabPageCtrl', function($scope, $http) {
    $scope.estabelecimento = {};
    $scope.mensagem = '';
    $scope.cadastrarEstabelecimento = function() {
        $http.post('http://dppwear-io.umbler.net/cadastrarEstab', $scope.estabelecimento)
        .success(function ()  {
            $scope.estabelecimento = {};
            $scope.mensagem = 'Estabelecimento cadastrado';
        })
        .error(function (erro) {
            $scope.mensagem = 'Nao foi possivel cadastrar';
        })
    };
  });