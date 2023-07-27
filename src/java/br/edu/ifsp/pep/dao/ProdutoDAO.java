package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoDAO {

    @PersistenceContext(unitName = "estudoPU")
    private EntityManager em;

    public void inserir(Produto produto) {
        em.persist(produto);
    }

    public void atualizar(Produto produto) {
        em.merge(produto);
    }

    public void excluir(Produto produto) {
        em.remove(em.merge(produto));
    }

    public List<Produto> buscarTodos() {
        return em.createQuery("SELECT e FROM Produto e",
                Produto.class)
                .getResultList();
    }

    public Produto findByCodigo(Integer codigo) {
        return em.find(Produto.class, codigo);
    }
}
