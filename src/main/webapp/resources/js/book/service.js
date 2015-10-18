/**
 * @author Tunahan.Pehlivan
 */

var app = angular.module('service', []);

	app.service('bookService', ['$http', '$q', function($http, $q){
		return {
			findAll: function() {
				var deferred = $q.defer();
				$http
					.get('core/findAll')
					.then(function successCallBack(response) {
						prepareResponse(response, deferred);
					});
				return deferred.promise;
			},
			
			remove: function(bookId) {
				var deferred = $q.defer();
				$http
					['delete']('core/remove/' + bookId)
					.then(function successCallBack(response) {
						prepareResponse(response, deferred);
					});
				return deferred.promise;
			},
			
			save: function(book) {
				var deferred = $q.defer();
				$http
					.post('core/save', book)
					.then(function successCallBack(response) {
						prepareResponse(response, deferred);
					});
				return deferred.promise;
			},
			
			update: function(book) {
				var deferred = $q.defer();
				$http
					.post('core/update', book)
					.then(function successCallBack(response) {
						prepareResponse(response, deferred);
					});
				return deferred.promise;
			}
		}
		
		function prepareResponse(response, deferred) {
			var data = response.data;
			if(data.status == "SUCCESS") {
				deferred.resolve(data);
			} else {
				deferred.reject(data);
			}
		}
	}]);