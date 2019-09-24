function AdminUserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;

    // POST - Create
    function createUser(user) {
        return fetch('https://wbdv-generic-server.herokuapp.com/api/khomiakovkevin/users', {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {})
    }

    // GET - Read
    function findAllUsers() {
        return fetch('https://wbdv-generic-server.herokuapp.com/api/khomiakovkevin/users')
            .then(function(response) {
                return response.json()
            })
    }

    function updateUser(userId, user, callback) {
        return fetch('https://wbdv-generic-server.herokuapp.com/api/khomiakovkevin/users' + '/' + userId, {
            method: "put",
            body: JSON.stringify(user),
            headers: {
                "content-type": "application/json"
            }
        }).then(function(response) {});
    }

    function deleteUser(userId, callback) {
        return fetch('https://wbdv-generic-server.herokuapp.com/api/khomiakovkevin/users' + '/' + userId, {
            method: "delete"
        }).then(function(response) {});
    }
}