/**
 * @author Tunahan.Pehlivan
 */
	
var app = angular.module('app', ['service', 'sharedServices']);

	app.controller('bookController', ['$scope', '$timeout', 'generalUtility', 'bookService', 
	    function($scope, $timeout, generalUtilityFactory, bookService) {
			// initialization
			$scope.showModal = false;
			$scope.dialogButtonStr = ''
			$scope.currentBook = {};
			$scope.showError = false;

			$timeout(function() {
				loadAllBooks();
			});
			
			//public functions
			$scope.openEditDialog = function(book) {
				var b = {id: book.id, name: book.name, author: book.author}; // FIXME: burayı adam gibi yaz
				$scope.dialogButtonStr = 'Güncelle';
				$scope.showError = false;
				$scope.currentBook = b;
				openDialog();
			};
			
			$scope.openSaveDialog = function() {
				$scope.dialogButtonStr = 'Kaydet';
				$scope.showError = false;
				$scope.currentBook = {};
				openDialog();
			};
			
			$scope.removeBook = function(book) {
				if(window.confirm("Emin misiniz?")) {
					bookService.remove(book.id)
						.then(function(data) {
							generalUtilityFactory.removeItemById($scope.books, book);
						}, handleException);
				}
			};
			
			$scope.saveOrUpdateBook = function() {
				var book = $scope.currentBook;
				if(!book.id) {
					bookService.save(book)
						.then(function(data) {
							book.id = data.params.id;
							$scope.books.push(book);
							closeDialog();
						}, handleExceptionWithDialog);
				} else {
					bookService.update(book)
						.then(function(data) {
							generalUtilityFactory.updateItemById($scope.books, book);
							closeDialog();
						}, handleExceptionWithDialog);
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
				alert(data.content[0]);
			}
			
			function handleExceptionWithDialog(data) {
				$scope.showError = true;
				$scope.errorMessages = data.content;
			}
			
			function loadAllBooks() {
				bookService.findAll()
					.then(function(data) {
						$scope.books = data.params;
					}, handleException);
			}
	}]);