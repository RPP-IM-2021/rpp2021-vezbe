package rpp2021vezbe.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the dobavljac database table.
 *
 */
@Entity
@NamedQuery(name="Dobavljac.findAll", query="SELECT d FROM Dobavljac d")
public class Dobavljac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOBAVLJAC_ID_GENERATOR", sequenceName="DOBAVLJAC_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOBAVLJAC_ID_GENERATOR")
	private Integer id;

	private String adresa;

	private String kontakt;

	private String naziv;

	//bi-directional many-to-one association to Porudzbina
	@OneToMany(mappedBy="dobavljac")
	@JsonIgnore
	private List<Porudzbina> porudzbinas;

	public Dobavljac() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getKontakt() {
		return this.kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Porudzbina> getPorudzbinas() {
		return this.porudzbinas;
	}

	public void setPorudzbinas(List<Porudzbina> porudzbinas) {
		this.porudzbinas = porudzbinas;
	}

	public Porudzbina addPorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().add(porudzbina);
		porudzbina.setDobavljac(this);

		return porudzbina;
	}

	public Porudzbina removePorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().remove(porudzbina);
		porudzbina.setDobavljac(null);

		return porudzbina;
	}

}