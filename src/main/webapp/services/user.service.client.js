function AdminUserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;

    this.url = 'https://wbdv-generic-server.herokuapp.com/api/khomiakovkevin/users';

    // POST - Create
    function createUser(user, callback) {
        return fetch(this.url, {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {})
    }

    // GET - Read
    function findAllUsers() {
        return fetch(this.url)
            .then(function(response) {
                return response.json()
            })
    }

    function findUserById(userId, callback) {
        return fetch(this.url + '/' + userId, {
            method: "get",
            body: JSON.stringify(user),
            headers: {
                "content-type": "application/json"
            }
        }).then(function(response) {})
    }

    function deleteUser(userId, callback) {
        return fetch(this.url + '/' + userId, {
            method: "delete"
        }).then(function(response) {});
    }

    function updateUser(userId, user, callback) {
        return fetch(this.url + '/' + userId, {
            method: "put",
            body: JSON.stringify(user),
            headers: {
                "content-type": "application/json"
            }
        }).then(function(response) {});
    }
}