'use strict';

/* Controllers */

var fotonControllers = angular.module('fotonControllers', []);

fotonControllers.controller('AlbumListController', [ '$scope', '$http',
        function($scope, $http) {
            $http.get('rest/album/list').success(function(data) {
                $scope.albums = data;
            });

        } ]);

fotonControllers.controller('AlbumController', [
        '$scope',
        '$http',
        '$routeParams',
        function($scope, $http, $routeParams) {
            $http.get('rest/album/get/' + $routeParams.albumId).success(
                    function(data) {
                        $scope.album = data;
                    });
            $scope.currentIndex = 0;

            $scope.setCurrentSlideIndex = function(index) {
                $scope.currentIndex = index;
            };

            $scope.isCurrentSlideIndex = function(index) {
                return $scope.currentIndex === index;
            };
            $scope.prevSlide = function () {
                $scope.currentIndex = ($scope.currentIndex < $scope.album.photos.length - 1) ? ++$scope.currentIndex : 0;
            };

            $scope.nextSlide = function () {
                $scope.currentIndex = ($scope.currentIndex > 0) ? --$scope.currentIndex : $scope.album.photos.length - 1;
            };
        } ]);
