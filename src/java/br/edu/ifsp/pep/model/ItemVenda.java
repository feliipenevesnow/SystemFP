/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 *
 * @author felii
 */
@Entity
@Table(name = "item_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemVenda.findAll", query = "SELECT i FROM ItemVenda i")})

@IdClass(ItemVendaPK.class)
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @JoinColumn(name = "produto_idproduto", referencedColumnName = "idproduto", nullable = false)
    @ManyToOne
    private Produto produto;
    @Id
    @JoinColumn(name = "venda_idvenda", referencedColumnName = "idvenda", nullable = false)
    @ManyToOne
    private Venda venda;
    @Basic(optional = false)
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    public ItemVenda() {
    }

    public ItemVenda(int quantidade, double subtotal) {
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.produto);
        hash = 97 * hash + Objects.hashCode(this.venda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemVenda other = (ItemVenda) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return Objects.equals(this.venda, other.venda);
    }

}
