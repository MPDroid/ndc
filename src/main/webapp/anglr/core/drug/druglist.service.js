'use strict';

angular.
  module('core.drug').
  factory('DrugList', ['$resource',
    function($resource) {
      return $resource('../rs/drug/list/:drugName', {}, {
        query: {
          method: 'GET',
          params: {drugName: 'drugName'},
          isArray: true
        }
      });
    }
  ]);
