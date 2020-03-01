routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/organizationKeySet', {
            templateUrl: './pages/organizationKeySet.html',
            controller: 'OrganizationKeySetController',
            controllerAs: 'orgKeySet'
        });
}