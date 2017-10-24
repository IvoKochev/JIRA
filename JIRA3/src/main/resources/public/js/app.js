angular.module('jiraProject', ['ngRoute', 'adminProjectServices', 'jira.controllers'], function() {

}).config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: '/common/projects.html;',
      controller: 'ProjectsCtrl'
    }).when('/projectView/:id', {
      templateUrl: 'projectView.html;',
      controller: 'ProjectViewCtrl'
    }).when('/404', {
      templateUrl: '404.html;',
      controller: 'ErrorCtrl'
    });
}]);
