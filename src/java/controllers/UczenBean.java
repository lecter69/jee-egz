/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import config.DBManager;
import entity.Uczen;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;


/**
 *
 * @author k
 */
public class UczenBean {
    private Uczen uczen = new Uczen();

    public Uczen getUczen() {
        return uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }
    
    public void uczenListener(ActionEvent ae) {
        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("uczenID").toString();
        int id = Integer.parseInt(ids);
        this.uczen.setId(id);

    }

     public String edytuj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        em.merge(this.uczen);
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Zmieniono");
        this.uczen = new Uczen();
        return "/show_uczen.xhtml";
    }

    public String zaladujDoEdycji() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.uczen = em.find(Uczen.class, uczen.getId());
        em.close();
        return "/edit_uczen.xhtml";
    }

    public String usun() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        this.uczen = em.find(Uczen.class, uczen.getId());
        em.remove(this.uczen);
        this.uczen = new Uczen();
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Usunieto");
        return "/show_uczen.xhtml";
    }

    public List<Uczen> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List<Uczen> list = em.createNamedQuery("Uczen.findAll").getResultList();
        em.close();
        return list;
    }
    
    public Uczen findUczen(int id) {
        EntityManager em = DBManager.getManager().createEntityManager();
        return em.find(Uczen.class, id);
    }
    
    public String pokazPlan() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.uczen = em.find(Uczen.class, uczen.getId());
        em.close();
        return "/show_plan_uczen.xhtml";
    }
        
    public String dodaj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        uczen.setId(0);
        em.persist(uczen);
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Dodano");
        this.uczen = new Uczen();
        return "/show_uczen.xhtml";
    }

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s,""));
    }
}
