import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './organizationTree.routes';
import OrganizationTreeController from './organizationTree.controller'

export default angular.module('app.organizationTree', [ngRoute])
    .config(routing)
    .controller('OrganizationTreeController', OrganizationTreeController)
    .name;