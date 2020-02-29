import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './employeeList.routes';
import EmployeeListController from './employeeList.controller'

export default angular.module('app.employeeList', [ngRoute])
    .config(routing)
    .controller('EmployeeListController', EmployeeListController)
    .name;