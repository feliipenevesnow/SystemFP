package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.ProdutoDAO;
import br.edu.ifsp.pep.model.Produto;
import br.edu.ifsp.pep.util.Util;
import jakarta.ejb.EJB;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProdutoController implements Serializable {

    @EJB
    private ProdutoDAO produtoDAO;

    private Produto produto = new Produto();
    private Produto produtoSelecionado = new Produto();

    public ProdutoController() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public void cadastrar() {
        if (!this.produto.getNome().equals("")
                && this.produto.getPreco() > 0 && this.produto.getQuantidade() > 0) {
            produtoDAO.inserir(this.produto);
            Util.info("Produto cadastrado com sucesso!");
            this.produto = new Produto();
        } else {
            Util.warn("Preencha todos os campos!");
        }

    }

    public void editar() {

        if (!this.produtoSelecionado.getNome().equals("")
                && this.produtoSelecionado.getPreco() > 0 && this.produtoSelecionado.getQuantidade() > 0) {
            produtoDAO.atualizar(this.produtoSelecionado);
            this.produtoSelecionado = null;
            Util.info("Produto editado com sucesso!");

        } else {
            Util.warn("Preencha todos os campos!");
        }

    }

    public void excluirProduto() {
        if (this.produtoSelecionado != null) {
            produtoDAO.excluir(this.produtoSelecionado);
            Util.info("Produto excluído com sucesso!");
            this.produtoSelecionado = null;
        } else {
            Util.warn("Selecione um produto!");
        }

    }

    public List<Produto> buscarTodos() {
        return produtoDAO.buscarTodos();
    }

    public String redirecionarCadastrarProduto() {
        return "create";
    }

    public String redirecionarEditarProduto() {
        return "edit";
    }

}
