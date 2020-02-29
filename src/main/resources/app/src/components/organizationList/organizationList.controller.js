export default class OrganizationListController {
    constructor($http) {
        this.$http = $http;
        this.organizationsTotal = 0;
        this.currentPage = 1;
        this.searchName = '';
        this.pageSize = 5;
        this.organizations = [];
        this.getOrganizations();
    }


    getOrganizations() {

        let page = this.currentPage - 1;
        let url = 'api/organization/list?page=' + page + '&name=' + this.searchName;

        this.$http.get(url)
            .then(response => {
                this.organizations = response.data.content;
                this.organizationsTotal = response.data.totalElements;
            });

    }

}


OrganizationListController.$inject = ['$http'];
