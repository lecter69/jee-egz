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
@Table(name = "nauczyciel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nauczyciel.findAll", query = "SELECT n FROM Nauczyciel n"),
    @NamedQuery(name = "Nauczyciel.findById", query = "SELECT n FROM Nauczyciel n WHERE n.id = :id"),
    @NamedQuery(name = "Nauczyciel.findByPesel", query = "SELECT n FROM Nauczyciel n WHERE n.pesel = :pesel"),
    @NamedQuery(name = "Nauczyciel.findByImie", query = "SELECT n FROM Nauczyciel n WHERE n.imie = :imie"),
    @NamedQuery(name = "Nauczyciel.findByNazwisko", query = "SELECT n FROM Nauczyciel n WHERE n.nazwisko = :nazwisko")})
public class Nauczyciel implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nauczyciel1", fetch = FetchType.EAGER)
    private List<Plan> planList;

    public Nauczyciel() {
    }

    public Nauczyciel(Integer id) {
        this.id = id;
    }

    public Nauczyciel(Integer id, String pesel, String imie, String nazwisko) {
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
        if (!(object instanceof Nauczyciel)) {
            return false;
        }
        Nauczyciel other = (Nauczyciel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Nauczyciel[ id=" + id + " ]";
    }
    
}
