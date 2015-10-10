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
	        },
	        
	        createCaptcha: function() {
	        	var captchaVal = "";
	        	var startOfLowerLetter = 65;
	        	var startOfUpperLetter = 97;
	        	var rangeVal = 25;
	        	for(var i = 0; i < 7 ;i++) {
	        		var randomVal = Math.floor(Math.random() * rangeVal);
	        		if(randomVal < 10) {
	        			captchaVal += randomVal;
	        		} else {
	        			if(randomVal < 17) {
	        				captchaVal += String.fromCharCode(startOfLowerLetter + randomVal);
	        			} else {
	        				captchaVal += String.fromCharCode(startOfUpperLetter + randomVal);
	        			}
	        		}
	        	}
	        	return captchaVal;
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
					data.content = angular.fromJson(data.content);
					if(!Array.isArray(data.content)) {
						data.content = convertArray(data.content)
					}
				} catch (e) {
					data.content = convertArray(data.content);
				}
			}
		}
		
		function convertArray(content) {
			var msg = content;
			content = [];
			content.push(msg);
			return content;
		}
	});