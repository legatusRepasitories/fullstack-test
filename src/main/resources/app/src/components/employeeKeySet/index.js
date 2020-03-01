import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './employeeKeySet.routes';
import EmployeeKeySetController from './employeeKeySet.controller'

export default angular.module('app.employeeKeySet', [ngRoute])
    .config(routing)
    .controller('EmployeeKeySetController', EmployeeKeySetController)
    .name;