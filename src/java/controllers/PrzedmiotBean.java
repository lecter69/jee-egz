/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import config.DBManager;
import entity.Przedmiot;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;


/**
 *
 * @author k
 */
public class PrzedmiotBean {
    private Przedmiot przedmiot = new Przedmiot();

    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    public void przedmiotListener(ActionEvent ae) {
        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("przedmiotID").toString();
        int id = Integer.parseInt(ids);
        this.przedmiot.setId(id);

    }

     public String edytuj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        em.merge(this.przedmiot);
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Zmieniono");
        this.przedmiot = new Przedmiot();
        return "/show_przedmiot.xhtml";
    }

    public String zaladujDoEdycji() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.przedmiot = em.find(Przedmiot.class, przedmiot.getId());
        em.close();
        return "/edit_przedmiot.xhtml";
    }

    public String usun() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        this.przedmiot = em.find(Przedmiot.class, przedmiot.getId());
        em.remove(this.przedmiot);
        this.przedmiot = new Przedmiot();
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Usunieto");
        return "/show_przedmiot.xhtml";
    }

    public List<Przedmiot> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List<Przedmiot> list = em.createNamedQuery("Przedmiot.findAll").getResultList();
        em.close();
        return list;
    }

    public Przedmiot findPrzedmiot(int id) {
        EntityManager em = DBManager.getManager().createEntityManager();
        return em.find(Przedmiot.class, id);
    }
    
    public String dodaj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        przedmiot.setId(0);
        em.persist(przedmiot);
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Dodano");
        this.przedmiot = new Przedmiot();
        return "/show_przedmiot.xhtml";
    }

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s,""));
    }
}
