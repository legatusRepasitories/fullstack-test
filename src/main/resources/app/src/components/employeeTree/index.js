import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './employeeTree.routes';
import EmployeeTreeController from './employeeTree.controller'

export default angular.module('app.employeeTree', [ngRoute])
    .config(routing)
    .controller('EmployeeTreeController', EmployeeTreeController)
    .name;