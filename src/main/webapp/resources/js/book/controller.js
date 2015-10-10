/**
 * @author Tunahan.Pehlivan
 */
	
var app = angular.module('app', ['service', 'sharedServices']);

	app.controller('bookController', ['$scope', '$timeout', 'generalUtility', 'bookService', 
	    function($scope, $timeout, generalUtility, bookService) {
			// initialization
			$scope.showModal = false;
			$scope.currentBook = {};
			$scope.showError = false;
			$scope.errorMessages = [];
			$scope.captcha = {};

			$timeout(function() {
				loadAllBooks();
			});
			
			//public functions
			$scope.openEditDialog = function(book) {
				var b = {id: book.id, name: book.name, author: book.author}; // FIXME: burayı adam gibi yaz
				$scope.dialogMode = "edit";
				$scope.showError = false;
				$scope.currentBook = b;
				openDialog();
			};
			
			$scope.openSaveDialog = function() {
				$scope.dialogMode = "save";
				$scope.showError = false;
				$scope.currentBook = {};
				$scope.captcha = {};
				$scope.captcha.generatedVal= generalUtility.createCaptcha();
				openDialog();
			};
			
			$scope.removeBook = function(book) {
				if(window.confirm("Emin misiniz?")) {
					bookService.remove(book.id)
						.then(function(data) {
							generalUtility.removeItemById($scope.books, book);
						}, handleException);
				}
			};
			
			$scope.saveOrUpdateBook = function() {
				var book = $scope.currentBook;
				if(!book.id) {
					//save mode
					if(!captchaIsValid()) {
						showError(["Girilen captcha değeri hatalı!"])
						return;
					}
					bookService.save(book)
						.then(function(data) {
							book.id = data.params.id;
							$scope.books.push(book);
							closeDialog();
						}, handleExceptionWithDialog);
				} else {
					//update mode
					bookService.update(book)
						.then(function(data) {
							generalUtility.updateItemById($scope.books, book);
							closeDialog();
						}, handleExceptionWithDialog);
				}
			};
			
			$scope.getDialogButtonStr = function() {
				if($scope.dialogMode == "save"){
					return "Kaydet";
				} else if($scope.dialogMode == "edit") {
					return "Güncelle";
				}
			};
			
			//private functions
			function openDialog() {
				$scope.showModal = true;
			}
			
			function closeDialog() {
				$scope.showModal = false;
			}
			
			function handleException(data) {
				// TODO : show data
			}
			
			function handleExceptionWithDialog(data) {
				showError(data.content);
			}
			
			function showError(msgArr) {
				$scope.showError = true;
				$scope.errorMessages = msgArr;
			}
			
			function captchaIsValid() {
				return $scope.captcha.generatedVal == $scope.captcha.val; 
			}
			
			function loadAllBooks() {
				bookService.findAll()
					.then(function(data) {
						$scope.books = data.params;
					}, handleException);
			}
	}]);