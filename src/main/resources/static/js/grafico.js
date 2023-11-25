// Recupere todas as instâncias de elementos .chart-canvas
const canvases = document.querySelectorAll(".chart-canvas");

canvases.forEach((canvas) => {
    const ctx = canvas.getContext("2d");
    const nomeVotantesNodeList = canvas.parentElement.querySelectorAll(".anuncio");
    const countVotantesNodeList = canvas.parentElement.querySelectorAll(".countVotantes");
    const titulo = canvas.getAttribute("data-titulo");

    const nomeVotantes = Array.from(nomeVotantesNodeList).map((element) => element.textContent);
    const contagemVotos = Array.from(countVotantesNodeList).map((element) => element.textContent);

    // Verifique se há dados antes de criar o gráfico
    if (nomeVotantes.length > 0 && contagemVotos.length > 0) {
        criarGrafico(ctx, nomeVotantes, contagemVotos, titulo);
    }
});

// Função para criar um gráfico
function criarGrafico(ctx, nomeVotantes, contagemVotos, titulo) {
    new Chart(ctx, {
        type: "bar",
        data: {
            labels: nomeVotantes,
            datasets: [
                {
                    label: "",
                    data: contagemVotos,
                    backgroundColor: "#344767",
                    borderWidth: 1,
                },
            ],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
        },
    });
}
