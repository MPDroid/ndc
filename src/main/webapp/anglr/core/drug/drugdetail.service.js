'use strict';

angular.
  module('core.drug').
  factory('DrugDetail', ['$resource',
    function($resource) {
      return $resource('../rs/drug/detail/:ndc', {}, {
        query: {
          method: 'GET',
          params: {ndc: 'ndc'},
          isArray: true
        }
      });
    }
  ]);
