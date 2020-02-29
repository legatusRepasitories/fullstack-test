export default class OrganizationFormController {
    constructor($http, $routeParams) {
        this.$http = $http;
        this.$routeParams = $routeParams;
        this.id = this.$routeParams.id === undefined ? undefined : Number.parseInt($routeParams.id);

        this.name = '';
        this.organizations = [];


        if (this.isNew()) {
            this.getOrganizations()
                .then(response => this.organizations = response.data);
        } else {
            this.fillFormData();
        }
    }

    isNew() {
        return angular.isUndefined(this.id);
    }


    fillFormData() {
        this.getOrganizations()
            .then(response => {
                this.organizations = response.data;
                return this.$http.get(`api/organization/${this.id}`);
            })
            .then(response => {
                let organization = response.data;

                this.name = organization.name;
                this.headOrganizationId = organization.headOrganizationId;
                this.organization = this.organizations.find(headOrg => headOrg.id === this.headOrganizationId)
                this.organizations = this.organizations.filter(organization => organization.id !== this.id);
            })
    }


    getOrganizations() {
        return this.$http.get('api/organization/all');
    }


    saveOrganization() {
        let organization = {
            id: this.isNew() ? null : this.id,
            name: this.name,
            headOrganizationId: this.organization === undefined || this.organization === null ? null : this.organization.id
        };



        this.isNew() ?
            this.$http.post('api/organization', organization).then(response => this.result = 'saved!') :
            this.$http.put('api/organization', organization).then(response => this.result = 'updated!');

    }

    delete() {
        this.$http.delete(`api/organization/${this.id}`).then(response => {

            this.result = response.data.length === 0 ? 'cannot be deleted': 'deleted';
        });
    }

}

OrganizationFormController.$inject = ['$http', '$routeParams'];
