angular.module('starter.controllers', ['ngOpenFB'])
.controller('AppCtrl', function($scope, $ionicModal, $timeout, ngFB, $state, $stateParams, $ionicHistory) {

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal
  $scope.loginData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };
    
    // Faz login com fb
    $scope.fbLogin = function () {
    ngFB.login({scope: 'email, publish_actions'}).then(
        function (response) {
            if (response.status === 'connected') {
                console.log('Facebook login succeeded');
                $scope.closeLogin();
                $ionicHistory.nextViewOptions({
    disableBack: true
  });
                $state.go("app.home");
            } else {
                alert('Facebook login failed');
            }
        });
};

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);

    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeLogin();
    }, 1000);
  };
})

.controller('ProfileCtrl', function ($scope, ngFB, $ionicViewService, $ionicHistory) {
    $ionicViewService.clearHistory();
    $ionicHistory.nextViewOptions({
    disableBack: true
  });
    ngFB.api({
        path: '/me',
        params: {fields: 'id,name'}
    }).then(
        function (user) {
            $scope.user = user;
        },
        function (error) {
            alert('Facebook error: ' + error.error_description);
        });
})

.controller('ListagemEstabCtrl', function($scope, $http, $state, $stateParams) {
    $scope.estabelecimentos = [];
    
    $scope.cidade = $stateParams.estabCidade;
    $scope.visualizar = function(id) {
        $state.go('app.estabelecimento', {estabId: id})
        console.log(id);
    }
    
    $http.get('http://dppwear-io.umbler.net/listarEstabelecimentosCidade/' + $scope.cidade)
    .success(function (estabelecimentos) {
        $scope.estabelecimentos = estabelecimentos;
    })
    .error(function (erro) {
        console.log(erro);
    });
})

.controller('PesquisarEstabCtrl', function($scope, $http, $state) {
    $scope.cidade = '';
    $scope.pesquisar = function(cidade){
        $state.go('app.listagemEstabelecimentos', {estabCidade: cidade});
        $scope.cidade = '';
        console.log(cidade);
    }  
})

//Listagem de categorias utilizada para a pesquisa de categorias
.controller('ListagemCategoriasCtrl', function($scope, $http, $state, $stateParams) {
    //Controler que retorna a lista de categorias para a view
    $scope.categorias = [];
    
    $http.get('http://dppwear-io.umbler.net/listarCategorias')
    .success(function (categorias) {
        $scope.categorias = categorias;
    })
    .error(function (erro) {
        console.log(erro);
    });
    
    $scope.pesquisar = function(cidade, categoria) {
        $state.go('app.listagemServicosPorCategoria', {cidade: cidade, categoriaId: categoria})
    };
    
})

//Listagem de serviços filtrados por categoria e cidade
.controller('ServicosPorCategoriaCtrl', function($scope, $http, $state, $stateParams, filterFilter) {
    $scope.cidade = $stateParams.cidade;
    $scope.categoriaId = $stateParams.categoriaId;
    $scope.servicos = [];
    
    $http.get('http://dppwear-io.umbler.net/listarServicosPorCategorias/' + $scope.cidade)
    .success(function (servicos) {
        console.log($scope.categoriaId);
        $scope.servicos = filterFilter(servicos, {Categoria_id: $scope.categoriaId});
        console.log(servicos[1]);
    })
    .error(function (erro) {
        console.log(erro);
    })
            
})

.controller('ServicoCtrl', function($scope, $http, $state, $stateParams, ngFB) {
    ngFB.api({
        path: '/me',
        params: {fields: 'id,name'}
    }).then(
        function (user) {
            $scope.user = user;
        },
        function (error) {
            alert('Facebook error: ' + error.error_description);
        });
    
    $scope.servicoId = $stateParams.servicoId;
    $scope.estabId = $stateParams.estabId;
    $scope.servico = {};
    $scope.tempoMedio = 0;
    $scope.mensagem = "";
    $http.get('http://dppwear-io.umbler.net/listarDetalhesServico/' + $scope.servicoId)
    .success(function (servico) {
        $scope.servico = servico;
        var split1 = $scope.servico.Tempo_medio_servico.split('T');
        $scope.tempo_servico = split1[1];
        console.log(servico);
    })
    .error(function (erro) {
        console.log(erro)
    });
    
    $scope.agendar = function(datahora) {
        var horas = datahora.getHours();
        var minutos = datahora.getMinutes();
        
        var split1 = $scope.servico.Tempo_medio_servico.split('T');
        var split = split1[1].split(':');
        
        if (minutos + split[1] > 60) {
            minutos += parseInt(split[1]) - 60;
            horas += 1;
        } else {
            minutos += parseInt(split[1]);
        }
        
        horas += parseInt(split[0]);
        datahorafim = new Date(datahora);

        datahorafim.setHours(horas);
        datahorafim.setMinutes(minutos);
        
        //var idcl = parseInt($scope.user.id/10000000000);
        var idcl = 269782328;
        
        var agendamento = {Cliente_id: idcl, Dia_inicio: datahora, Hora_inicio: datahora, Dia_fim: datahorafim, Hora_fim: datahorafim, Status_servico: 'A', Servico_id: $scope.servicoId, Preco_servico: $scope.servico.Preco_servico, Funcionario_id: 1};
        console.log(agendamento); 
        
        $http.post('http://dppwear-io.umbler.net/agendar', agendamento)
        .success(function (result) {
            $scope.mensagem = result;
            console.log(result);
        })
        .error(function (erro) {
            $scope.mensagem = erro;
            console.log(erro);
        })
        
    };
    
})



.controller('ServicosAgendadosCtrl', function($scope, $http, $state, $stateParams, ngFB) {
    ngFB.api({
        path: '/me',
        params: {fields: 'id,name'}
    }).then(
        function (user) {
            $scope.user = user;
            var nome = user.name.split(' ');
            $scope.primeiroNome = nome[0];
            $scope.agendamentos = [];
            
            //var idcl = parseInt($scope.user.id/10000000000);
            var idcl = 269782328;
            $http.get('http://dppwear-io.umbler.net/listarAgendamentosCliente/' + idcl)
            .success(function (agendamentos) {
                $scope.agendamentos = agendamentos;
                console.log(agendamentos);
            })
            .error(function (erro) {
                console.log(erro)
            });
        },
        function (error) {
            alert('Facebook error: ' + error.error_description);
        });
})




.controller('EstabelecimentoCtrl', function($scope, $http, $state, $stateParams) {
    $scope.estabelecimento = {};
    $scope.servicos = [];
    $scope.estabId = $stateParams.estabId;
    
    $http.get('http://dppwear-io.umbler.net/listarEstabelecimentoId/' + $scope.estabId)
    .success(function (estabelecimento) {
        $scope.estabelecimento = estabelecimento;
        console.log(estabelecimento);
    })
    .error(function (erro) {
        console.log(erro)
    });
        
    $http.get('http://dppwear-io.umbler.net/listarEstabelecimentoCompleto/' + $scope.estabId)
    .success(function (servicos) {
        $scope.servicos = servicos;
    })
    .error(function (erro) {
        console.log(erro);
    });
    
    $scope.detalhes = function(servico, estab) {
        console.log("Detalhes pressionado" + servico + " estab " + estab);
        $state.go('app.servico', {servicoId: servico, estabId: estab});
    };
    
    
});
