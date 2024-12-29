<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleLogin.css">
    </head>
    <body>
        <a href="HomeController" class="home-button">Home</a>
        <div class="login-container">
            <h1>Login to Book Shop</h1>
            <form action="LoginController" method="post">
                <div class="input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="input-group">
                    <button type="submit" class="btn">Login</button>
                </div>
                <c:if test="${not empty error}">
                    <div class="error-message">
                        <p>${error}</p>
                    </div>
                </c:if>
            </form>
            <p>Don't have an account? <a href="register">Register here</a>.</p>
        </div>
    </body>
</html>
