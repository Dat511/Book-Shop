<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard - Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleAdmin.css">
        <style>
            a {
                text-decoration: none;
                color: inherit;
            }

            a:visited {
                color: white;
            }

            .content-section {
                display: none; /* Hide all sections by default */
            }

            .content-section.active {
                display: block; /* Show the active section */
            }
        </style>
    </head>
    <body>
        <div class="admin-container">
            <aside class="sidebar">
                <h2><a href="AdminController">Admin Dashboard</a></h2>
                <nav>
                    <ul>
                        <li><a href="AdminController?product" >Product Management</a></li>
                        <li><a href="AdminController?order" >Order Management</a></li>
                        <li><a href="AdminController?user">User Management</a></li>
                    </ul>
                </nav>
            </aside>

            <main class="main-content">
                <c:if test="${not empty successMessage}">
                    <div class="notification" id="successNotification">
                        <p>${successMessage}</p>
                    </div>
                    <c:remove var="successMessage" scope="session"/>
                </c:if>
                <section id="users" class="content-section active">
                    <h2 style="margin-left: 250px">User Management</h2>

                    <table border="1" cellpadding="10" cellspacing="0" style="margin-left: 250px;">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <c:if test="${user.role}">
                                        <td>Admin</td>
                                    </c:if>
                                    <c:if test="${user.role == false}">
                                        <td>User</td>
                                    </c:if>
                                    <td>
                                        <a href="UpdateUser?id=${user.id}" class="btn btn-primary">Edit</a>
                                        <a href="DeleteUser?id=${user.id}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
            </main>
        </div>
        <script src="js/script.js"></script>
    </body>
</html>
