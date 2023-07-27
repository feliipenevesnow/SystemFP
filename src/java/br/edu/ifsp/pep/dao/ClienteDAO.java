package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ClienteDAO {

    @PersistenceContext(unitName = "estudoPU")
    private EntityManager em;

    public void inserir(Cliente cliente) {
        em.persist(cliente);
    }

    public void atualizar(Cliente cliente) {
        em.merge(cliente);
    }

    public void excluir(Cliente cliente) {
        em.remove(em.merge(cliente));
    }

    public List<Cliente> buscarTodos() {
        return em.createQuery("SELECT e FROM Cliente e",
                Cliente.class)
                .getResultList();
    }

    public Cliente findByCodigo(Integer codigo) {
        return em.find(Cliente.class, codigo);
    }
}
