angular.module('jira.controllers', [])
  .controller('jiraCtrl', function($scope, projectService) {
    console.log("In Jira Controoler");
    projectService.all(function(data) {
      console.log(data);
      $scope.projects = data;

    });
  });
