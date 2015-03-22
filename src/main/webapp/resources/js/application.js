var dataTable;
var mathenticate;
$(document).ready(function () {

    dataTable = $("#dataTable").DataTable({
        ajax: {
            url: "/users",
            dataSrc: ""
        },
        "columns": [
            {"data": "username"},
            {"data": "name"},
            {"data": "surname"},
            {"data": "phone"},
            {"data": "id"}
        ],
        "columnDefs": [
            {
                "targets": [4],
                "visible": false,
                "searchable": false
            }
        ]
    });

    $(document).ajaxStart(function() {
        NProgress.start();
    });

    $(document).ajaxComplete(function() {
        NProgress.done();
    });

    //JS captcha
    mathenticate = {
        bounds: {
            lower: 5,
            upper: 50
        },
        first: 0,
        second: 0,
        generate: function () {
            this.first = Math.floor(Math.random() * this.bounds.lower) + 1;
            this.second = Math.floor(Math.random() * this.bounds.upper) + 1;
        },
        show: function () {
            return this.first + ' + ' + this.second;
        },
        solve: function () {
            return this.first + this.second;
        }
    };
    mathenticate.generate();

    var $auth = $('<input type="text" name="auth"/>');

    //client side validation
    $("input,select,textarea").not("[type=submit]").jqBootstrapValidation();

    $("#btnNewUser").on("click", function () {
        $("#userForm #userId").val("")
        $("#userForm")[0].reset();
        $auth.show();
        mathenticate.generate();
        $auth.attr('placeholder', mathenticate.show()).insertAfter('input[id="phone"]');
        $("#modalForm").modal();
    });

    $("#btnEditUser").on("click", function () {
        $auth.hide();
        $("#userForm")[0].reset();
        var selectedUser = dataTable.rows('.selected').data()[0];
        $("#userForm #username").val(selectedUser.username);
        $("#userForm #name").val(selectedUser.name);
        $("#userForm #surname").val(selectedUser.surname);
        $("#userForm #phone").val(selectedUser.phone);
        $("#userForm #userId").val(selectedUser.id);
        $("#modalForm").modal();
    });

    //TODO: refactor
    $('form').on('submit', function (e) {
        var userId = $("#userForm #userId").val() == "" ? null : $("#userForm #userId").val();

        if ($("#userForm #userId").val() == "") {

            if ($auth.val() == mathenticate.solve()) {

                $.ajax('/users/add', {//insert
                    type: 'POST',
                    data: {
                        username: $("#userForm #username").val(),
                        name: $("#userForm #name").val(),
                        surname: $("#userForm #surname").val(),
                        phone: $("#userForm #phone").val(),
                        id: userId
                    },
                    success: function (data) {
                        console.log(data);
                        dataTable.ajax.reload(null, false);
                        $("#modalForm").modal("hide")
                    },
                    error: function (errormessage) {
                        alert("User exists!");//TODO: use bootstrap alert
                    }
                });

            } else {
                alert('Captcha wrong');
            }


        } else {
            $.ajax('/users/' + userId, {//update
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify({
                    username: $("#userForm #username").val(),
                    name: $("#userForm #name").val(),
                    surname: $("#userForm #surname").val(),
                    phone: $("#userForm #phone").val(),
                    id: userId
                }),
                success: function (data) {
                    console.log(data);
                    dataTable.ajax.reload(null, false);
                    $("#modalForm").modal("hide")
                }
            });
        }
        e.preventDefault();
    });

    $("#btnDeleteUser").on("click", function () {
        if (confirm("Are sure?")) {
            var userId = dataTable.row('.selected').data().id;
            $.ajax('/users/' + userId, {
                type: 'DELETE',
                success: function (data) {
                    console.log(data);
                    dataTable.ajax.reload(null, false);
                    $("#modalForm").modal("hide")
                }
            })
        }
    });

    $('#dataTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $("#btnEditUser").addClass('disabled');
            $("#btnDeleteUser").addClass('disabled');
            $(this).removeClass('selected');
        }
        else {
            dataTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            $("#btnEditUser").removeClass('disabled');
            $("#btnDeleteUser").removeClass('disabled');
        }
    });

});
