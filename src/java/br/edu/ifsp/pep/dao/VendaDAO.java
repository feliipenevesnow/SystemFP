package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Venda;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VendaDAO {

    @PersistenceContext(unitName = "estudoPU")
    private EntityManager em;

    public void inserir(Venda venda) {
        em.persist(venda);
    }

    public void atualizar(Venda venda) {
        em.merge(venda);
    }

    public void excluir(Venda venda) {
        em.remove(em.merge(venda));
    }

    public List<Venda> buscarTodos() {
        return em.createQuery("SELECT e FROM Venda e",
                Venda.class)
                .getResultList();
    }

    public Venda findByCodigo(Integer codigo) {
        return em.find(Venda.class, codigo);
    }
}
