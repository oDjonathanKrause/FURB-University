angular.module('yapp')
  .controller('CategoriaCtrl', function($scope, $http) {
    // Controler das categorias. Este controler lista todas as categorias disponiveis e retorna para a view
    $scope.categorias = [];
    
    $http.get('http://dppwear-io.umbler.net/listarCategorias')
    .success(function (categorias) {
        $scope.categorias = categorias;
    })
    .error(function (erro) {
        console.log(erro);
    });
    
});