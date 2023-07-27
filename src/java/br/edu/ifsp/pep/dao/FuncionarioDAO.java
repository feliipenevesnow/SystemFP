package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Funcionario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class FuncionarioDAO {

    @PersistenceContext(unitName = "estudoPU")
    private EntityManager em;

    public void inserir(Funcionario funcionario) {
        em.persist(funcionario);
    }

    public void atualizar(Funcionario funcionario) {
        em.merge(funcionario);
    }

    public void excluir(Funcionario funcionario) {
        em.remove(em.merge(funcionario));
    }

    public List<Funcionario> buscarTodos() {
        return em.createQuery("SELECT e FROM Funcionario e",
                Funcionario.class)
                .getResultList();
    }

    public Funcionario findByCodigo(Integer codigo) {
        return em.find(Funcionario.class, codigo);
    }

    public Funcionario buscarPorLoginSenha(String email, String senha) {
        TypedQuery<Funcionario> query = em.createQuery(
                "Select p FROM Funcionario p WHERE p.email = :email AND p.senha = :senha",
                Funcionario.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
