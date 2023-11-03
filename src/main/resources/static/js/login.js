const wrongPassword = document.getElementById("wrong_password");
const wrongPasswordText = document.getElementById("wrong_password_text");
const nomeEstudante = document.getElementById("nomeEstudante");
const temaNome = document.getElementById("temaNome");
const myModal = document.getElementById('myModal')
const myInput = document.getElementById('myInput')

if (wrongPasswordText.textContent==null || wrongPasswordText.textContent==="") {
    wrongPassword.style.display = 'none';
}
myModal.addEventListener('shown.bs.modal', () => {
    myInput.focus()
})

$(document).ready(function () {
    $('#votoForm').submit(function (event) {
        event.preventDefault();

        var formData = $(this).serialize();
        $.ajax({
            type: 'POST',
            url: '/estudante/criar-votacao',
            data: formData,
            success: function (data) {
                // Handle the success response here
                console.log(data);
            },
            error: function (error) {
                // Handle the error response here
                console.error(error);
            }
        });
    });
});

