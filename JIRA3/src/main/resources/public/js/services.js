angular.module('adminProjectServices', [])
  .factory('projectsService', function($http) {
    return {
      all: function(collback) {
        var url = "/list";
        $http.get(url)
          .then(function(data) {
            collback(data.data);
          });
      }
    };
  });
