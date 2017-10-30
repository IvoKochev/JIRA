angular.module('adminProjectServices', [])
  .factory('ProjectList', function($http) {
    return {
      all: function(collback) {
        var url = "/list";
        $http.get(url)
          .then(function(data) {
            collback(data.data);
          });
      }
    };
  }).factory('ProjectService', ['$http', function($http) {
    var _posts = function posts(param) {
      console.log(param);
      return $http.get('/common/projectView/' + param);
    };
    var description = '';
    var title = '';
    return {
      title: function() {
        return title;
      },
      setTitle: function(newTitle) {
        title = newTitle;
      },
      description: function() {
        return description;
      },
      setDescription: function(newDescription) {
        description = newDescription;
      },
      posts: _posts
    };
  }]).factory('SprintViewService', ['$http', function($http) {
    var _posts = function posts(param) {
      console.log(param);
      return $http.get('/common/sprintView/' + param);
    };
    var description = '';
    var title = '';
    return {
      title: function() {
        return title;
      },
      setTitle: function(newTitle) {
        title = newTitle;
      },
      description: function() {
        return description;
      },
      setDescription: function(newDescription) {
        description = newDescription;
      },
      posts: _posts
    };
  }]).factory('IssueViewService', ['$http', function($http) {
    var _posts = function posts(param) {
      console.log(param);
      return $http.get('/common/issueView/' + param);
    };
    var description = '';
    var title = '';
    return {
      title: function() {
        return title;
      },
      setTitle: function(newTitle) {
        title = newTitle;
      },
      description: function() {
        return description;
      },
      setDescription: function(newDescription) {
        description = newDescription;
      },
      posts: _posts
    };
  }]);
