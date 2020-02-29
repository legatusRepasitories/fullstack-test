routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/employeeList', {
            templateUrl: './pages/employeeList.html',
            controller: 'EmployeeListController',
            controllerAs: 'employeeList'
        });
}