import angular from 'angular';
import ngRoute from 'angular-route';
import ui_bootstrap from 'angular-ui-bootstrap';

import home from '../components/home';
import employeeList from '../components/employeeList';
import organizationList from '../components/organizationList';
import employeeForm from '../components/employeeForm';
import organizationForm from '../components/organizationForm';
// import organizationTree from '../components/organizationTree';
// import employeeTree from '../components/employeeTree';
import tree from '../components/tree';

const app = angular.module('app',
    [ngRoute, ui_bootstrap, home, employeeList, organizationList,
        employeeForm, organizationForm,tree])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode(true);
    });

export default app