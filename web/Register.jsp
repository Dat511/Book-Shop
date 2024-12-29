<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register - Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleRegister.css">
    </head>
    <body>
        <a href="HomeController" class="home-button">Home</a>
        <div class="register-container">
            <h1>Create an Account</h1>
            <form action="register" method="post">
                <div class="input-group">
                    <label for="full-name">Full Name</label>
                    <input type="text" id="full-name" name="fullName" required>
                </div>
                <div class="input-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="input-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone" required>
                </div>
                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="input-group">
                    <label for="confirm-password">Confirm Password</label>
                    <input type="password" id="confirm-password" name="confirmPassword" required>
                </div>
                <div class="input-group">
                    <button type="submit" class="btn">Register</button>
                </div>
                <c:if test="${not empty errors}">
                    <div class="error-message">
                        <p>${error}</p>
                    </div>
                </c:if>
            </form>
            <p>Already have an account? <a href="LoginController">Login here</a>.</p>
        </div>
    </body>
</html>
