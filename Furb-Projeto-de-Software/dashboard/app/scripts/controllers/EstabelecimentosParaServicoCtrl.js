angular.module('yapp')
  .controller('EstabelecimentosParaServicoCtrl', function($scope, $http) {
    //Controler que retorna a lista de estabelecimentos para a view
    $scope.estabelecimentos = [];
    
    $http.get('http://dppwear-io.umbler.net/listarEstabelecimentos')
    .success(function (estabelecimentos) {
        $scope.estabelecimentos = estabelecimentos;
    })
    .error(function (erro) {
        console.log(erro);
    });
    
});