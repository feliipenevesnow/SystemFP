package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.VendaDAO;
import br.edu.ifsp.pep.model.Cliente;
import br.edu.ifsp.pep.model.ItemVenda;
import br.edu.ifsp.pep.model.Produto;
import br.edu.ifsp.pep.model.Venda;
import br.edu.ifsp.pep.util.Util;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.glassfish.jaxb.core.Utils;

@Named
@SessionScoped
public class VendaController implements Serializable {

    @EJB
    private VendaDAO vendaDAO;

    @Inject
    private FuncionarioController funcionarioController;

    private Venda venda = new Venda();

    private Produto produtoSelecionado = new Produto();
    private int quantidade;

    private Cliente clienteSelecionado = new Cliente();

    private List<ItemVenda> itens = new ArrayList<>();

    public VendaController() {
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public String redirecionarEfetuarVenda() {
        return "vender";
    }

    public String redirecionarPagamento() {
        return "pagamento";
    }

    public void adicionar() {
        boolean achou = false;

        if (this.produtoSelecionado.getIdproduto() != null) {
            for (ItemVenda item : itens) {
                if (item.getProduto().equals(this.produtoSelecionado)) {
                    item.setQuantidade(item.getQuantidade() + this.quantidade);
                    achou = true;
                }
            }
            if (!achou) {
                ItemVenda item = new ItemVenda();
                item.setProduto(this.produtoSelecionado);
                item.setQuantidade(this.quantidade);
                item.setSubtotal(this.produtoSelecionado.getPreco() * this.quantidade);
                this.itens.add(item);
            }
            this.quantidade = 0;
            this.produtoSelecionado = new Produto();
        }

    }

    public double getTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public String cancelar() {
        this.clienteSelecionado = new Cliente();
        this.produtoSelecionado = new Produto();
        this.itens.clear();
        this.quantidade = 0;
        this.venda = new Venda();
        return "list";
    }

    public String continuar() {
        return "vender";
    }

    public String finalizar() {
        this.venda.setCliente(clienteSelecionado);
        this.venda.setFuncionario(funcionarioController.getFuncionarioLogado());
        this.venda.setData(new Date());
        this.venda.setItemVendaList(itens);
        this.vendaDAO.inserir(venda);

        this.clienteSelecionado = new Cliente();
        this.produtoSelecionado = new Produto();
        this.itens.clear();
        this.quantidade = 0;
        this.venda = new Venda();

        return "list";
    }

    public List<Venda> buscarTodos() {
        return vendaDAO.buscarTodos();
    }

}
