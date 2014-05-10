var fotonApp = angular.module('fotonApp', [ 'ngRoute', 'fotonControllers' ]);

fotonApp.config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/album', {
        templateUrl : 'resources/partials/albumList.html',
        controller : 'AlbumListController'
    }).when('/album/:albumId', {
        templateUrl : 'resources/partials/album.html',
        controller : 'AlbumController'
    }).otherwise({
        redirectTo : '/album'
    });
} ]);