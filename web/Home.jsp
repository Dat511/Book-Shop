<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleHome.css">
    </head>
    <body>
        <div class="container">
            <header>
                <div class="header-left">
                    <div class="logo">
                        <a href="HomeController">
                            <img src="img/fruitlogo.jpg" alt="Book Shop Logo">
                        </a>
                    </div>

                </div>
                <nav>
                    <ul>
                        <li><a href="HomeController">Home</a></li>
                        <li><a href="CartController">Cart</a></li>
                        <li><a href="About.jsp">About</a></li>
                        <li><a href="Contact.jsp">Contact</a></li>
                    </ul>
                </nav>
                <div class="search-login">
                    <form action="SearchController" method="post">
                        <input type="text" class="search-bar" name="search" placeholder="Search...">
                        <input type="text" name="cid" value="${cid}" hidden>
<%--                        <input type="number" name="min">--%>
<%--                        <input type="number" name="max">--%>
                        <button class="search-button">Search</button>
                    </form>

                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <span class="user-name">Welcome, ${sessionScope.user.fullname}!</span>
                            <a href="LogoutController" class="logout-button">Logout</a>
                        </c:when>
                        <c:otherwise>
                            <a href="Login.jsp" class="login-button">Login</a>
                        </c:otherwise>
                    </c:choose>
                </div>

            </header>

            <div class="country-buttons">
                <a href="HomeController" class="btn btn-country">All</a>
                <c:forEach var="c" items="${listC}">
                    <a href="CategoryController?cid=${c.getId()}" class="btn btn-country">${c.getName()}</a>
                </c:forEach>
            </div>

            <section class="product-list">
                <c:forEach var="v" items="${listP}">
                    <div class="product-item">
                        <a href="DetailController?pid=${v.vegetableId}" style="text-decoration: none">
                            <img src="img/${v.img}" alt="product-img">
                            <h3>${v.name}</h3>
                            <p>Quantity: ${v.pack}</p>
                            <p class="price">${v.price} VNĐ</p>
                        </a>
                    </div>
                </c:forEach>
            </section>

            <footer>
                <p>&copy; 2024 Book Shop. All rights reserved.</p>
                <p>Contact us: <a href="mailto:bookreviewr@fpt.edu.vn">bookreviewr@fpt.edu.vn</a> | Phone: <a href="tel:+840868877813">086 887 7813</a></p>
                <p>Follow us on:
                    <a href="#">Facebook</a> |
                    <a href="#">Instagram</a> |
                    <a href="#">Twitter</a>
                </p>
            </footer>


    </body>
</html>
