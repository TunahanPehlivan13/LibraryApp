/**
 * @author Tunahan.Pehlivan
 */

	app.factory('generalUtility', function() {
	    return {
	        removeItemById: function(items, item) {
	        	for(var i = items.length - 1; i >= 0; i--) {
	        	    if(items[i].id === item.id) {
	        	    	items.splice(i, 1);
	        	    }
	        	}
	        },
	        
	        updateItemById: function(items, item) {
	        	for(var i = items.length - 1; i >= 0; i--) {
	        	    if(items[i].id === item.id) {
	        	    	items[i] = item;
	        	    }
	        	}
	        }
	    };
	})
	.factory('httpInterceptor', function ($q, $window) {
		return function (promise) {
			return promise.then(function (response) {
				$('#ajaxLoader').hide();
				validateResponse(response.data);
				return response;
			}, function (response) {
				$('#ajaxLoader').hide();
				return $q.reject(response);
			});
		};
		
		function validateResponse(data) {
			if(data.params) {
				data.params = angular.fromJson(data.params);
			}
			if(data.content) {
				try {
					if(!data.content) {
						return;
					}
					data.content = angular.fromJson(data.content);
					if(!Array.isArray(data.content)) {
						data.content = toArray(data.content)
					}
				} catch (e) {
					data.content = toArray(data.content);
				}
			}
		}
		
		function toArray(content) {
			var msg = content;
			content = [];
			content.push(msg);
			return content;
		}
	});