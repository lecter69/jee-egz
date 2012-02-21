/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jakub Martin
 */
@Embeddable
public class PlanPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "uczen", nullable = false)
    private int uczen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nauczyciel", nullable = false)
    private int nauczyciel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "przedmiot", nullable = false)
    private int przedmiot;

    public PlanPK() {
    }

    public PlanPK(int uczen, int nauczyciel, int przedmiot) {
        this.uczen = uczen;
        this.nauczyciel = nauczyciel;
        this.przedmiot = przedmiot;
    }

    public int getUczen() {
        return uczen;
    }

    public void setUczen(int uczen) {
        this.uczen = uczen;
    }

    public int getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(int nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public int getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(int przedmiot) {
        this.przedmiot = przedmiot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) uczen;
        hash += (int) nauczyciel;
        hash += (int) przedmiot;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanPK)) {
            return false;
        }
        PlanPK other = (PlanPK) object;
        if (this.uczen != other.uczen) {
            return false;
        }
        if (this.nauczyciel != other.nauczyciel) {
            return false;
        }
        if (this.przedmiot != other.przedmiot) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PlanPK[ uczen=" + uczen + ", nauczyciel=" + nauczyciel + ", przedmiot=" + przedmiot + " ]";
    }
    
}
