window.onload = function () {
    setTimeout(function () {
        const notification = document.getElementById('successNotification');
        if (notification) {
            notification.classList.add('fade-out');
            setTimeout(function () {
                notification.style.display = 'none';
            }, 500);
        }
    }, 3000); // 3 seconds
};

function confirmDeletion(productName) {
    return confirm("Are you sure you want to delete " + productName + "?");
}

