<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard - Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleAddProduct.css">
    </head>
    <body>
        <div class="admin-container">
            <div class="header">
                <h2>Admin Panel</h2>
                <nav>
                    <ul>
                        <li><a href="AdminController">Products Management</a></li>
                        <li><a href="AdminController">Orders Management</a></li>
                        <li><a href="AdminController">User Management</a></li>
                    </ul>
                </nav>
            </div>

            <div class="main-content">
                <h2>Edit User</h2>
                <div class="content-section active">
                    <form method="post" action="UpdateUser">
                        <input type="text"  name="id" value="${user.id}" hidden>
                        <div class="form-group">
                            <label for="name">User name:</label>
                            <input type="text" id="name" name="name" value="${user.username}">
                        </div>

                        <div class="form-group">
                            <label for="fullname">Full Name:</label>
                            <input type="text" id="fullname" name="fullname" value="${user.fullname}">
                        </div>
                        
                        <div class="form-group">
                            <label for="pass">Password:</label>
                            <input type="text" id="pass" name="pass" value="${user.password}">
                        </div>

                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" id="email" name="email" value="${user.email}">
                        </div>

                        <div class="form-group">
                            <label for="phone">Phone number:</label>
                            <input type="text" id="phone" name="phone" value="${user.phoneNumber}">
                        </div>

                        <div class="form-group" >
                            <label for="role">Role:</label><br>
                            
                        </div>

                        <input type="submit" value="Edit User" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

