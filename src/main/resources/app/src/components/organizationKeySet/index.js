import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './organizationKeySet.routes';
import OrganizationKeySetController from './organizationKeySet.controller'

export default angular.module('app.organizationKeySet', [ngRoute])
    .config(routing)
    .controller('OrganizationKeySetController', OrganizationKeySetController)
    .name;