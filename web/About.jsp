<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>About Us - Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleHome.css">
        <link rel="stylesheet" type="text/css" href="css/styleAbout.css">
    </head>
    <body>
        <div class="container">
            <!-- Header Section -->
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

            <main>
                <section class="about-us" style="background-color: white; padding: 20px; margin: 20px 0">
                    <h1>About Our Book Shop</h1>
                    <p>Welcome to <strong>Book Shop</strong>, your one-stop shop for the freshest and most delicious fruits available. We are passionate about providing our customers with high-quality, organic, and sustainably sourced fruits that are both healthy and flavorful.</p>
                    
                    <h2>Our Mission</h2>
                    <p>At Book Shop, our mission is to promote a healthy lifestyle by offering a wide variety of fresh fruits that are packed with vitamins and nutrients. We believe in the power of natural, wholesome foods to enhance well-being and support a vibrant life. That's why we are committed to sourcing our products from trusted farmers who adhere to sustainable and ethical farming practices.</p>
                    
                    <h2>Our Products</h2>
                    <p>We offer an extensive selection of fruits, including:</p>
                    <ul>
                        <li>Seasonal fruits such as apples, oranges, and berries.</li>
                        <li>Exotic fruits like mangoes, dragon fruits, and kiwis.</li>
                        <li>Organic fruits grown without the use of synthetic pesticides or fertilizers.</li>
                        <li>Locally sourced fruits from farms in Vietnam.</li>
                    </ul>
                    <p>Whether you're looking for a healthy snack, ingredients for a fresh fruit salad, or a special treat, Book Shop has something for everyone. Our products are carefully selected and delivered with care to ensure maximum freshness and quality.</p>
                    
                    <h2>Why Choose Us?</h2>
                    <p>At Book Shop, we stand out from the competition because:</p>
                    <ul>
                        <li>We prioritize quality and freshness in every fruit we sell.</li>
                        <li>Our products are 100% natural, with no added preservatives or artificial flavors.</li>
                        <li>We are committed to sustainability and work closely with farmers to support eco-friendly farming methods.</li>
                        <li>We offer competitive prices without compromising on quality.</li>
                        <li>Our customer service team is dedicated to ensuring your shopping experience is smooth and satisfying.</li>
                    </ul>

                    <h2>Join Our Community</h2>
                    <p>We believe that healthy eating should be accessible to everyone. That's why we are constantly looking for new ways to serve our customers better, from expanding our product range to offering convenient delivery options. Follow us on social media to stay updated on our latest products, special offers, and tips for a healthy lifestyle.</p>
                    <p>Thank you for choosing Book Shop. We look forward to being a part of your journey to better health!</p>
                </section>
            </main>

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
