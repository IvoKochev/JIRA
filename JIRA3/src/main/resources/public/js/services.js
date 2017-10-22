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
  })
  .factory('Page', ['$http', function($http) {
        var _posts = function posts(param) {
  console.log(param);
          return  $http.get('/admin/projectView/'+ param);
        };
                var description = '';
                var title = '';
                return {
                    title: function () { return title; },
                    setTitle: function (newTitle) { title = newTitle; },
                    description: function () { return description; },
                    setDescription: function (newDescription) { description = newDescription; },
                    posts : _posts
            };
    }]);
