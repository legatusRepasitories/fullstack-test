routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/organizationTree', {
            templateUrl: './pages/organizationTree.html',
            controller: 'OrganizationTreeController',
            controllerAs: 'organizationTree'
        });
}