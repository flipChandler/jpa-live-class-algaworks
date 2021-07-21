package br.com.algaworks.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "configuracao")
public class Configuracao {
	
	@Id
	private Integer id;
	
	@MapsId
	@OneToOne
	private Usuario usuario;
	
	private boolean receberNotificacoes;
	
	private boolean encerraSessaoAutorizada;

	public Configuracao() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isReceberNotificacoes() {
		return receberNotificacoes;
	}

	public void setReceberNotificacoes(boolean receberNotificacoes) {
		this.receberNotificacoes = receberNotificacoes;
	}

	public boolean isEncerraSessaoAutorizada() {
		return encerraSessaoAutorizada;
	}

	public void setEncerraSessaoAutorizada(boolean encerraSessaoAutorizada) {
		this.encerraSessaoAutorizada = encerraSessaoAutorizada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuracao other = (Configuracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
