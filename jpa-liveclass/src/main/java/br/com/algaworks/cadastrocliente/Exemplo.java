package br.com.algaworks.cadastrocliente;

import br.com.algaworks.cadastrocliente.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Exemplo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Clientes-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Cliente cliente = entityManager.find(Cliente.class, 1);
//        System.out.println(cliente.getNome());

        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();
        Cliente cliente4 = new Cliente();

        cliente1.setNome("Autopeças Estrada");
        cliente2.setNome("Arcos Dourados");
        cliente3.setNome("Bar do Zé Ratinho");
        cliente4.setNome("Açougue do Chico Tripa");

        // insert
//        entityManager.getTransaction().begin();
//        entityManager.persist(cliente1);
//        entityManager.persist(cliente2);
//        entityManager.persist(cliente3);
//        entityManager.persist(cliente4);
//        entityManager.getTransaction().commit();

        //delete | no caso o cliente3 | buscou pelo id 3 e setou no cliente5
        // importante o entityManager recuperar esse valor para alocá-lo em sua memória e depois segui pra exclusão
        Cliente cliente5 = entityManager.find(Cliente.class, 3);
//        entityManager.getTransaction().begin();
//        entityManager.remove(cliente5);
//        entityManager.getTransaction().commit();

        //update1 | no caso o cliente1 | buscou pelo id 1 e setou no cliente6
        Cliente cliente6 = entityManager.find(Cliente.class, 1);
        entityManager.getTransaction().begin();
        cliente6.setNome("Bar do Zé Lito"); // só de setar o novo nome aqui já altera, pq já está sendo gerenciado pelo entityManager
        entityManager.getTransaction().commit();

        //update2 | no caso o cliente4 | buscou pelo id 4 e setou no cliente7
        //uma atualização de um objeto que não estava sendo gerenciado pelo entityManager | veio de uma aplicação web
        Cliente cliente7 = new Cliente();
        cliente7.setId(4);
        cliente7.setNome("Hotel Miconos");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente7);
        entityManager.getTransaction().commit();



        entityManager.close();
        entityManagerFactory.close();
    }
}
