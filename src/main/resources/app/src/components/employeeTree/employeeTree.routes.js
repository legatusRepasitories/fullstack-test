routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/employeeTree', {
            templateUrl: './pages/employeeTree.html',
            controller: 'EmployeeTreeController',
            controllerAs: 'employeeTree'
        });
}