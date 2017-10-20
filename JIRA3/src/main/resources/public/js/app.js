angular.module('jiraProject', ['ngRoute', 'jiraServices', 'jira.controllers'], function() {

}).config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/admin/home', {
      templateUrl: '/admin/projects.html;',
      controller: 'jiraCtrl'
    }).otherwise({
      redirectTo: 'admin/home'
    });
}]);
