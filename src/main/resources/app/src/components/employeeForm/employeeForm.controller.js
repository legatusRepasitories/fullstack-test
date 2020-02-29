export default class EmployeeFormController {
    constructor($http, $routeParams) {
        this.$http = $http;
        this.$routeParams = $routeParams;
        this.id = this.$routeParams.id === undefined ? undefined : Number.parseInt($routeParams.id);

        this.name = '';
        this.organizations = [];
        this.organizationEmployees = [];

        if (this.isNew()) {
            this.getOrganizations()
                .then(response => this.organizations = response.data);
        } else {
            this.fillFormWithData();
        }
    }

    isNew() {
        return angular.isUndefined(this.id);
    }

    getOrganizations() {
        return this.$http.get('api/organization/all');
    }

    fillFormWithData() {

        this.getOrganizations()
            .then(response => {
                this.organizations = response.data;
                return this.$http.get(`api/employee/${this.id}`);
            })
            .then(response => {
                let employee = response.data;
                this.chiefId = employee.chiefId;
                this.name = employee.name;
                this.organization = this.organizations
                    .find(organization => {
                        return organization.id === employee.organizationId
                    });

                return this.$http.get('api/employee/organization/' + this.organization.id);
            })
            .then(response => {
                this.organizationEmployees = response.data;

                this.organizationEmployees =
                    this.organizationEmployees.filter(employee => employee.id !== this.id);

                this.chief = this.organizationEmployees.find(chief => chief.id === this.chiefId)
            });
    }


    getOrganizationEmployees() {
        this.$http.get('api/employee/organization/' + this.organization.id)
            .then(response => this.organizationEmployees = response.data);
    }


    saveEmployee() {
        let employee = {
            id: this.isNew() ? null : this.id,
            name: this.name,
            organizationId: this.organization.id,
            chiefId: this.isNew() ? null : this.chief.id
        };

        console.log(employee);

        this.isNew() ?
            this.$http.post('api/employee', employee).then(response => this.result = 'saved!') :
            this.$http.put('api/employee', employee).then(response => this.result = 'updated!');

    }

    delete() {
        this.$http.delete(`api/employee/${this.id}`).then(response => {

            this.result = response.data.length === 0 ? 'cannot be deleted': 'deleted';
        });
    }

}

EmployeeFormController.$inject = ['$http', '$routeParams'];
