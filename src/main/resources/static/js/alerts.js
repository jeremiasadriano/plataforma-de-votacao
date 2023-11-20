$(document).ready(function () {
    $('#exampleModal').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget); // Botão que acionou o modal
        const estudanteId = button.data('id'); // Extrai o ID do atributo data-id

        // Define o valor do campo de input escondido no modal
        $('#idInput').val(estudanteId);
    });
});
