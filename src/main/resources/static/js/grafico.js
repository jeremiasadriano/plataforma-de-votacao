const ctx = document.getElementById("vote");
const nomeVotantesNodeList = document.querySelectorAll(".anuncio");
const countVotantesNodeList = document.querySelectorAll(".countVotantes");
const titulo = document.getElementById("titulo").textContent;

const nomeVotantes = Array.from(nomeVotantesNodeList).map(element => element.textContent);
const contagemVotos = Array.from(countVotantesNodeList).map(element => element.textContent);

new Chart(ctx, {
    type: "bar",
    data: {
        labels: nomeVotantes,
        datasets: [
            {
                label: titulo,
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