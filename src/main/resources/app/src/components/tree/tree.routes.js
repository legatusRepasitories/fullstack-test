routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/organizationTree', {
            templateUrl: './pages/tree.html',
            controller: 'TreeController',
            controllerAs: 'tree'
        })
        .when('/employeeTree', {
            templateUrl: './pages/tree.html',
            controller: 'TreeController',
            controllerAs: 'tree'
        })
}