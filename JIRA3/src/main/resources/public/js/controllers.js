angular.module('jira.controllers', [])
  .controller('ProjectsCtrl', function($scope, ProjectList) {
    $scope.page = 1;
    var pageSize = 18;
    var projects;
    $scope.isPrevDisabled = true;
    $scope.isNextDisabled = false;

    $scope.goToPrevPage = function() {
      if ($scope.page === 1) {
        return;
      }
      --$scope.page;
      if ($scope.page === 1) {
        $scope.isPrevDisabled = true;
      }
      $scope.isNextDisabled = false;
      $scope.projects = projects.slice(($scope.page - 1) * pageSize, $scope.page * pageSize);
    };
    $scope.goToNextPage = function() {
      if ($scope.page >= projects.length / pageSize) {
        return;
      }
      ++$scope.page;
      if ($scope.page >= projects.length / pageSize) {
        $scope.isNextDisabled = true;
      }
      $scope.isPrevDisabled = false;
      $scope.projects = projects.slice(($scope.page - 1) * pageSize, $scope.page * pageSize);
    };
    ProjectList.all(function(data) {
      console.log(data);
      projects = data;
      $scope.projects = projects.slice(($scope.page - 1) * pageSize, $scope.page * pageSize);
    });
  })
  .controller('ProjectViewCtrl', ['$scope', 'ProjectService', '$routeParams', '$http', '$location', '$rootScope', function($scope, ProjectService, $routeParams, $http, $location, $rootScope) {
    ProjectService.posts($routeParams.id).then(function success(response) {
      $scope.project = response.data;
    }, function error(data, status, headers, config) {
      $location.path('/404');
    });
  }])
  .controller('CreateProjectCtrl', function($scope) {
    console.log("CreateProjectCtrl");

    
  }).controller('ErrorCtrl', function($scope) {
    console.log("404Ctrl");
  });
