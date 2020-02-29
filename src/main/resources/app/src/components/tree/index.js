import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './tree.routes';
import TreeController from './tree.controller'

export default angular.module('app.tree', [ngRoute])
    .config(routing)
    .controller('TreeController', TreeController)
    .name;