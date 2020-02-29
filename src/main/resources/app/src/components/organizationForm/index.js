import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './organizationForm.routes';
import OrganizationFormController from './organizationForm.controller'

export default angular.module('app.organizationForm', [ngRoute])
    .config(routing)
    .controller('OrganizationFormController', OrganizationFormController)
    .name;