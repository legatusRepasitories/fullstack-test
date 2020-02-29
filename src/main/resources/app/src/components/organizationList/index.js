import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './organizationList.routes';
import OrganizationListController from './organizationList.controller'

export default angular.module('app.organizationList', [ngRoute])
    .config(routing)
    .controller('OrganizationListController', OrganizationListController)
    .name;