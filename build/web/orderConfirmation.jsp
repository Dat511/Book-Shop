<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="css/styleConfirmation.css"> <!-- Include your CSS file -->
</head>
<body>
    <div class="container">
        <h1>Order Confirmation</h1>

        <c:if test="${not empty order}">
            <h2>Order Details</h2>
            <p><strong>Order ID:</strong> ${order.orderId}</p>
            <p><strong>Date:</strong> ${order.orderDate}</p>
            <p><strong>Status:</strong> ${order.status}</p>
            <p><strong>Total Amount:</strong> ${order.total} VND</p>

            <h3>Items Ordered</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${orderDetails}">
                        <tr>
                            <td>${detail.vegetable.name}</td>
                            <td>${detail.quantity}</td>
                            <td>${detail.vegetable.price} VND</td>
                            <td>${detail.quantity * detail.vegetable.price} VND</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <a href="HomeController">Continue Shopping</a>
    </div>
</body>
</html>
