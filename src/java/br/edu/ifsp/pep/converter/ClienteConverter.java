package br.edu.ifsp.pep.converter;

import br.edu.ifsp.pep.dao.ClienteDAO;
import br.edu.ifsp.pep.model.Cliente;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter<Cliente>{

    @Override
    public Cliente getAsObject(FacesContext context, 
            UIComponent component, String value) {
        
        if (value == null || value.isBlank()) {
            return null;
        }
        
        ClienteDAO clienteDAO = CDI.current()
                .select(ClienteDAO.class).get();
        return clienteDAO.findByCodigo(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, 
            UIComponent component, Cliente value) {
        
        return value.getIdcliente().toString();
    }
    
}
