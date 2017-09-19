
/**
 * Service que lista os estabelecimentos
 */

angular.module('yapp')
.service('ListEstabelecimentosService', function($http){

    var url = 'http://dppwear-io.umbler.net/listarEstabelecimentos';

    return{
      listarEstabelecimentos : function (){
          return $http.get(url).then(function(response){
              return response.data;
            });
      }
    }
});

/**
 * Service que lista os serviços agendados de acordo com o estab_id e status_servico
 */

angular.module('yapp')
.service('ListAgendamentosService', function($http){
    return{
        listarAgendamentos : function (estab_id, status_servico){
            console.log("estab_id: " + estab_id);
            console.log("status_servico: " + status_servico);
            return $http.get('http://dppwear-io.umbler.net/listarAgendamentos/' + estab_id + '/' + status_servico).then(function(response){
                return response.data;
            });
        }
}});

/**
 * Service que lista os serviços por estabelecimento
 */
angular.module('yapp')
    .service('ListServicoService', function($http){
      return{
        listarServicos : function (id){
            console.log("serv: " + id);
            return $http.get('http://dppwear-io.umbler.net/listarServicosByEstab/' + id).then(function(response){
                return response.data;
            });
        }
    }});