(function() {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $roleFld;
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

        if (user != null) {
            userService.createUser(user).then(findAllUsers);

            clear();
        }
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
        clone.attr("id", user.id);
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

        console.log(clone)

        $tbody.append(clone);
    }

    function updateUser() {
        user = getData($idFld.val());

        console.log($idFld);

        if (user != null) {
            userService.updateUser($idFld.val(), user).then(findAllUsers);
            clear();
        }
        $idFld.val("");
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function deleteUser(id) {
        userService.deleteUser(id).then(findAllUsers);
    }

    function selectUser(id) {

        $row = $(id);

        $idFld.val(id);

        $usernameFld.val($row.find(".wbdv-username").html());
        $passwordFld.val($row.find(".wbdv-password").html());
        $firstNameFld.val($row.find(".wbdv-first-name").html());
        $lastNameFld.val($row.find(".wbdv-last-name").html());
        $roleFld.val($row.find(".wbdv-role").html()).change();
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

    function renderUsers(users) {
        $tbody.empty();

        for (var u in users) {
            renderUser(users[u]);
        }
    }

    function clear() {
        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
    }
})()