/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jakub Martin
 */
@Entity
@Table(name = "uczen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uczen.findAll", query = "SELECT u FROM Uczen u"),
    @NamedQuery(name = "Uczen.findById", query = "SELECT u FROM Uczen u WHERE u.id = :id"),
    @NamedQuery(name = "Uczen.findByPesel", query = "SELECT u FROM Uczen u WHERE u.pesel = :pesel"),
    @NamedQuery(name = "Uczen.findByImie", query = "SELECT u FROM Uczen u WHERE u.imie = :imie"),
    @NamedQuery(name = "Uczen.findByNazwisko", query = "SELECT u FROM Uczen u WHERE u.nazwisko = :nazwisko")})
public class Uczen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "pesel", nullable = false, length = 11)
    private String pesel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "imie", nullable = false, length = 50)
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nazwisko", nullable = false, length = 50)
    private String nazwisko;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uczen1", fetch = FetchType.EAGER)
    private List<Plan> planList;

    public Uczen() {
    }

    public Uczen(Integer id) {
        this.id = id;
    }

    public Uczen(Integer id, String pesel, String imie, String nazwisko) {
        this.id = id;
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @XmlTransient
    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uczen)) {
            return false;
        }
        Uczen other = (Uczen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Uczen[ id=" + id + " ]";
    }
    
}
