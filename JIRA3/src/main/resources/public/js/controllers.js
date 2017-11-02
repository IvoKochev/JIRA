angular.module('jira.controllers', [])
  .controller('ProjectsCtrl', function($scope, ProjectList) {
    $scope.page = 1;
    var pageSize = 15;
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
  .controller('ProjectViewCtrl', ['$scope', 'ProjectService', '$routeParams', '$http', '$location', '$rootScope', '$filter', function($scope, ProjectService, $routeParams, $http, $location, $rootScope, $filter) {
    $scope.pageS = 1;
    $scope.pageU = 1;
    var pageSizeS = 21;
    var pageSizeU = 8;
    var sprints;
    var users;
    $scope.isPrevDisabledS = true;
    $scope.isNextDisabledS = false;
    $scope.isPrevDisabledU = true;
    $scope.isNextDisabledU = false;



    $scope.goToPrevPageS = function() {
      if ($scope.pageS === 1) {
        return;
      }
      --$scope.pageS;
      if ($scope.pageS === 1) {
        $scope.isPrevDisabledS = true;
      }
      $scope.isNextDisabledS = false;
      $scope.sprints = sprints.slice(($scope.pageS - 1) * pageSizeS, $scope.pageS * pageSizeS);
    };
    $scope.goToNextPageS = function() {
      if ($scope.pageS >= sprints.length / pageSizeS) {
        return;
      }
      ++$scope.pageS;
      if ($scope.pageS >= sprints.length / pageSizeS) {
        $scope.isNextDisabledS = true;
      }
      $scope.isPrevDisabledS = false;
      $scope.sprints = sprints.slice(($scope.pageS - 1) * pageSizeS, $scope.pageS * pageSizeS);
    };



    $scope.goToPrevPageU = function() {
      if ($scope.pageU === 1) {
        return;
      }
      --$scope.pageU;
      if ($scope.pageU === 1) {
        $scope.isPrevDisabledU = true;
      }
      $scope.isNextDisabledU = false;
      $scope.users = users.slice(($scope.pageU - 1) * pageSizeU, $scope.pageU * pageSizeU);
    };
    $scope.goToNextPageU = function() {
      if ($scope.pageU >= users.length / pageSizeU) {
        return;
      }
      ++$scope.pageU;
      if ($scope.pageU >= users.length / pageSizeU) {
        $scope.isNextDisabledU = true;
      }
      $scope.isPrevDisabledU = false;
      $scope.users = users.slice(($scope.pageU - 1) * pageSizeU, $scope.pageU * pageSizeU);
    };


    ProjectService.posts($routeParams.id).then(function success(response) {
      $scope.project = response.data;
      sprints = $scope.project.sprints;
      $scope.sprints = sprints.slice(($scope.pageS - 1) * pageSizeS, $scope.pageS * pageSizeS);
      var usersTemp = $scope.project.users;
      u = $filter('orderBy')(usersTemp, '-rating');
      users = u;
      $scope.users = users.slice(($scope.pageU - 1) * pageSizeU, $scope.pageU * pageSizeU);

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
      $scope.page = 1;
      var pageSize = 3;
      var issues;
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
        $scope.issues = issues.slice(($scope.page - 1) * pageSize, $scope.page * pageSize);
      };
      $scope.goToNextPage = function() {
        if ($scope.page >= issues.length / pageSize) {
          return;
        }
        ++$scope.page;
        if ($scope.page >= issues.length / pageSize) {
          $scope.isNextDisabled = true;
        }
        $scope.isPrevDisabled = false;
        $scope.issues = issues.slice(($scope.page - 1) * pageSize, $scope.page * pageSize);
      };
      $scope.sprintId = $routeParams.id;
      issues = response.data;

      $scope.issues = issues.slice(($scope.page - 1) * pageSize, $scope.page * pageSize);
    }, function error(data, status, headers, config) {
      $location.path('/404');
    });
  }]).controller('IssueViewCtrl', ['$scope', 'IssueViewService', '$routeParams', '$http', '$location', '$rootScope', function($scope, IssueViewService, $routeParams, $http, $location, $rootScope) {
    IssueViewService.posts($routeParams.id).then(function success(response) {
      $scope.issue = response.data;

    }, function error(data, status, headers, config) {
      $location.path('/404');
    });
  }])
  .controller('ErrorCtrl', function($scope) {

  });
