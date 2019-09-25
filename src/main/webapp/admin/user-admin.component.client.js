(function() {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $roleFld;
    var $idFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $idFld = $("#idFld");
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");

        $userRowTemplate = $('.wbdv-template');
        $tbody = $('.wbdv-tbody');

        $idFld.val("");

        $(".wbdv-create").click(function() {
            createUser();
        });

        $(".wbdv-update").click(function() {
            updateUser();
        });

        findAllUsers()
    }

    function createUser() {
        user = getData();

        userService
            .createUser(user)
            .then(findAllUsers);

        clearForm();
    }

    function getData() {

        var id = Date.now();
        var username = $usernameFld.val();
        var pass = $passwordFld.val();
        var fname = $firstNameFld.val();
        var lname = $lastNameFld.val();
        var role = $roleFld.val();

        return new User(id, username, pass, fname, lname, role);
    }

    function renderUser(user) {
        var clone = $userRowTemplate.clone();

        clone.attr("class", "wbdv-user");
        clone.attr("id", "user" + user.id);
        clone.find(".wbdv-username").html(user.username);
        clone.find(".wbdv-password").html(user.password);
        clone.find(".wbdv-first-name").html(user.firstName);
        clone.find(".wbdv-last-name").html(user.lastName);
        clone.find(".wbdv-role").html(user.role);

        clone.on("click", ".wbdv-remove", function() {
            deleteUser(user.id);
        });
        clone.on("click", ".wbdv-edit", function() {
            selectUser(user.id);
        });

        $tbody.append(clone);
    }

    function updateUser() {

        var username = $usernameFld.val();
        var pass = $passwordFld.val();
        var fname = $firstNameFld.val();
        var lname = $lastNameFld.val();
        var role = $roleFld.val();

        user = {
            "id": $idFld.val(),
            "username": username,
            "password": pass,
            "firstName": fname,
            "lastName": lname,
            "role": role
        }

        if (user != null) {
            userService.updateUser($idFld.val(), user).then(findAllUsers);
            clearForm();
        }

        $idFld.val("");
    }

    function findUserById(id) {
        userService
            .findUserById(id)
            .then(renderUser);
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function deleteUser(id) {
        userService
            .deleteUser(id)
            .then(findAllUsers);
    }

    function selectUser(id) {

        $row = $("#user" + id);

        $idFld.val(id);

        $usernameFld.val($row.find(".wbdv-username").html());
        $passwordFld.val($row.find(".wbdv-password").html());
        $firstNameFld.val($row.find(".wbdv-first-name").html());
        $lastNameFld.val($row.find(".wbdv-last-name").html());
        $roleFld.val($row.find(".wbdv-role").html()).change();
    }


    function renderUsers(users) {
        $tbody.empty();

        for (var u in users) {
            renderUser(users[u]);
        }
    }

    function clearForm() {
        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
    }
})()