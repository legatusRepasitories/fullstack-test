app.controller('getcontroller', function($scope, $http, $location) {

    $scope.getfunction = function(){
        let url = $location.absUrl() + "/api/organization/list";

        $http.get(url).then(function (response) {

            $scope.response = response.data;

        }, function error(response) {

            $scope.postResultMessage = "Error with status: " +  response.statusText;

        });
    }
});

