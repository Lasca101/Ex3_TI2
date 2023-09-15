package app;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import service.ClienteService;


public class Aplicacao {
	
	private static ClienteService clienteService = new ClienteService();
	
    public static void main(String[] args) {
    	VelocityTemplateEngine engine = new VelocityTemplateEngine();
    	
        port(6789);
        
        staticFiles.location("/public");
        
        get("/", (request, response) -> ClienteService.mostrarClientes(request, response), engine);

        get("/insert", (request, response) -> ClienteService.mostrarInsert(request, response), engine);
        
        post("/insert", (request, response) -> clienteService.insert(request, response));

        get("/edit/:id", (request, response) -> ClienteService.mostrarEdit(request, response), engine);
        
        post("/edit/:id", (request, response) -> clienteService.edit(request, response));

        get("/delete/:id", (request, response) -> ClienteService.mostrarDelete(request, response), engine);
        
        post("/delete/:id", (request, response) -> clienteService.delete(request, response));

    }
    
    
}