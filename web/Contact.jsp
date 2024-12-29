<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contact Us - Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleHome.css">
        <link rel="stylesheet" type="text/css" href="css/styleContact.css">
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
                        <!--<input type="number" name="min">-->
                        <!--<input type="number" name="max">-->
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

            <main>
                <section class="contact-us">
                    <h1>Contact Us</h1>
                    <p>We’d love to hear from you! Whether you have a question about our products, need assistance with an order, or just want to share your feedback, feel free to reach out to us using the form below.</p>

                    <h2>Get in Touch</h2>
                    <form action="ContactController" method="post">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="subject">Subject:</label>
                            <input type="text" id="subject" name="subject" required>
                        </div>
                        <div class="form-group">
                            <label for="message">Message:</label>
                            <textarea id="message" name="message" rows="5" required></textarea>
                        </div>
                        <button type="reset" class="submit-button">Send Message</button>
                    </form>

                    <h2>Other Ways to Reach Us</h2>
                    <ul class="contact-info">
                        <li><strong>Email:</strong> <a href="mailto:bookreviewr@fpt.edu.vn">bookreviewr@fpt.edu.vn</a></li>
                        <li><strong>Phone:</strong> <a href="tel:+840868877813">086 887 7813</a></li>
                        <li><strong>Address:</strong> 123 Fruit Street, Hanoi, Vietnam</li>
                    </ul>

                    <h2>Follow Us on Social Media</h2>
                    <p>Stay connected with us on social media for the latest updates, promotions, and fruit-related tips!</p>
                    <ul class="social-media">
                        <li><a href="#">Facebook</a></li>
                        <li><a href="#">Instagram</a></li>
                        <li><a href="#">Twitter</a></li>
                    </ul>
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
