package rpp2021vezbe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the porudzbina database table.
 *
 */
@Entity
@NamedQuery(name="Porudzbina.findAll", query="SELECT p FROM Porudzbina p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Porudzbina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORUDZBINA_ID_GENERATOR", sequenceName="PORUDZBINA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORUDZBINA_ID_GENERATOR")
	private Integer id;

	/*
	 * @Temporal je potrebno samo za verzije jave pre 1.8 (Java 8)
	 */

	//@Temporal(TemporalType.DATE)
	private Date datum;

	//@Temporal(TemporalType.DATE)
	private Date isporuceno;

	private BigDecimal iznos;

	private Boolean placeno;

	//bi-directional many-to-one association to Dobavljac
	@ManyToOne
	@JoinColumn(name="dobavljac")
	private Dobavljac dobavljac;

	//bi-directional many-to-one association to StavkaPorudzbine
	@OneToMany(mappedBy="porudzbina", cascade = {CascadeType.ALL})
	@JsonIgnore
	private List<StavkaPorudzbine> stavkaPorudzbines;

	public Porudzbina() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getIsporuceno() {
		return this.isporuceno;
	}

	public void setIsporuceno(Date isporuceno) {
		this.isporuceno = isporuceno;
	}

	public BigDecimal getIznos() {
		return this.iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public Boolean getPlaceno() {
		return this.placeno;
	}

	public void setPlaceno(Boolean placeno) {
		this.placeno = placeno;
	}

	public Dobavljac getDobavljac() {
		return this.dobavljac;
	}

	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
	}

	public List<StavkaPorudzbine> getStavkaPorudzbines() {
		return this.stavkaPorudzbines;
	}

	public void setStavkaPorudzbines(List<StavkaPorudzbine> stavkaPorudzbines) {
		this.stavkaPorudzbines = stavkaPorudzbines;
	}

	public StavkaPorudzbine addStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) {
		getStavkaPorudzbines().add(stavkaPorudzbine);
		stavkaPorudzbine.setPorudzbina(this);

		return stavkaPorudzbine;
	}

	public StavkaPorudzbine removeStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) {
		getStavkaPorudzbines().remove(stavkaPorudzbine);
		stavkaPorudzbine.setPorudzbina(null);

		return stavkaPorudzbine;
	}

}