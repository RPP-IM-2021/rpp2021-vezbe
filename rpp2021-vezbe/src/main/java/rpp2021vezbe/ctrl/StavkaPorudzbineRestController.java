package rpp2021vezbe.ctrl;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rpp2021vezbe.model.Porudzbina;
import rpp2021vezbe.model.StavkaPorudzbine;
import rpp2021vezbe.repository.PorudzbinaRepository;
import rpp2021vezbe.repository.StavkaPorudzbineRepository;

@CrossOrigin
@RestController
public class StavkaPorudzbineRestController {

	@Autowired
	private StavkaPorudzbineRepository stavkaPorudzbineRepository;

	@Autowired
	private PorudzbinaRepository porudzbinaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("stavkaPorudzbine")
	public Collection<StavkaPorudzbine> getAll(){
		return stavkaPorudzbineRepository.findAll();
	}

	@GetMapping("stavkaPorudzbine/{id}")
	public ResponseEntity<StavkaPorudzbine> getOne(@PathVariable("id") Integer id){
		if(stavkaPorudzbineRepository.findById(id).isPresent()) {
			StavkaPorudzbine savedStavkaPorudzbine = stavkaPorudzbineRepository.getOne(id);
			return new ResponseEntity<>(savedStavkaPorudzbine, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("stavkeZaPorudzbinu/{id}")
	public Collection<StavkaPorudzbine> getAllForPorudzbina(@PathVariable("id") Integer id){
		if (porudzbinaRepository.findById(id).isPresent()) {
			Optional<Porudzbina> porudzbinaOptional = porudzbinaRepository.findById(id);
			Porudzbina porudzbina = porudzbinaOptional.get();
			return stavkaPorudzbineRepository.findByPorudzbina(porudzbina);
		}

		return new ArrayList<StavkaPorudzbine>();

	}

	@GetMapping("stavkaPorudzbineCena/{cena}")
	public Collection<StavkaPorudzbine> getStavkaPorudzbineWithPriceUnder(
			@PathVariable("cena") BigDecimal cena){
		return stavkaPorudzbineRepository.findByCenaLessThanOrderById(cena);

	}

	@PostMapping("stavkaPorudzbine")
	public ResponseEntity<StavkaPorudzbine> addOne(@RequestBody StavkaPorudzbine stavkaPorudzbine){
		stavkaPorudzbine.setRedniBroj(
				stavkaPorudzbineRepository.nextRBR(stavkaPorudzbine.getPorudzbina().getId()));
		StavkaPorudzbine savedStavkaPorudzbine = stavkaPorudzbineRepository.save(stavkaPorudzbine);
		URI location = URI.create("/stavkaPorudzbine/" + savedStavkaPorudzbine.getId());
		return ResponseEntity.created(location).body(savedStavkaPorudzbine);
	}

	@PutMapping("stavkaPorudzbine/{id}")
	public ResponseEntity<StavkaPorudzbine> update(@RequestBody StavkaPorudzbine stavkaPorudzbine,
			@PathVariable("id") Integer id) {
		if (!stavkaPorudzbineRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		stavkaPorudzbine.setId(id);
		StavkaPorudzbine savedStavkaPruPorudzbine = stavkaPorudzbineRepository.save(stavkaPorudzbine);
		return ResponseEntity.ok().body(savedStavkaPruPorudzbine);
	}

	@DeleteMapping("stavkaPorudzbine/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id){

		if (id == -100 && !stavkaPorudzbineRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO stavka_porudzbine "
					+ "(\"id\", \"redni_broj\", \"kolicina\", \"jedinica_mere\", \"cena\", \"porudzbina\", \"artikl\") "
					+ "VALUES ('-100', '100', '1', 'komad', '100', '1', '1')");
		}

		if(stavkaPorudzbineRepository.existsById(id)) {
			stavkaPorudzbineRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);

	}



}
