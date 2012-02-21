package config;

import entity.Uczen;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;


public class UczenPeselValidator implements Validator {
    public void validate(FacesContext ctx, UIComponent component, Object value) {
        if (!(value instanceof String))
            throw new ValidatorException(new FacesMessage("Przekazana wartosc nie jest lancuchem znakow!"));
        String pesel = (String)value;
        if (!pesel.matches("[0-9]{11}"))
            throw new ValidatorException(new FacesMessage("Niepoprawny format numeru PESEL!"));
        EntityManager em = DBManager.getManager().createEntityManager();
        List<Uczen> list = em.createNamedQuery("Uczen.findByPesel").setParameter("pesel", pesel).getResultList();
        em.close();
        if (list.size()>0)
            throw new ValidatorException(new FacesMessage("Wprowadzony nr PESEL istnieje w bazie!"));
    }
}
