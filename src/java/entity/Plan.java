/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jakub Martin
 */
@Entity
@Table(name = "plan", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nauczyciel", "dzien", "godzina"}),
    @UniqueConstraint(columnNames = {"uczen", "dzien", "godzina"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findByUczen", query = "SELECT p FROM Plan p WHERE p.planPK.uczen = :uczen"),
    @NamedQuery(name = "Plan.findByNauczyciel", query = "SELECT p FROM Plan p WHERE p.planPK.nauczyciel = :nauczyciel"),
    @NamedQuery(name = "Plan.findByPrzedmiot", query = "SELECT p FROM Plan p WHERE p.planPK.przedmiot = :przedmiot"),
    @NamedQuery(name = "Plan.findByDzien", query = "SELECT p FROM Plan p WHERE p.dzien = :dzien"),
    @NamedQuery(name = "Plan.findByGodzina", query = "SELECT p FROM Plan p WHERE p.godzina = :godzina")})
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanPK planPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dzien", nullable = false)
    private int dzien=1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "godzina", nullable = false)
    private float godzina;
    @JoinColumn(name = "przedmiot", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Przedmiot przedmiot1;
    @JoinColumn(name = "nauczyciel", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Nauczyciel nauczyciel1;
    @JoinColumn(name = "uczen", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Uczen uczen1;

    public Plan() {
    }

    public Plan(PlanPK planPK) {
        this.planPK = planPK;
    }

    public Plan(PlanPK planPK, int dzien, float godzina) {
        this.planPK = planPK;   
        this.dzien = dzien;
        this.godzina = godzina;
    }

    public Plan(int uczen, int nauczyciel, int przedmiot) {
        this.planPK = new PlanPK(uczen, nauczyciel, przedmiot);        
    }

    public PlanPK getPlanPK() {
        return planPK;
    }

    public void setPlanPK(PlanPK planPK) {
        this.planPK = planPK;
    }

    public int getDzien() {
        return dzien;
    }

    public void setDzien(int dzien) {
        this.dzien = dzien;
    }

    public float getGodzina() {
        return godzina;
    }

    public void setGodzina(float godzina) {
        this.godzina = godzina;
    }

    public Przedmiot getPrzedmiot1() {
        return przedmiot1;
    }

    public void setPrzedmiot1(Przedmiot przedmiot1) {
        this.przedmiot1 = przedmiot1;
    }

    public Nauczyciel getNauczyciel1() {
        return nauczyciel1;
    }

    public void setNauczyciel1(Nauczyciel nauczyciel1) {
        this.nauczyciel1 = nauczyciel1;
    }

    public Uczen getUczen1() {
        return uczen1;
    }

    public void setUczen1(Uczen uczen1) {
        this.uczen1 = uczen1;
    }
    
    public String getPlanPKAsString() {
        PlanPK klucz = this.getPlanPK();
        return klucz.getUczen()+";"+klucz.getNauczyciel()+";"+klucz.getPrzedmiot();
    }
    
    public static PlanPK convertStringAsPlanPK(String s) {
        String[] czesciKlucza = s.split(";");
        int uczenID = Integer.parseInt(czesciKlucza[0]);
        int nauczycielID = Integer.parseInt(czesciKlucza[1]);
        int przedmiotID = Integer.parseInt(czesciKlucza[2]);        
        PlanPK klucz = new PlanPK(uczenID, nauczycielID, przedmiotID);
        return klucz;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planPK != null ? planPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.planPK == null && other.planPK != null) || (this.planPK != null && !this.planPK.equals(other.planPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Plan[ planPK=" + planPK + " ]";
    }
    
}
