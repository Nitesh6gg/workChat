document.addEventListener('DOMContentLoaded', function() {
    // Handle form submission for login
    var loginForm = document.getElementById('loginForm');

    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the default form submission

            // Get user input values
            var email = document.getElementById('email').value;
            var password = document.getElementById('password').value;

            // Store the email in localStorage
            localStorage.setItem('userEmail', email);

            // Redirect to the message.html page
            window.location.href = '/message';
        });
    }
});
