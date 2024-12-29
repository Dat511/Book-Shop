<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib prefix="c"
                                                                 uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleHome.css" />
        <link rel="stylesheet" type="text/css" href="css/styleProduct.css" />
    </head>
    <body>
        <div class="container">
            <!-- Header Section -->
            <header style="margin-bottom: 20px">
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
                        <li><a href="#">Contact</a></li>
                    </ul>
                </nav>
                <div class="search-login">
                    <input
                        type="text"
                        class="search-bar"
                        placeholder="Search..."
                        />
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

            <div class="product-container">
                <div class="product-image">
                    <img src="img/${detail.img}" alt="${detail.name}" />
                </div>
                <div class="product-info">
                    <div class="product-name">${detail.name}</div>
                    <div class="product-origin">
                        <span class="detail-label">Origin: </span>
                        ${detail.origin}
                    </div>
                    <div class="product-price">Price: ${detail.price}đ</div>
                    <div class="product-price">Instock: ${detail.pack}</div>
                    <div class="custom-input-container">
                        <form action="CartController" method="post">
                            <input type="hidden" name="action" value="add" />
                            <input type="hidden" name="vegetableId" value="${detail.vegetableId}" />
                            <input type="hidden" name="name" value="${detail.name}" />
                            <input type="hidden" name="price" value="${detail.price}" />
                            <input type="hidden" name="origin" value="${detail.origin}" />
                            <input type="hidden" name="img" value="${detail.img}" />
                            <input type="hidden" name="pack" value="${detail.pack}" />
                            <c:choose>
                                <c:when test="${detail.pack > 0}">
                                    <label for="quantity">Quantity: </label>
                                    <input
                                        type="number"
                                        class="quantity-input"
                                        name="quantity"
                                        value="1"
                                        min="1"
                                        max="${detail.pack}"
                                        placeholder="Enter quantity"
                                        id="quantity"
                                        />
                                    <button type="submit" class="add-to-cart">Add to cart</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="add-to-cart outstock">Out of stock</button>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                </div>
            </div>
            <div class="tab-container">
                <div class="tab active" onclick="openTab(event, 'description')">Description</div>
                <div class="tab" onclick="openTab(event, 'reviews')">Reviews</div>
                <div class="tab" onclick="openTab(event, 'specs')">Specifications</div>
            </div>

            <div id="description" class="tab-content active">
                <p>This product is a state-of-the-art fruit processor designed to make your culinary tasks easier and more efficient. With cutting-edge technology and a sleek design, it's the perfect addition to any modern kitchen.</p>
                <ul>
                    <li>Elegant design with a natural touch</li>
                    <li>High-quality materials for durability</li>
                    <li>Energy-efficient and eco-friendly</li>
                    <li>Available in multiple colors to match your kitchen decor</li>
                </ul>
                <h3>Key Features</h3>
                <p>Our fruit processor is packed with features that set it apart from the competition:</p>
                <ul>
                    <li><strong>Smart Technology:</strong> Integrates seamlessly with your kitchen appliances.</li>
                    <li><strong>Easy to Use:</strong> Intuitive controls make it simple for anyone to operate.</li>
                    <li><strong>Portable:</strong> Compact and lightweight, it's perfect for outdoor picnics.</li>
                    <li><strong>Customizable:</strong> Tailor the settings to suit your specific fruit processing needs.</li>
                </ul>
                <h3>What's Included</h3>
                <p>In the box, you will find:</p>
                <ul>
                    <li>The Fruit Processor</li>
                    <li>Charging Cable</li>
                    <li>User Manual</li>
                    <li>Warranty Card</li>
                </ul>
            </div>
        </div>
        <div id="reviews" class="tab-content">
            <div class="review">
                <h3>John Apple <span class="rating">★★★★☆</span></h3>
                <p>"This fruit processor has completely changed the way I prepare my meals. The quality is top-notch, and the performance is reliable. I highly recommend it!"</p>
            </div>
            <div class="review">
                <h3>Jane Berry <span class="rating">★★★☆☆</span></h3>
                <p>"Overall, it's a good product, but I had some issues with the setup. The customer service was helpful, but the process could have been smoother."</p>
            </div>
            <div class="review">
                <h3>Emily Peach <span class="rating">★★★★★</span></h3>
                <p>"Absolutely love this fruit processor! It's easy to use and works perfectly with all my kitchen gadgets. I would buy it again without hesitation."</p>
            </div>
            <div class="review">
                <h3>Michael Grape <span class="rating">★★★★☆</span></h3>
                <p>"Great product, but a bit on the pricey side. That said, you get what you pay for, and this processor delivers."</p>
            </div>
        </div>

        <div id="specs" class="tab-content">
            <h2>Fruit Processor Specifications</h2>
            <table>
                <tr>
                    <th>Feature</th>
                    <th>Details</th>
                </tr>
                <tr>
                    <td>Dimensions</td>
                    <td>5.5 x 2.8 x 1.2 inches</td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td>8.5 ounces</td>
                </tr>
                <tr>
                    <td>Compatibility</td>
                    <td>Works with all major fruit types</td>
                </tr>
                <tr>
                    <td>Color </td>
                    <td>Green</td>
                </tr>
                <tr>
                    <td>Warranty</td>
                    <td>2-Months Limited Warranty</td>
                </tr>
            </table>
        </div>
        <!-- Footer Section -->
        <footer>
            <p>&copy; 2024 Book Shop. All rights reserved.</p>
            <p>
                Contact us:
                <a href="mailto:bookreviewr@fpt.edu.vn"
                   >bookreviewr@fpt.edu.vn</a
                >
                | Phone: <a href="tel:+840868877813">086 887 7813</a>
            </p>
            <p>
                Follow us on: <a href="#">Facebook</a> |
                <a href="#">Instagram</a> |
                <a href="#">Twitter</a>
            </p>
        </footer>
    </div>
</body>
<script>
    function openTab(evt, tabName) {
        // Get all elements with class="tab-content" and hide them
        var tabcontent = document.getElementsByClassName("tab-content");
        for (var i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Get all elements with class="tab" and remove the class "active"
        var tablinks = document.getElementsByClassName("tab");
        for (var i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    // Set the default tab to be open on page load
    document.addEventListener('DOMContentLoaded', function () {
        document.getElementById("description").style.display = "block";
    });
</script>
</html>
