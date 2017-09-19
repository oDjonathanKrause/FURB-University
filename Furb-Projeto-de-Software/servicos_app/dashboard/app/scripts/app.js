'use strict';

/**
 * @ngdoc overview
 * @name yapp
 * @description
 * # yapp
 *
 * Main module of the application.
 */
angular
  .module('yapp', [
    'ui.router',
    'ngAnimate'
  ])
  .config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.when('/dashboard', '/dashboard/principal');
    $urlRouterProvider.otherwise('/login');

    $stateProvider
      .state('base', {
        abstract: true,
        url: '',
        templateUrl: 'views/base.html'
      })
        .state('login', {
          url: '/login',
          parent: 'base',
          templateUrl: 'views/login.html',
          controller: 'LoginCtrl'
        })
        .state('dashboard', {
          url: '/dashboard',
          parent: 'base',
          templateUrl: 'views/dashboard.html',
          controller: 'DashboardCtrl'
        })
          .state('overview', {
            url: '/overview',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/overview.html'
          })
          .state('reports', {
            url: '/reports',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/reports.html'
          })
          // My changes
          .state('principal', {
            url: '/principal',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/principal.html'
          })
          .state('servicos_por_estab', {
            url: '/servicos_por_estab',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/servicos_por_estab.html',
            controller: 'ServicosPorEstabPageCtrl'
          })
          .state('horarios_agendados', {
            url: '/horarios_agendados',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/horarios_agendados.html',
            controller: 'ServicosAgendadosPageCtrl'
          })
          .state('cadastrar_servico', {
            url: '/cadastrar_servico',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/cadastrar_servico.html',
            controller: 'CadastrarServicoPageCtrl'
          })
          .state('cadastrar_estab', {
            url: '/cadastrar_estab',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/cadastrar_estab.html',
            controller: 'CadastrarEstabPageCtrl'
          })
          .state('404', {
            url: '/404',
            parent: 'base',
            templateUrl: 'views/404.html'
          });

  });
