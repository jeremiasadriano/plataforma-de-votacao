const wrongPassword = document.getElementById("wrong_password");
const wrongPasswordText = document.getElementById("wrong_password_text");

if (wrongPasswordText.textContent==null || wrongPasswordText.textContent==="") {
    wrongPassword.style.display = 'none';
}