var app = angular.module( 'xstore', [
  'auth0',
  'ngRoute'
]);



app.config( function myAppConfig ( $routeProvider, authProvider, $httpProvider, $locationProvider) {
  $routeProvider
    .when( '/', {
      controller: 'ProductListCtrl',
      templateUrl: 'Product/list.scala.html',
      pageTitle: 'All Products',
      requiresLogin: true
    })

    .when( '/product/crt/', {
      controller: 'ProductCreateCtrl',
      templateUrl: 'Product/create.scala.html',
      pageTitle: 'Create Product',
      requiresLogin: true
    })

    .when( '/product/show/:id', {
      controller: 'ProductShowCtrl',
      templateUrl: 'Product/show.scala.html',
      pageTitle: 'Product Details',
      requiresLogin: true
    })
	
    .when( '/product/list', {
      controller: 'ProductListCtrl',
      templateUrl: 'Product/list.scala.html',
      pageTitle: 'Product Details',
      requiresLogin: true
    })
	
    .when( '/login', {
      templateUrl: 'Application/login.scala.html',
      pageTitle: 'Login'
    });


  authProvider.init({
    domain: "boxed.auth0.com",
    clientID: "rectojRIfjERbByPK2AdO7EHf9ywZt3U",
    callbackURL: "http://xposeb.herokuapp.com/authenticate",
    loginUrl: '/login'
  });

  authProvider.on('loginSuccess', function($location) {
    $location.path('/');
  });

  authProvider.on('loginFailure', function($log, error) {
    $log('Error logging in', error);
  });

  $httpProvider.interceptors.push('authInterceptor');
})
.run(function(auth) {
  auth.hookEvents();
});



app.controller( 'AppCtrl', ['$scope', '$location', function ( $scope, $location ) {

  $scope.$on('$routeChangeSuccess', function(e, nextRoute){
  
    if ( nextRoute.$$route && angular.isDefined( nextRoute.$$route.pageTitle ) ) {
	
      $scope.pageTitle = nextRoute.$$route.pageTitle;
    }
  });
}]);


app.controller( 'RtCtrl', [ '$scope', '$location', 'auth', function ( $scope, $location, auth ) {

	$scope.user_name = "Sohan Nohemy";
	
	$scope.login = function () {
		console.log("login");
		auth.signin({
		  // popup: true to use popup instead of redirect
		});
	}
	
	$scope.logout = function () {
		console.log("logout");
		auth.signout();
		$location.path('/login');
	}

}]);


app.controller( 'ProductListCtrl', [ '$scope', '$location', function ( $scope, $location ) {

	$scope.products = [
		{id: 2, name: "product Name", description: "Description"},
	];
	
	for(var i = 1; i <= 10; i++) {
		$scope.products[i - 1] = {id: i, name: "product Name", description: "Description"};
	}
	
}]);


app.controller( 'ProductCreateCtrl', [ '$scope', '$location', function ( $scope, $location ) {



}]);

app.controller( 'ProductShowCtrl', [ '$scope', '$location', function ( $scope, $location ) {



}]);