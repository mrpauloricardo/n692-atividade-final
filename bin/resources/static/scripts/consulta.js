document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8000/consultar')
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Erro ao consultar os usuários.');
            }
        })
        .then(data => {
            if (data.trim() === "") {
                document.getElementById('mensagem__consulta').innerText = "Nenhum usuário cadastrado.";
                document.getElementById('tabela__singular').style.display = "none";
                document.getElementById('tabela__completa').style.display = "none";
            } else {
                document.getElementById('tabela__singular').style.display = "none";
                document.getElementById('mensagem__consulta').innerText = "";
                document.getElementById('tabela__completa').style.display = "";

                document.getElementById('tabela__completa__corpo').innerHTML = "";

                const linhas = data.split('\n\n');
                linhas.forEach(linha => {
                    const colunas = linha.split('\n');
                    const row = document.createElement('tr');
                    colunas.forEach(coluna => {
                        const cell = document.createElement('td');
                        cell.textContent = coluna.split(': ')[1];
                        row.appendChild(cell);
                    });
                    document.getElementById('tabela__completa__corpo').appendChild(row);
                });
            }
        })
        .catch(error => {
            console.error('Error', error);
            document.getElementById('mensagem__consulta').innerText = 'Erro ao consultar os usuários';
        });
});

document.getElementById('formulario__consulta').addEventListener('submit', function (event) {
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
            if (data.trim() === "Usuário não encontrado") {
                document.getElementById('mensagem__consulta').innerText = "Usuário não encontrado";
                document.getElementById('tabela__singular').style.display = "none";
                document.getElementById('tabela__completa').style.display = "";
            } else {
                document.getElementById('mensagem__consulta').innerText = "";
                document.getElementById('tabela__completa').style.display = "none";
                document.getElementById('tabela__singular').style.display = "";
    
                const colunas = data.split('\n');
                const corpoResultado = document.getElementById('tabela__singular__corpo');
                corpoResultado.innerHTML = "";
    
                const row = document.createElement('tr');
                colunas.forEach(coluna => {
                    const cell = document.createElement('td');
                    cell.textContent = coluna.split(': ')[1];
                    row.appendChild(cell);
                });
                corpoResultado.appendChild(row);
            }
        })
        .catch(error => {
            console.error('Erro', error);
            document.getElementById('mensagem__consulta').innerText = 'Erro ao consultar o usuário.';
        });
});