<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="resources/js/library/jquery.min.js"></script>
<script src="resources/js/library/bootstrap.min.js"></script>
<script src="resources/js/library/angular.min.1.0.7.js"></script>
<script src="resources/js/book/controller.js"></script>
<script src="resources/js/book/service.js"></script>
<script src="resources/js/book/directive.js"></script>
<script src="resources/js/book/factory.js"></script>
<script src="resources/js/book/app.js"></script>


<link rel="stylesheet"
	href="resources/css/library/bootstrap.min.css" type="text/css" />
<link rel="stylesheet"
	href="resources/css/main.css" type="text/css" />

<title>LibraryApp</title>
</head>
<body ng-app="app">
	<div ng-controller="bookController" class="tableField">

		<button ng-click="openSaveDialog()" class="btn btn-info" style="margin-bottom: 7px">
			Yeni Kayıt Ekle
		</button>
		
		<table class="table">
			<tr>
				<th>Kitap ismi</th>
				<th>Yazar</th>
				<th></th>
			</tr>
			<tr ng-repeat="book in books">
				<td>{{book.name}}</td>
				<td>{{book.author}}</td>
				<td>
					<button ng-click="openEditDialog(book)" class="btn btn-warning">
						Güncelle
					</button>
					<button ng-click="removeBook(book);" class="btn btn-danger">
						Sil
					</button>
				</td>
			</tr>
		</table>

		<modal title="LibraryApp" visible="showModal">
			<div data-ng-show="showError" class="alert alert-danger">
				<ul ng-repeat="msg in errorMessages">
					<li>{{msg}}</li>
				</ul>
			</div>
			
			<form role="form">
				<div class="form-group">
					<label for="name">Kitap ismi</label> 
					<input class="form-control" id="name" vvalue="currentBook.name" ng-model="currentBook.name" />
				</div>
				<div class="form-group">
					<label for="author">Yazar</label>
					<input class="form-control" id="author" value="currentBook.author" ng-model="currentBook.author" />
				</div>
				<div class="form-group" ng-show="dialogMode == 'save'">
					<div class="captcha">
						<label>
							{{captcha.generatedVal}}
						</label>
					</div>
					<label for="generatedCaptcha">Captcha değerini giriniz</label>
					<input class="form-control" id="captcha" value="captcha.val" ng-model="captcha.val" />
				</div>
				<button type="submit" class="btn btn-primary" ng-click="saveOrUpdateBook()">
					{{getDialogButtonStr()}}
				</button>
			</form>
		</modal>
	</div>

	<div id="ajaxLoader">
		<img src="resources/img/ajax-loader.gif">
	</div>
</body>
</html>