import angular from 'angular';
import ngRoute from 'angular-route';

import routing from './employeeForm.routes';
import EmployeeFormController from './employeeForm.controller'

export default angular.module('app.employeeForm', [ngRoute])
    .config(routing)
    .controller('EmployeeFormController', EmployeeFormController)
    .name;