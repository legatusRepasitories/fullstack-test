routing.$inject = ['$routeProvider'];

export default function routing($routeProvider) {
    $routeProvider
        // .when('/employeeList', {
        //     templateUrl: './pages/employeeList.html',
        //     controller: 'employeeListController'
        // })
        .when('/organizationList', {
            templateUrl: './pages/organizationList.html',
            controller: 'organizationListController'
        })
}