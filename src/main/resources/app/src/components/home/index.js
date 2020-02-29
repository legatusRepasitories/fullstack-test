import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './home.routes';
import HomeController from './home.controller';

export default angular.module('app.home', [ngRoute])
    .config(routing)
    .controller('HomeController', HomeController)
    .name;