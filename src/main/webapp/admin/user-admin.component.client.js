(function() {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    // var users = [
    // {
    //     username: 'johnsena',
    //     password: '123123',
    //     firstname: 'john',
    //     lastname: 'sena',
    //     role: 'Student'
    // },
    // {
    //     username: 'kevinkho',
    //     password: '321321',
    //     firstname: 'kevin',
    //     lastname: 'kho',
    //     role: 'Faculty'
    // }];

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

        userService
            .findAllUsers()
            .then(renderUsers)
    }

    function createUser() {
        user = getData(0);

        if (user != null) {
            userService.createUser(user).then(renderUsers);
            clear();
        }
    }

    function findAllUsers() {
        $tbody.find("tr:not(.wbdv-hidden)").remove();
        userService.findAllUsers().then(renderUsers);
    }

    function deleteUser(id) {
        userService.deleteUser(id).then(findAllUsers);
    }

    function updateUser() {
        if ($idFld.val() == "") {
            alert("Something went wrong.");
            return;
        }
        user = getData($idFld.val());

        if (user != null) {
            userService.updateUser($idFld.val(), user).then(findAllUsers);
            clear();
        }
        $idFld.val("");
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

    function renderUsers(users) {
        $tbody.empty();

        for (var u in users) {
            renderUser(users[u]);
        }
    }

    function getData(id) {
        var username = $usernameFld.val();
        var pass = $passwordFld.val();
        var fname = $firstNameFld.val();
        var lname = $lastNameFld.val();
        var role = $roleFld.val();

        return new User(id, username, pass, fname, lname, role);
    }

    function clear() {
        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
    }
})()