// Consultar Clientes
document.getElementById('formulario__consulta').addEventListener('submit', function(event) {
    event.preventDefault();

    const cpf = document.getElementById('cpfConsulta').value;

    fetch(`http://localhost:8000/consultar?cpf=${cpf}`)
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Erro ao consultar o usuário.');
            }
        })
        .then(data => {
            document.getElementById('mensagemConsulta').innerText = data;
        })
        .catch(error => {
            console.error('Erro', error);
            document.getElementById('mensagemConsulta').innerText = 'Erro ao consultar o usuário';
        });
});