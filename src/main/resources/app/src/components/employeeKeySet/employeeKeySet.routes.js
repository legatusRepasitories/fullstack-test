routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/employeeKeySet', {
            templateUrl: './pages/employeeKeySet.html',
            controller: 'EmployeeKeySetController',
            controllerAs: 'empKeySet'
        });
}