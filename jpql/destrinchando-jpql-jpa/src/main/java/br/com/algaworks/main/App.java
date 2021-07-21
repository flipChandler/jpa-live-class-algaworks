package br.com.algaworks.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.algaworks.dto.UsuarioDTO;
import br.com.algaworks.model.Dominio;
import br.com.algaworks.model.Usuario;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		primeiraConsulta(entityManager);
		//escolhendoRetorno(entityManager);
		//fazendoProjecoes(entityManager);
		//fazendoProjecoesDTO(entityManager);
		//passandoParametros(entityManager);
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	// GERA O BD
	public static void primeiraConsulta(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u";
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}
	
	// COM WHERE NÃO GERA O BD
	public static void primeirasConsultaWhere(EntityManager entityManager) {		
		String jpqlSingle = "SELECT u from Usuario u where u.id = 1";
		TypedQuery<Usuario> querySingle = entityManager.createQuery(jpqlSingle, Usuario.class);
		Usuario single = querySingle.getSingleResult();
		System.out.println(single.getId() + ", " + single.getNome());
	}
	
	public static void escolhendoRetorno(EntityManager entityManager) {
		String jpql = "SELECT u.dominio FROM Usuario u WHERE u.id = 3";
		TypedQuery<Dominio> query = entityManager.createQuery(jpql, Dominio.class);
		Dominio dominio = query.getSingleResult();
		System.out.println(dominio.getId() + ", " + dominio.getNome());
		
		String jpql2 = "SELECT u.nome FROM Usuario u";
		TypedQuery<String> query2 = entityManager.createQuery(jpql2, String.class);
		List<String> nomes = query2.getResultList();
		nomes.forEach(nome -> System.out.println(nome));
	}
	
	
	public static void fazendoProjecoes(EntityManager entityManager) {
		String vetor = "SELECT id, login, nome from Usuario";
		TypedQuery<Object[]> query = entityManager.createQuery(vetor, Object[].class);
		List<Object[]> lista = query.getResultList();
		lista.forEach(l -> System.out.println(String.format("%s, %s, %s", l)));
	}
	
	public static void fazendoProjecoesDTO(EntityManager entityManager) {
		String jpql = "SELECT new br.com.algaworks.dto.UsuarioDTO(id, login, nome) "
				+ "FROM Usuario";
		
		TypedQuery<UsuarioDTO> query = entityManager.createQuery(jpql, UsuarioDTO.class);
		
		List<UsuarioDTO> lista = query.getResultList();
		lista.forEach(u -> System.out.println(u.getId() + ", " + u.getNome() + ", " + u.getLogin()));
	}
	
	public static void passandoParametros(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u WHERE u.id = :idUsuario";
		
		TypedQuery<Usuario> query = entityManager.
				createQuery(jpql, Usuario.class)
				.setParameter("idUsuario", 2);
		
		Usuario usuario = query.getSingleResult();
		System.out.println(usuario.getId() + ", " + usuario.getNome());
		
		String jpql2 = "SELECT u FROM Usuario u WHERE u.login = :loginUsuario";
		TypedQuery<Usuario> query2 = entityManager.createQuery(jpql2, Usuario.class);
		query2.setParameter("loginUsuario", "eli");
		
		Usuario usuario2 = query2.getSingleResult();
		System.out.println(usuario2.getNome() + ", " + usuario2.getSenha());
	}

}
