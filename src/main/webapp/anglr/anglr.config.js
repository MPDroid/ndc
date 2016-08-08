'use strict';

angular.
  module('drugApp').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/anglr', {
          template: '<drug-list></drug-list>'
        }).
        when('/anglr/detail/:ndc', {
          template: '<drug-detail></drug-detail>'
        }).
        otherwise('/anglr');
    }
  ]);
