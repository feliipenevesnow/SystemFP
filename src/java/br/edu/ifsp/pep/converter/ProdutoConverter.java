package br.edu.ifsp.pep.converter;

import br.edu.ifsp.pep.dao.ProdutoDAO;
import br.edu.ifsp.pep.model.Produto;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto>{

    @Override
    public Produto getAsObject(FacesContext context, 
            UIComponent component, String value) {
        
        if (value == null || value.isBlank()) {
            return null;
        }
        
        ProdutoDAO clienteDAO = CDI.current()
                .select(ProdutoDAO.class).get();
        return clienteDAO.findByCodigo(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, 
            UIComponent component, Produto value) {
        
        return value.getIdproduto().toString();
    }
    
}
