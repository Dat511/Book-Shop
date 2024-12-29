<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleHome.css">
        <link rel="stylesheet" type="text/css" href="css/styleCart.css">
    </head>
    <body>
        <div class="container">
            <header>
                <div class="header-left">
                    <div class="logo">
                        <a href="HomeController">
                            <img src="img/fruitlogo.jpg" alt="Book Shop Logo" />
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
                    <input type="text" class="search-bar" placeholder="Search...">
                    <button class="search-button">Search</button>
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

            <div class="cart-container">
                <div class="product-list">
                    <c:if test="${empty cartItems}">
                        <p>Your cart is empty.</p>
                    </c:if>
                    <c:forEach var="item" items="${cartItems}">
                        <div class="product-item">
                            <div><img src="img/${item.img}" alt="${item.name}"></div>
                            <div class="product-info">
                                <div class="product-name">${item.name}</div>
                                <div class="product-origin">${item.pack}G</div>
                            </div>
                            <div class="product-price">${item.price}đ</div>
                            <div class="product-quantity">
                                <form action="CartController" method="post">
                                    <input type="hidden" name="vegetableId" value="${item.vegetableId}" />
                                    <input type="number" name="quantity" value="${item.pack}" min="1" />
                                    <input type="submit" name="action" value="update" />
                                </form>
                            </div>
                            <div class="product-total">${item.price * item.pack}đ</div>
                            <div class="product-remove">
                                <form action="CartController" method="post">
                                    <input type="hidden" name="vegetableId" value="${item.vegetableId}" />
                                    <input type="submit" name="action" value="remove" />
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="order-summary">
                    <form action="checkout" method="post">
                        <div>Đơn hàng</div>
                        <div>Tạm tính: 
                            <c:set var="subtotal" value="0" />
                            <c:forEach var="item" items="${cartItems}">
                                <c:set var="subtotal" value="${subtotal + (item.price * item.pack)}" />
                            </c:forEach>
                            <span>${subtotal}đ</span>
                        </div>
                        <div class="total">
                            Tổng tiền: 
                            <input type="text" value="${subtotal}" name="total_amount" readonly="true">
                        </div>
                        <button type="submit" class="checkout-button">Thanh toán</button>
                    </form>
                </div>
            </div>

            <footer>
                <p>&copy; 2024 Book Shop. All rights reserved.</p>
                <p>Contact us: <a href="mailto:bookreviewr@fpt.edu.vn">bookreviewr@fpt.edu.vn</a> | Phone: <a href="tel:+840868877813">086 887 7813</a></p>
                <p>Follow us on:
                    <a href="#">Facebook</a> |
                    <a href="#">Instagram</a> |
                    <a href="#">Twitter</a>
                </p>
            </footer>
        </div>
    </body>
</html>
