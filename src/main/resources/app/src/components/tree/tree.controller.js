export default class TreeController {

    constructor($http, $location) {
        this.$http = $http;
        this.url = $location.absUrl().includes('organization') ? 'organization' : 'employee';
        this.elements = [];

        this.initBaseElements();
    }

    initBaseElements() {
        this.$http.get(`api/${this.url}/tree`)
            .then(response => {
                // console.log(response.data);
                this.elements = response.data;
                // console.log(this.elements);
            });
    }

    loadChildElements(element) {
        if (element.childs !== undefined && element.childs.length > 0) {
            element.childs = [];
            return;
        }
        this.$http.get(`api/${this.url}/${element.id}/child`)
            .then(response => {
                // console.log('response:');
                // console.log(response.data);
                element.childs = response.data;
                // console.log(this);
            });
    }

}

TreeController.$inject = ['$http', '$location'];