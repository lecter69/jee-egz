package controllers;

import config.DBManager;
import entity.Plan;
import entity.PlanPK;
import entity.Uczen;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;


public class PlanBean {
    private Plan plan;
    private Plan staryPlan;
    private int nauczycielID;
    private int uczenID;
    
    public PlanBean() {
        this.inicjujPlan();
    }

    private void inicjujPlan() {
        this.plan = new Plan();
        this.plan.setPlanPK(new PlanPK());
        this.staryPlan = null;
    }
    
    public int getNauczycielID() {
        return nauczycielID;
    }

    public void setNauczycielID(int nauczycielID) {
        this.nauczycielID = nauczycielID;
    }
    
    public int getUczenID() {
        return uczenID;
    }

    public void setUczenID(int uczenID) {
        this.uczenID = uczenID;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String dodaj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        em.persist(plan);
        try {
            em.getTransaction().commit();
        } catch (RollbackException re) {
            this.dodajInformacje("Nie udalo sie dodac - unique!");
            return null;
        }
        finally {
            em.close();
        }
        this.dodajInformacje("Dodano");
        this.inicjujPlan();
        return "/show_plan.xhtml";
    }
    

    public List<Plan> getListaPlan() {
        EntityManager em = DBManager.getManager().createEntityManager();       
        List<Plan> lista = em.createNamedQuery("Plan.findAll").getResultList();        
        em.close();
        return lista;
    }
    
    public List<Plan> getListaPlanNauczyciel() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List<Plan> lista = em.createNamedQuery("Plan.findByNauczyciel").setParameter("nauczyciel", this.getNauczycielID()).getResultList();
        em.close();
        return lista;
    }
    
    public List<Plan> getListaPlanUczen() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List<Plan> lista = em.createNamedQuery("Plan.findByUczen").setParameter("uczen", this.getUczenID()).getResultList();
        em.close();
        
        FacesContext fc = FacesContext.getCurrentInstance();    
        fc.getExternalContext().getSessionMap().remove("uczenID");
        
        return lista;
    }
    
    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s,""));
    }

    public void planListener(ActionEvent ae) {
        String kluczTekst = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("planPK").toString();
        PlanPK klucz = Plan.convertStringAsPlanPK(kluczTekst);
        this.plan = new Plan();
        this.plan.setPlanPK(klucz);
    }

    public String zaladujDoEdycji() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.plan = em.find(Plan.class, plan.getPlanPK());
        this.staryPlan = new Plan(plan.getPlanPK().getUczen(), plan.getPlanPK().getNauczyciel(), plan.getPlanPK().getPrzedmiot());
        this.staryPlan.setDzien(this.plan.getDzien());
        this.staryPlan.setGodzina(this.plan.getGodzina());
        em.close();        
        return "/edit_plan.xhtml";
    }

    public String usun() {       
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        this.plan = em.find(Plan.class, plan.getPlanPK());
        em.remove(this.plan);
        this.inicjujPlan();
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Usunieto");
        return "/show_plan.xhtml";
    }

    public String edytuj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        if (this.plan.equals(this.staryPlan))
            em.merge(this.plan);
        else {
            em.remove(em.find(Plan.class, this.staryPlan.getPlanPK()));
            em.persist(this.plan);
        }
        try {
            em.getTransaction().commit();
        } catch (RollbackException re) {
            this.dodajInformacje("Nie udalo sie zmienic - unique!");
            return null;
        }
        finally {
            em.close();
        }
        this.dodajInformacje("Zmieniono");
        this.inicjujPlan();
        return "/show_plan.xhtml";
    }
}
