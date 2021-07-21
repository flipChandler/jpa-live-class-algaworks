package br.com.algaworks.dto;

public class UsuarioDTO {
	
	private Integer id;
	
	private String login;
	
	private String nome;

	public UsuarioDTO(Integer id, String login, String nome) {
		super();
		this.id = id;
		this.login = login;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
