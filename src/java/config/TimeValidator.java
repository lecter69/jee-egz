package config;

import entity.Uczen;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;


public class TimeValidator implements Validator {
    public void validate(FacesContext ctx, UIComponent component, Object value) {
        //if (!(value instanceof String))
        //    throw new ValidatorException(new FacesMessage("Przekazana wartosc nie jest lancuchem znakow! "));
        String pesel = value.toString();
        if (!pesel.matches("([01]?[0-9]|2[0-3]).[0-5][0-9]?"))
            throw new ValidatorException(new FacesMessage("Niepoprawny format godziny xx.xx!"));        
    }
}
