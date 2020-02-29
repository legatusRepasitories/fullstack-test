export default class EmployeeTreeController {

    constructor($http) {
        this.$http = $http;
        this.baseEmployees = [];
        this.childEmployees = [];
        this.initBaseBaseEmployees();
    }

    initBaseBaseEmployees() {
        this.$http.get('api/employee/tree')
            .then(response => this.baseEmployees = response.data);
    }

    loadChildEmployees(employee) {
        this.$http.get(`api/employee/${employee.id}/child`)
            .then(response => {
                console.log('response:');
                console.log(response.data);
                employee.childEmployees = response.data
            });
    }

}

EmployeeTreeController.$inject = ['$http'];