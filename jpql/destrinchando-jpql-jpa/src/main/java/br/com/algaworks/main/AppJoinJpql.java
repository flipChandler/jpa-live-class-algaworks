package br.com.algaworks.main;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.algaworks.model.Configuracao;
import br.com.algaworks.model.Usuario;

public class AppJoinJpql {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//fazendoJoin(entityManager);
		//fazendoLeftJoin(entityManager);
		//carregamentoComJoinFetch(entityManager);
		//filtrandoRegistrosConcat(entityManager);
		//filtrandoRegistrosBetween(entityManager);
		//filtrandoRegistrosOperadoresLogicos(entityManager);
		//filtrandoRegistrosOperadorIn(entityManager);
		//ordenandoResultados(entityManager);
		paginandoResultados(entityManager);
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public static void fazendoJoin(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u JOIN u.dominio d"; // equivalente a u.dominio_id = d.id;
		String jpql2 = "SELECT u FROM Usuario u JOIN u.dominio d WHERE d.id = 1"; // 
		String sql = "SELECT u.* FROM Usuario u JOIN Dominio d ON u.dominio_id = d.id";
		
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		usuarios.forEach(u -> System.out.println(u.getNome() + ", " + u.getSenha()));
	}

	public static void fazendoLeftJoin(EntityManager entityManager) {
		String jpql = "SELECT u, c FROM Usuario u LEFT JOIN u.configuracao c";
		
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> lista = query.getResultList();
		lista.forEach(array -> {
			// array[0] = Usuario
			// array[1] = Configuracao
			String out = ((Usuario) array[0]).getNome() + " ";
			if (array[1] == null) {
				out += ", NULL";
			}else {
				out += ((Configuracao) array[1]).getId();
			}
			System.out.println(out);
		});
	}

	public static void carregamentoComJoinFetch(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u JOIN FETCH u.configuracao c JOIN FETCH u.dominio d"; // JOIN FETCH FAZ MENOS QUERIES
		
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}

	// LIKE, IS NULL, IS EMPTY, BETWEEN, <, >, >=, <=, ==, <>
	public static void filtrandoRegistros(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u WHERE u.nome LIKE :nomeUsuario"; // JEITO 1
		
		TypedQuery<Usuario> query = entityManager
				.createQuery(jpql, Usuario.class)
				.setParameter("nomeUsuario", "Cal%");
		
		List<Usuario> usuarios = query.getResultList();
		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}

	public static void filtrandoRegistrosConcat(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u WHERE u.nome LIKE CONCAT(:nomeUsuario, '%')"; // JEITO 2
		
		TypedQuery<Usuario> query = entityManager
				.createQuery(jpql, Usuario.class)		
				.setParameter("nomeUsuario", "Cal");
		List<Usuario> usuarios = query.getResultList();		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}
	
	public static void filtrandoRegistrosBetween(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u WHERE u.ultimoAcesso BETWEEN :ontem and :hoje";
		
		TypedQuery<Usuario> query = entityManager
				.createQuery(jpql, Usuario.class)		
				.setParameter("ontem", LocalDateTime.now().minusDays(1))
				.setParameter("hoje", LocalDateTime.now());
		List<Usuario> usuarios = query.getResultList();		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}
	
	public static void filtrandoRegistrosOperadoresLogicos(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u WHERE (u.ultimoAcesso > :ontem and u.ultimoAcesso < :hoje) "
				+ "or u.ultimoAcesso IS NULL";
		
		TypedQuery<Usuario> query = entityManager
				.createQuery(jpql, Usuario.class)		
				.setParameter("ontem", LocalDateTime.now().minusDays(1))
				.setParameter("hoje", LocalDateTime.now());
		List<Usuario> usuarios = query.getResultList();		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}
	
	public static void filtrandoRegistrosOperadorIn(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u WHERE u.id IN (:ids)";				
		
		TypedQuery<Usuario> query = entityManager
				.createQuery(jpql, Usuario.class)		
				.setParameter("ids", Arrays.asList(1, 4));
		List<Usuario> usuarios = query.getResultList();		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}
	
	public static void ordenandoResultados(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u ORDER BY u.nome";				
		
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);		
		List<Usuario> usuarios = query.getResultList();		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}
	
	public static void paginandoResultados(EntityManager entityManager) {
		String jpql = "SELECT u FROM Usuario u";				
		
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class)
				.setFirstResult(1)
				.setMaxResults(2);
		List<Usuario> usuarios = query.getResultList();		
		usuarios.forEach(usuario -> System.out.println(usuario.getId() + ", " + usuario.getNome()));
	}
	
	
	
	// IS NULL = SELECT u FROM Usuario u WHERE u.senha IS NULL
	// IS EMPTY = SELECT d FROM Dominio d WHERE d.usuarios IS EMPTY
	
	
	
	
	
	
}
