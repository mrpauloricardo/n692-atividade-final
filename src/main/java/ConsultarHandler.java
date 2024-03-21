package main.java;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.Map;

public class ConsultarHandler implements HttpHandler {
    private static Map<String, CadastrarHandler.Usuario> usuarios;

    public ConsultarHandler(Map<String, CadastrarHandler.Usuario> usuarios) {
        ConsultarHandler.usuarios = usuarios;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers headers = exchange.getResponseHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(200, -1);
            return;
        }

        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.startsWith("cpf=")) {
            String cpf = query.split("=")[1];
            CadastrarHandler.Usuario usuario = usuarios.get(cpf);
            if (usuario != null) {
                String response = "Nome: " + usuario.getNome() + "\n"
                                + "CPF: " + usuario.getCpf() + "\n"
                                + "Celular: " + usuario.getCelular() + "\n"
                                + "Email: " + usuario.getEmail();
                sendResponse(exchange, response);
            } else {
                sendResponse(exchange, "Usuário não encontrado");
            }
        } else {
            StringBuilder response = new StringBuilder();
            for (CadastrarHandler.Usuario usuario : usuarios.values()) {
                response.append("Nome: ").append(usuario.getNome()).append("\n")
                        .append("CPF: ").append(usuario.getCpf()).append("\n")
                        .append("Celular: ").append(usuario.getCelular()).append("\n")
                        .append("Email: ").append(usuario.getEmail()).append("\n\n");
            }
            sendResponse(exchange, response.toString());
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        byte[] responseBytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }
}