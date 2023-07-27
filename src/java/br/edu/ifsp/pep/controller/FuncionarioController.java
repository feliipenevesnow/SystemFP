package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.FuncionarioDAO;
import br.edu.ifsp.pep.model.Funcionario;
import br.edu.ifsp.pep.util.Util;
import jakarta.ejb.EJB;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class FuncionarioController implements Serializable {

    @EJB
    private FuncionarioDAO funcionarioDAO;

    private Funcionario funcionario = new Funcionario();
    private Funcionario funcionarioSelecionado = new Funcionario();
    private Funcionario funcionarioLogado;

    public FuncionarioController() {
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
        this.funcionarioSelecionado = funcionarioSelecionado;
    }

    public String autenticar() {
        funcionarioLogado = funcionarioDAO
                .buscarPorLoginSenha(funcionario.getEmail(), funcionario.getSenha());
        if (funcionarioLogado != null) {
            Util.info("Usuário autenticado.");
            return "dashboard";
        } else {
            Util.error("Login ou Senha inválidos.");
        }
        this.funcionario = new Funcionario();
        return null;
    }

    public void cadastrar() {
        if (!this.funcionario.getNome().equals("")
                && !this.funcionario.getEmail().equals("")
                && !this.funcionario.getSenha().equals("")
                && !this.funcionario.getCargo().equals("")
                && !this.funcionario.getCpf().equals("")) {
            funcionarioDAO.inserir(this.funcionario);
            Util.info("Funcionario cadastrado com sucesso!");
            this.funcionario = new Funcionario();
        } else {
            Util.warn("Preencha todos os campos!");
        }

    }

    public void editar() {

        if (!this.funcionarioSelecionado.getNome().equals("")
                && !this.funcionarioSelecionado.getEmail().equals("")
                && !this.funcionarioSelecionado.getSenha().equals("")
                && !this.funcionarioSelecionado.getCargo().equals("")
                && !this.funcionarioSelecionado.getCpf().equals("")) {
            funcionarioDAO.atualizar(this.funcionarioSelecionado);
            Util.info("Funcionario atualizado com sucesso!");
            this.funcionarioSelecionado = new Funcionario();
        } else {
            Util.warn("Preencha todos os campos!");
        }

    }

    public void excluirFuncionario() {
        if (this.funcionarioSelecionado != null) {
            funcionarioDAO.excluir(this.funcionarioSelecionado);
            Util.info("Funcionario excluído com sucesso!");
            this.funcionarioSelecionado = null;
        } else {
            Util.warn("Selecione um funcionario!");
        }

    }

    public List<Funcionario> buscarTodos() {
        return funcionarioDAO.buscarTodos();
    }

    public String redirecionarCadastrarFuncionario() {
        return "create";
    }

    public String redirecionarEditarFuncionario() {
        return "edit";
    }

    public String sair() {
        this.funcionarioLogado = null;
        return "index";
    }

}
