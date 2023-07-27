/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


public class ItemVendaPK implements Serializable {

    private int venda;

    private int produto;

    public ItemVendaPK() {
    }

    public ItemVendaPK(int vendaIdvenda, int produtoIdproduto) {
        this.venda = vendaIdvenda;
        this.produto = produtoIdproduto;
    }

    public int getVendaIdvenda() {
        return venda;
    }

    public void setVendaIdvenda(int vendaIdvenda) {
        this.venda = vendaIdvenda;
    }

    public int getProdutoIdproduto() {
        return produto;
    }

    public void setProdutoIdproduto(int produtoIdproduto) {
        this.produto = produtoIdproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) venda;
        hash += (int) produto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVendaPK)) {
            return false;
        }
        ItemVendaPK other = (ItemVendaPK) object;
        if (this.venda != other.venda) {
            return false;
        }
        if (this.produto != other.produto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifsp.pep.model.ItemVendaPK[ vendaIdvenda=" + venda + ", produtoIdproduto=" + produto + " ]";
    }
    
}
