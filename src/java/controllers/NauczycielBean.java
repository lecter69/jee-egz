/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import config.DBManager;
import entity.Nauczyciel;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;


/**
 *
 * @author k
 */
public class NauczycielBean {
    private Nauczyciel nauczyciel = new Nauczyciel();

    public Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public void nauczycielListener(ActionEvent ae) {
        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nauczycielID").toString();
        int id = Integer.parseInt(ids);
        this.nauczyciel.setId(id);
    }

     public String edytuj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        em.merge(this.nauczyciel);
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Zmieniono");
        this.nauczyciel = new Nauczyciel();
        return "/show_nauczyciel.xhtml";
    }

    public String zaladujDoEdycji() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.nauczyciel = em.find(Nauczyciel.class, nauczyciel.getId());
        em.close();
        return "/edit_nauczyciel.xhtml";
    }

    public String usun() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        this.nauczyciel = em.find(Nauczyciel.class, nauczyciel.getId());
        em.remove(this.nauczyciel);
        this.nauczyciel = new Nauczyciel();
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Usunieto");
        return "/show_nauczyciel.xhtml";
    }

    public List<Nauczyciel> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List<Nauczyciel> list = em.createNamedQuery("Nauczyciel.findAll").getResultList();
        em.close();
        return list;
    }
    
    public Nauczyciel findNauczyciel(int id) {
        EntityManager em = DBManager.getManager().createEntityManager();
        return em.find(Nauczyciel.class, id);
    }
    
    public String pokazPlan() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.nauczyciel = em.find(Nauczyciel.class, nauczyciel.getId());
        em.close();
        return "/show_plan_nauczyciel.xhtml";
    }

    public String dodaj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        nauczyciel.setId(0);
        em.persist(nauczyciel);
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Dodano");
        this.nauczyciel = new Nauczyciel();
        return "/show_nauczyciel.xhtml";
    }

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s,""));
    }
}
