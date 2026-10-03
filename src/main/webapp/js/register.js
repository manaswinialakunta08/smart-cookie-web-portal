
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");

const togglePassword = document.getElementById("togglePassword");

const strengthBar = document.getElementById("strengthBar");
const strengthText = document.getElementById("strengthText");

const passwordMessage = document.getElementById("passwordMessage");


// Show / hide password
togglePassword.addEventListener("click", function () {

    if (password.type === "password") {

        password.type = "text";
        togglePassword.textContent = "🙈";

    } else {

        password.type = "password";
        togglePassword.textContent = "👁";
    }

});


// Password strength
password.addEventListener("input", function () {

    const value = password.value;

    let strength = 0;

    if (value.length >= 8) {
        strength++;
    }

    if (/[A-Z]/.test(value)) {
        strength++;
    }

    if (/[0-9]/.test(value)) {
        strength++;
    }

    if (/[^A-Za-z0-9]/.test(value)) {
        strength++;
    }


    if (value.length === 0) {

        strengthBar.style.width = "0";
        strengthText.textContent = "Enter a password";

    } else if (strength <= 1) {

        strengthBar.style.width = "25%";
        strengthText.textContent = "Weak";

    } else if (strength === 2) {

        strengthBar.style.width = "50%";
        strengthText.textContent = "Fair";

    } else if (strength === 3) {

        strengthBar.style.width = "75%";
        strengthText.textContent = "Good";

    } else {

        strengthBar.style.width = "100%";
        strengthText.textContent = "Strong";
    }

});


// Confirm password
confirmPassword.addEventListener("input", function () {

    if (confirmPassword.value === password.value) {

        passwordMessage.textContent = "Passwords match";
        passwordMessage.style.color = "green";

    } else {

        passwordMessage.textContent = "Passwords do not match";
        passwordMessage.style.color = "red";
    }

});
