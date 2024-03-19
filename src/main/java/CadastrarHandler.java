package main.java;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.HashMap;
import java.util.Map;

public class CadastrarHandler implements HttpHandler {
    private static Map<String, Usuario> usuarios = new HashMap<>();

    public CadastrarHandler(Map<String, Usuario> usuarios) {
        CadastrarHandler.usuarios = usuarios;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers headers = exchange.getResponseHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        
        String query = exchange.getRequestURI().getQuery();
        String[] params = query.split("&");
        String nome = params[0].split("=")[1];
        String cpf = params[1].split("=")[1];
        String celular = params[2].split("=")[1];
        String email = params[3].split("=")[1];

        usuarios.put(cpf, new Usuario(nome, cpf, celular, email));
        sendResponse(exchange, "Usuário cadastrado com sucesso");
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        byte[] responseBytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();   
    }

    static class Usuario {
        private String nome;
        private String cpf;
        private String celular;
        private String email;

        public Usuario(String nome, String cpf, String celular, String email) {
            this.nome = nome;
            this.cpf = cpf;
            this.celular = celular;
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public String getCpf() {
            return cpf;
        }

        public String getCelular() {
            return celular;
        }

        public String getEmail() {
            return email;
        }
    }
}