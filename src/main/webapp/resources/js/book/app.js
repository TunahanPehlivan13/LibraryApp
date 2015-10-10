/**
 * @author Tunahan.Pehlivan
 */

	angular.module('sharedServices', [])
		.config(function ($httpProvider) {
			$httpProvider.responseInterceptors.push('httpInterceptor');
			var spinnerFunction = function (data, headersGetter) {
				$('#ajaxLoader').show();
				return data;
			};
			$httpProvider.defaults.transformRequest.push(spinnerFunction);
		});