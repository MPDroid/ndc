'use strict';

// Register `drugList` component, along with its associated controller and
// template
angular.module('drugList').component('drugList', {
	templateUrl : 'drug-list/drug-list.template.html',
	controller : [ 'DrugList', function DrugListController(DrugList) {
		var self = this;
		var response;
		self.pageSize = 5;
		self.pageSetSize = 10;
		self.totalSize = 0;
		self.firstPageInSet=1;
		self.lastPageInSet=self.firstPageInSet +self.pageSetSize;
		
		self.range = function(min, max, step) {
		    step = step || 1;
		    var input = [];
		    for (var i = min; i <= max; i += step) {
		        input.push(i);
		    }
		    return input;
		};
		
		self.initializePagination = function(firstPageInSet) {
			self.firstPageInSet = firstPageInSet;
			self.pageNum = 1;
			self.lastPageInSet = self.firstPageInSet + self.pageSetSize - 1;
		};

		self.initializePagination(1);

		self.searchClicked = function() {
			if (self.drugName != null && self.drugName != "") {
				DrugList.query({
					drugName : self.drugName,
					pageNum : self.pageNum,
					pageSize : self.pageSize,
					totalSize : self.totalSize
				}, function(response, headers) {
					var totalSizeStr = headers('X-total-count');
					if (totalSizeStr != null)
						self.totalSize = totalSizeStr;

					if (self.totalSize < self.lastPageInSet)
						self.lastPageInSet = self.totalSize;
					self.drugs = response;
				});
			}
		};

		self.drugNameChanged =  function() {
			self.initializePagination(1);
		};

		self.pageSet = function(pageSetNumber) {
			self.initializePagination(pageSetNumber);
			
			self.pageClicked(self.firstPageInSet);
			
		};

	    self.pageClicked = function(pageNum) {
			self.pageNum = pageNum;
			self.searchClicked();
		};
	} ]
});
