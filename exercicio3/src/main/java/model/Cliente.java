package model;

public class Cliente {
	private int id;
	private String nome;
	private int idade;
	private String carro;
	
	public Cliente() {
		this.id = -1;
		this.nome = "";
		this.idade = -1;
		this.carro = "";
	}
	
	public Cliente(int id, String nome, int idade, String carro) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.carro = carro;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public String getCarro() {
		return carro;
	}
	
	public String imprimeLinha() {
		return "Cliente [Id = " + id + ", Nome = " + nome + ", Idade = " + idade + ", Carro = " + carro + "]";
	}

}