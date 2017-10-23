angular.module('jiraProject', ['ngRoute', 'adminProjectServices', 'jira.controllers'], function() {

}).config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: '/admin/projects.html;',
      controller: 'ProjectsCtrl'
    }).when('/projectView/:id', {
      templateUrl: 'projectView.html;',
      controller: 'ProjectViewCtrl'
    });
}]);
