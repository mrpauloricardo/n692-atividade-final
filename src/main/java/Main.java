package main.java;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, CadastrarHandler.Usuario> usuarios = new HashMap<>();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/cadastrar", new CadastrarHandler(usuarios));
        server.createContext("/consultar", new ConsultarHandler(usuarios));
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado na porta 8000");
    }    
}