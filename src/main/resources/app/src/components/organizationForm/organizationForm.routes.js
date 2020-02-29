routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .when('/organizationForm', {
            templateUrl: './pages/organizationForm.html',
            controller: 'OrganizationFormController',
            controllerAs: 'organizationForm'
        })
        .when('/organizationForm/:id', {
            templateUrl: './pages/organizationForm.html',
            controller: 'OrganizationFormController',
            controllerAs: 'organizationForm',

        });
}