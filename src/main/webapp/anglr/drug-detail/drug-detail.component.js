'use strict';

// Register `phoneDetail` component, along with its associated controller and template
angular.
  module('drugDetail').
  component('drugDetail', {
    templateUrl: 'drug-detail/drug-detail.template.html',
    controller: ['$routeParams', 'DrugDetail','DrugPackages',
      function DrugDetailController($routeParams, DrugDetail,DrugPackages) {
    	self=this;
    	self.ndc = $routeParams.ndc;
        DrugDetail.get({ndc: self.ndc}, function(response) {
            self.drug = response;
        });
    	//this.drug.packages=DrugPackages.query();
      }
    ]
  });
