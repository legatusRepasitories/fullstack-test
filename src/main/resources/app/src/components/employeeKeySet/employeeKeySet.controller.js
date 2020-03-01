export default class EmployeeKeySetController {
    constructor($http) {
        this.$http = $http;
        this.lastId = 0;
        this.searchName = '';
        this.employees = [];
        this.allFetched = false;

        this.loadEmployees();
    }

    loadEmployees() {
        this.lastId = 0;
        this.employees = [];
        this.allFetched = false;
        this.loadMoreEmployees();
    }

    loadMoreEmployees() {

        let url = `api/employee/keySet?lastId=${this.lastId}&name=${this.searchName}`;

        this.$http.get(url)
            .then(response => {
                this.employees = [...this.employees, ...response.data];
                let length = response.data.length;
                this.lastId = length === 0 ? 0 : response.data[length - 1].id;

                this.allFetched = length === 0 || length % 5 !== 0;
            });
    }
}


EmployeeKeySetController.$inject = ['$http'];