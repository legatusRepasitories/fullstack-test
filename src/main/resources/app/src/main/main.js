import angular from 'angular';
import ngRoute from 'angular-route';
import 'bootstrap/dist/css/bootstrap.min.css';
import ui_bootstrap4 from 'ui-bootstrap4';

import home from '../components/home';
import employeeList from '../components/employeeList';
import organizationList from '../components/organizationList';
import employeeForm from '../components/employeeForm';
import organizationForm from '../components/organizationForm';
import tree from '../components/tree';
import organizationKeySet from '../components/organizationKeySet';
import employeeKeySet from '../components/employeeKeySet';

const app = angular.module('app',
    [ngRoute, ui_bootstrap4, home, employeeList, organizationList,
        employeeForm, organizationForm, tree, organizationKeySet, employeeKeySet])
    .config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode(true);
    }]);

export default app