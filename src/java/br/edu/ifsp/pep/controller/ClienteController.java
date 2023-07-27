package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.ClienteDAO;
import br.edu.ifsp.pep.model.Cliente;
import br.edu.ifsp.pep.util.Util;
import jakarta.ejb.EJB;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ClienteController implements Serializable {

    @EJB
    private ClienteDAO clienteDAO;

    private Cliente cliente = new Cliente();
    private Cliente clienteSelecionado = new Cliente();

    public ClienteController() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public void cadastrar() {
        if (!this.cliente.getNome().equals("")
                && !this.cliente.getCpf().equals("")) {
            clienteDAO.inserir(this.cliente);
            Util.info("Cliente cadastrado com sucesso!");
            this.cliente = new Cliente();
        } else {
            Util.warn("Preencha todos os campos!");
        }

    }

    public void editar() {

        if (!this.clienteSelecionado.getNome().equals("")
                && !this.clienteSelecionado.getCpf().equals("")) {
            clienteDAO.atualizar(this.clienteSelecionado);
            Util.info("Cliente atualizado com sucesso!");
            this.clienteSelecionado = new Cliente();
        } else {
            Util.warn("Preencha todos os campos!");
        }

    }

    public void excluirCliente() {
        if (this.clienteSelecionado != null) {
            clienteDAO.excluir(this.clienteSelecionado);
            Util.info("Cliente excluído com sucesso!");
            this.clienteSelecionado = null;
        } else {
            Util.warn("Selecione um cliente!");
        }

    }

    public List<Cliente> buscarTodos() {
        return clienteDAO.buscarTodos();
    }

    public String redirecionarCadastrarCliente() {
        return "create";
    }

    public String redirecionarEditarCliente() {
        return "edit";
    }

}
