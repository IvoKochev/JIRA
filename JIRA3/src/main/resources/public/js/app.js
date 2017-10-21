angular.module('jiraProject', ['ngRoute', 'jiraServices', 'jira.controllers'], function() {

}).config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/projects', {
      templateUrl: '/admin/projects.html;',
      controller: 'jiraCtrl'
    }).otherwise({
      redirectTo: '/projects'
    });
}]);
