routes.$inject = ['$routeProvider'];

export default function routes($routeProvider) {
    $routeProvider
        .otherwise( {
            templateUrl: './pages/home.html',
            controller: 'HomeController',
            controllerAs: 'home'
        });
}