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
                <section id="products" class="content-section active">
                    <h2 style="margin-left: 250px">Product Management</h2>

                    <div class="country-buttons">
                        <a href="AdminController" class="btn btn-country">All</a>
                        <c:forEach var="c" items="${listC}">
                            <a href="AdminController?cid=${c.getId()}" class="btn btn-country">${c.getName()}</a>
                        </c:forEach>
                    </div>
                    <div class="add-product">
                        <a href="addProduct.jsp">Add New Product</a>
                    </div>
                    <div class="product-container">
                        <c:forEach var="product" items="${listP}">
                            <div class="product-item">
                                <img src="img/${product.img}" alt="Product 1">
                                <div class="product-name">${product.name}</div>
                                <div class="product-price">${product.price}</div>
                                <div class="product-actions">
                                    <a href="updatepro?id=${product.vegetableId}" class="edit">Edit</a>
                                    <a href="deletepro?id=${product.vegetableId}" class="delete" onclick="return confirmDeletion('${product.name}')">Delete</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </main>
        </div>
        <script src="js/script.js"></script>
    </body>
</html>
