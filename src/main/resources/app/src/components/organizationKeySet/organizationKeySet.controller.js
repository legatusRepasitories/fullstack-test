export default class OrganizationKeySetController {
    constructor($http) {
        this.$http = $http;
        this.lastId = 0;
        this.searchName = '';
        this.organizations = [];
        this.allFetched = false;

        this.loadOrganizations();
    }

    loadOrganizations() {
        this.lastId = 0;
        this.organizations = [];
        this.allFetched = false;
        this.loadMoreOrganizations();
    }

    loadMoreOrganizations() {

        let url = `api/organization/keySet?lastId=${this.lastId}&name=${this.searchName}`;

        this.$http.get(url)
            .then(response => {
                this.organizations = [...this.organizations, ...response.data];
                let length = response.data.length;
                this.lastId = length === 0 ? 0 : response.data[length - 1].id;

                this.allFetched = length === 0 || length % 5 !== 0;
            });
    }
}


OrganizationKeySetController.$inject = ['$http'];