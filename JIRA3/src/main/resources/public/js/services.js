angular.module('jiraServices', [])
  .factory('projectService', function($http) {
    return {
      all: function(collback) {
        var url = "/project/list";
        $http.get(url)
          .then(function(data) {
            collback(data.data);
          });
      }
    };
  });
