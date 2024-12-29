<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book Shop</title>
        <link rel="stylesheet" type="text/css" href="css/styleViewOrder.css">
        <style>
            .container {
                margin: 0 auto;
                max-width: 1280px;
            }
            td {
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="cart-container">
                <div class="product-list">
                    <c:forEach var="item" items="${cartItems}">
                        <div class="product-item">
                            <div><img src="img/${item.vegetable.img}" alt="${item.vegetable.name}"></div>
                            <div class="product-info">
                                <div class="product-name">${item.vegetable.name}</div>
                                <div class="product-origin">${item.quantity}G</div>
                            </div>
                            <div class="product-price">${item.vegetable.price}đ</div>
                            <div class="product-quantity">
                                <input type="hidden" name="vegetableId" value="${item.vegetableId}" />
                                <span>${item.quantity}</span>
                            </div>
                            <div class="product-total">${item.vegetable.price * item.quantity}đ</div>
                            <div class="product-remove">
                                <input type="hidden" name="vegetableId" value="${item.vegetableId}" />
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="order-summary">
                    <h3 style="font-size: 18px">Order detail: </h3>
                    <table>
                        <tbody>
                            <tr>
                                <td>Name: </td>
                                <td>${detail.user.fullname}</td>
                            </tr>
                            <tr>
                                <td>Order date:</td>
                                <td>${fn:substring(detail.orderDate, 0, 10)}</td>
                            </tr>
                            <tr>
                                <td>Status:</td>
                                <td>${detail.status}</td>
                            </tr>
                        </tbody>
                    </table>

                    <div>
                        <c:set var="subtotal" value="0" />
                        <c:forEach var="item" items="${cartItems}">
                            <c:set var="subtotal" value="${subtotal + (item.vegetable.price * item.quantity)}" />
                        </c:forEach>
                    </div>
                    <div class="total">
                        Tổng tiền: 
                        <span>${subtotal}</span>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
