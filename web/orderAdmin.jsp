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
                display: none; 
            }

            .content-section.active {
                display: block;
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
                <section id="orders" class="content-section active">
                    <h2 style="margin-left: 250px">Order Management</h2>
                    <table border="1" cellpadding="10" cellspacing="0" style="margin-left: 250px;">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Customer Name</th>
                                <th>Order Date</th>
                                <th>Status</th>
                                <th>Total</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="o" items="${listOfOrder}">
                                <tr>
                                    <td>${o.orderId}</td>
                                    <td>${o.user.fullname}</td>
                                    <td>${o.orderDate}</td>
                                    <td>${o.status}</td>
                                    <td>${o.total}</td>
                                    <td>
                                        <a href="viewOrder?id=${o.orderId}" class="btn btn-info">View</a>
                                        <a href="UpdateOrder?action=accept&id=${o.orderId}" class="btn btn-success">Accept</a>
                                        <a href="UpdateOrder?action=delete&id=${o.orderId}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this order?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>

                </tbody>
                </table>
                </section>
            </main>
        </div>
        <script src="js/script.js"></script>
    </body>
</html>
