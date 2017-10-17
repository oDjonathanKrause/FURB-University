// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers', 'ngOpenFB'])

.run(function($ionicPlatform, ngFB) {
  $ionicPlatform.ready(function() {
      ngFB.init({appId: '1302933593158032'});

    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

    .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'AppCtrl'
  })
  
  .state('app.profile', {
    url: "/profile",
    views: {
        'menuContent': {
            templateUrl: "templates/profile.html",
            controller: "ProfileCtrl"
        }
    }
})
  
    .state('app.login', {
    url: "/login",
    views: {
        'menuContent': {
            templateUrl: "templates/login.html"
           // controller: "ProfileCtrl"
        }
    }
})
  
    .state('app.home', {
      url: '/home',
      views: {
        'menuContent': {
          templateUrl: 'templates/home.html',
            controller: 'ProfileCtrl'
        }
      }
    })
  
  .state('app.estabelecimento', {
    url: '/estabelecimento/:estabId',
    views: {
      'menuContent': {
        templateUrl: 'templates/estabelecimento.html',
        controller: 'EstabelecimentoCtrl'
      }
    }
  })
  
    .state('app.servico', {
    url: '/estabelecimento/:estabId/:servicoId',
    views: {
      'menuContent': {
        templateUrl: 'templates/servico.html',
        controller: 'ServicoCtrl'
      }
    }
  })
  
  .state('app.listagemEstabelecimentos', {
    url: '/listagemEstabelecimentos/:estabCidade',
    views: {
      'menuContent': {
        templateUrl: 'templates/listagemEstabelecimentos.html',
        controller: 'ListagemEstabCtrl'
      }
    }
  })
  
    .state('app.servicosAgendados', {
    url: '/servicosAgendados',
    views: {
      'menuContent': {
        templateUrl: 'templates/servicosAgendados.html',
        controller: 'ServicosAgendadosCtrl'
      }
    }
  })
  
  
   .state('app.pesquisarEstabelecimentos', {
    url: '/pesquisarEstabelecimentos',
    views: {
      'menuContent': {
        templateUrl: 'templates/pesquisarEstabelecimentos.html',
        controller: 'PesquisarEstabCtrl'
      }
    }
  })

  .state('app.pesquisarServicosPorCategorias', {
    url: '/pesquisarServicosPorCategorias',
    views: {
      'menuContent': {
        templateUrl: 'templates/pesquisarServicosPorCategorias.html',
        controller: 'ListagemCategoriasCtrl'
      }
    }
  })  

    
  .state('app.listagemServicosPorCategoria', {
    url: '/listagemServicosPorCategoria/:cidade/:categoriaId',
    views: {
      'menuContent': {
        templateUrl: 'templates/listagemServicosPorCategoria.html',
        controller: 'ServicosPorCategoriaCtrl'
      }
    }
  });
    
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/login');
});
