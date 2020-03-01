export default class EmployeeListController {
    constructor($http) {
        this.$http = $http;
        this.employeesTotal = 0;
        this.currentPage = 1;
        this.searchName = '';
        this.pageSize = 5;
        this.employees = [];
        this.maxSize = 5;
        this.getEmployees();
    }


    getEmployees() {

        let page = this.currentPage - 1;
        let url = 'api/employee/list?page=' + page + '&name=' + this.searchName;

        this.$http.get(url)
            .then(response => {
                this.employees = response.data.content;
                this.employeesTotal = response.data.totalElements;
            });

    }

}


EmployeeListController.$inject = ['$http'];
