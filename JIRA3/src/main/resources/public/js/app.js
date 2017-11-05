angular.module('jiraProject', ['ngRateIt', 'ngFileSaver', 'ngRoute', 'adminProjectServices', 'jira.controllers'])

  .config(['$routeProvider', '$compileProvider', function($routeProvider, $compileProvider) {
    $routeProvider
      .when('/', {
        templateUrl: '/common/projects.html;',
        controller: 'ProjectsCtrl'
      }).when('/projectView/:id', {
        templateUrl: 'projectView.html;',
        controller: 'ProjectViewCtrl'
      }).when('/createProject', {
        templateUrl: '/createProject.html;',
        controller: 'CreateProjectCtrl'
      }).when('/account', {
        templateUrl: '/common/account.html;',
        controller: 'AccountCtrl'
      }).when('/createSprint/:id', {
        templateUrl: 'createSprint.html;',
        controller: 'SprintCtrl'
      }).when('/createIssue/:id', {
        templateUrl: 'createIssue.html;',
        controller: 'IssueCtrl'
      }).when('/sprintView/:id', {
        templateUrl: 'sprintView.html;',
        controller: 'SprintViewCtrl'
      }).when('/issueView/:id', {
        templateUrl: 'issueView.html;',
        controller: 'IssueViewCtrl'
      }).when('/getAttachment/:id', {
        templateUrl: 'issueView.html;',
        controller: 'AttachmentCtrl'
      });
    // $compileProvider.aHrefSanitizationWhitelist(/^\s*(|blob|):/);
  }]);
