'use strict';

angular.
  module('core.drug').
  factory('DrugPackages', ['$resource',
    function($resource) {
      return $resource('../rs/drug/packages/:ndc', {}, {
        query: {
          method: 'GET',
          params: {ndc: 'pkgs'},
          isArray: true
        }
      });
    }
  ]);
