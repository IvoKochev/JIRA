angular.module('jira.controllers', [])
  .controller('projectsCtrl', function($scope, projectsService) {
    $scope.page = 1;
    var pageSize = 12;
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
    projectsService.all(function(data) {
      console.log(data);
      projects = data;
      $scope.projects = projects.slice(($scope.page - 1) * pageSize, $scope.page * pageSize);
    });
  })
  .controller('pCtrl', function($scope, projectsService) {
    console.log("InP Ctrl");


  });
