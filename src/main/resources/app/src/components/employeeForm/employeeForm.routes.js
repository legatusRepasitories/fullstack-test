routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/employeeForm', {
            templateUrl: './pages/employeeForm.html',
            controller: 'EmployeeFormController',
            controllerAs: 'employeeForm'
        })
        .when('/employeeForm/:id', {
            templateUrl: './pages/employeeForm.html',
            controller: 'EmployeeFormController',
            controllerAs: 'employeeForm',

        });
}