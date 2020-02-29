export default class OrganizationTreeController {

    constructor($http) {
        this.$http = $http;
        this.baseOrganizations = [];
        this.childOrganizations = [];
        this.initBaseOrganizations();
    }

    initBaseOrganizations() {
        this.$http.get('api/organization/tree')
            .then(response => this.baseOrganizations = response.data);
    }

    loadChildOrganizations(organization) {
        this.$http.get(`api/organization/${organization.id}/child`)
            .then(response => {
                console.log('response:');
                console.log(response.data);
                organization.childOrganizations = response.data
            });
    }

}

OrganizationTreeController.$inject = ['$http'];