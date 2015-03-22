<%--
  Created by IntelliJ IDEA.
  User: buraq
  Date: 19.03.2015
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>USERS</title>
</head>
<jsp:include page="template/staticFiles.jsp"/>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <button id="btnNewUser" class="btn btn-default">New User</button>
            <button id="btnEditUser" class="btn btn-default disabled">Edit User</button>
            <button id="btnDeleteUser" class="btn btn-danger disabled">Delete User</button>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table id="dataTable">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone</th>
                    <th>id</th>

                </tr>
                </thead>

                <tfoot>
                <tr>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone</th>
                    <th>id</th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>

<!-- modal dialog -->
<div id="modalForm" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">User Details</h4>
            </div>
            <div class="modal-body">
                <form id="userForm">
                    <div class="form-group controls">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" placeholder="Enter username" required minlength="3" maxlength="30" >
                    </div>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" placeholder="Enter name" required minlength="3" maxlength="30">
                    </div>
                    <div class="form-group">
                        <label for="surname">Surname</label>
                        <input type="text" class="form-control" id="surname" placeholder="Enter surname" required minlength="2" maxlength="30">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone number</label>
                        <input type="text" class="form-control" data-mask="(999)-9999999" id="phone" placeholder="Enter phone number" required>
                    </div>
                    <input type="hidden" id="userId">

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-primary" value="Save"/>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>
