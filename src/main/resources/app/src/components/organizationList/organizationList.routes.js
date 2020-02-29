routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/organizationList', {
            templateUrl: './pages/organizationList.html',
            controller: 'OrganizationListController',
            controllerAs: 'organizationList'
        });
}