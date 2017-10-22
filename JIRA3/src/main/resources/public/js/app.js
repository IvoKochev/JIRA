angular.module('jiraProject', ['ngRoute', 'adminProjectServices', 'jira.controllers'], function() {

}).config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: '/admin/projects.html;',
      controller: 'projectsCtrl'
    }).when('/projectView/:id', {
      templateUrl: 'projectView.html;',
      controller: 'pCtrl'
    });
}]);
