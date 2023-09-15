package service;

import java.util.HashMap;
import dao.ClienteDAO;
import model.Cliente;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class ClienteService {

	private static ClienteDAO clienteDAO = new ClienteDAO();


	public ClienteService() {
		makeForm();
	}
	
	public static ModelAndView mostrarClientes(Request req, Response res) {
    	HashMap<String, String> model = new HashMap<>();
    	model.put("tabela", makeForm());
    	
    	return new ModelAndView(model, "/view/home.vm");
    }
	
	public static ModelAndView mostrarInsert(Request req, Response res) {
    	HashMap<String, String> model = new HashMap<>();
    	model.put("tabela", makeForm());
    	
    	return new ModelAndView(model, "/view/insert.vm");
    }
	
	public static ModelAndView mostrarEdit(Request req, Response res) {
    	HashMap<String, String> model = new HashMap<>();
    	model.put("tabela", makeForm());
    	
    	return new ModelAndView(model, "/view/edit.vm");
    }
	
	public static ModelAndView mostrarDelete(Request req, Response res) {
    	HashMap<String, String> model = new HashMap<>();
    	model.put("tabela", makeForm());
    	
    	return new ModelAndView(model, "/view/delete.vm");
    }

	public static String makeForm() {
		// Aqui, você pode criar a lógica para obter os clientes do banco de dados
		// e preencher a tabela no HTML com os dados dos clientes.
		Cliente[] clientes = clienteDAO.getCliente();
		StringBuilder tableRows = new StringBuilder();;

		for (Cliente cliente : clientes) {
			tableRows.append("<tr>");
			tableRows.append("<td>").append(cliente.getId()).append("</td>");
			tableRows.append("<td>").append(cliente.getNome()).append("</td>");
			tableRows.append("<td>").append(cliente.getIdade()).append("</td>");
			tableRows.append("<td>").append(cliente.getCarro()).append("</td>");
			tableRows.append("<td>");
			tableRows.append("<a href='/edit/").append(cliente.getId()).append("'>Editar</a> | ");
			tableRows.append("<a href='/delete/").append(cliente.getId()).append("'>Excluir</a>");
			tableRows.append("</td>");
			tableRows.append("</tr>");
		}

		String tabelaHtml = tableRows.toString();
		return tabelaHtml;
	}

//	public Object atualizaClientes(Request request, Response response) {
//		makeForm(); // Atualiza a tabela de clientes
//		return new VelocityTemplateEngine().render(new ModelAndView(null, "templates/clientes.vm"));
//	}
//
	public Object insert(Request request, Response response) {
		Cliente[] clientes = clienteDAO.getCliente();
		int id = clientes.length;
		String nome = request.queryParams("nome");
		int idade = Integer.parseInt(request.queryParams("idade"));
		String carro = request.queryParams("carro");

		Cliente cliente = new Cliente(id, nome, idade, carro);
		if(clienteDAO.inserirCliente(cliente) == true) {
			response.status(201); // 201 Created
		} else {
			response.status(404); // 404 Not found
		}

		response.redirect("/");
		return mostrarClientes(request, response);
	}
	
	public Object edit(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		String nome = request.queryParams("editNome");
		int idade = Integer.parseInt(request.queryParams("editIdade"));
		String carro = request.queryParams("editCarro");

		Cliente cliente = new Cliente(id, nome, idade, carro);
		if(clienteDAO.atualizarCliente(cliente, id) == true) {
			response.status(201); // 201 Created
		} else {
			response.status(404); // 404 Not found
		}

		response.redirect("/");
		return mostrarClientes(request, response);
	}
	
	public Object delete(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		if(clienteDAO.excluirCliente(id) == true) {
			response.status(201); // 201 Created
		} else {
			response.status(404); // 404 Not found
		}

		response.redirect("/");
		return mostrarClientes(request, response);
	}

}