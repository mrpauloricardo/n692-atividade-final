
// Cadastrar Clientes
document.getElementById('formulario__cadastro').addEventListener('submit', function(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const cpf = document.getElementById('cpf').value;
    const celular = document.getElementById('celular').value;
    const email = document.getElementById('email').value;

    fetch(`http://localhost:8000/cadastrar?nome=${nome}&cpf=${cpf}&celular=${celular}&email=${email}`)
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Erro ao cadastrar o usuário.');
            }
        })
        .then(data => {
            document.getElementById('mensagem').innerText = data;
        })
        .catch(error => {
            console.error('Erro', error);
            document.getElementById('mensagem').innerText = 'Erro ao cadastrar o usuário';
        });
});