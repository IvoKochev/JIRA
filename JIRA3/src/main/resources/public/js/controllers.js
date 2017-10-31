angular.module('jira.controllers', [])
  .controller('ProjectsCtrl', function($scope, ProjectList) {
    $scope.page = 1;
    var pageSize = 16;
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
    $scope.isVoted = false;
    $scope.myCallback = function(rating, custumVar, project) {
      $scope.isVoted = true;
      var data = {
        ratingId: rating,
        userId: custumVar,
        projectId: project
      };
      $http.post("/rating", data);
      console.log(rating, custumVar);
    };
  }])
  .controller('CreateProjectCtrl', function($scope) {

  })
  .controller('AccountCtrl', function($scope) {

  })
  .controller('SprintCtrl', ['$scope', '$routeParams', '$http', '$location', '$rootScope', function($scope, $routeParams, $http, $location, $rootScope) {
    $scope.projectId = $routeParams.id;
  }])
  .controller('IssueCtrl', ['$scope', '$routeParams', '$http', '$location', '$rootScope', function($scope, $routeParams, $http, $location, $rootScope) {
  $scope.sprintId = $routeParams.id;

  }]).controller('SprintViewCtrl', ['$scope', 'SprintViewService', '$routeParams', '$http', '$location', '$rootScope', function($scope, SprintViewService, $routeParams, $http, $location, $rootScope) {
    SprintViewService.posts($routeParams.id).then(function success(response) {
      $scope.issues = response.data;
      $scope.sprintId = $routeParams.id;
    }, function error(data, status, headers, config) {
      $location.path('/404');
    });
  }]).controller('IssueViewCtrl', ['$scope', 'IssueViewService', '$routeParams', '$http', '$location', '$rootScope', function($scope, IssueViewService, $routeParams, $http, $location, $rootScope) {
    IssueViewService.posts($routeParams.id).then(function success(response) {
      $scope.issue = response.data;
      console.log($scope.issue);
    }, function error(data, status, headers, config) {
      $location.path('/404');
    });
  }])
  .controller('ErrorCtrl', function($scope) {

  });
