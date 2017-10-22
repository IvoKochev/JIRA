angular.module('jiraProject', ['ngRoute', 'adminProjectServices', 'jira.controllers'], function() {

}).config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/projects', {
      templateUrl: 'projects.html;',
      controller: 'projectsCtrl'
    }).when('/projectView', {
      templateUrl: 'projectView.html;',
      controller: 'pCtrl'
    }).otherwise({
      redirectTo: '/projects'
    });
}]);
