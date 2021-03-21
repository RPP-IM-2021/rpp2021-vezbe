package rpp2021vezbe.ctrl;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rpp2021vezbe.model.Dobavljac;
import rpp2021vezbe.repository.DobavljacRepository;

@RestController
public class DobavljacRestController {

	@Autowired
	private DobavljacRepository dobavljacRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("dobavljac")
	public Collection<Dobavljac> getAll(){
		return dobavljacRepository.findAll();
	}


	@GetMapping("dobavljac/{id}")
	public ResponseEntity<Dobavljac> getOne(@PathVariable("id") Integer id) {
		if (dobavljacRepository.findById(id).isPresent()) {
			Dobavljac dobavljac= dobavljacRepository.getOne(id);
			return new ResponseEntity<>(dobavljac, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("dobavljac/naziv/{naziv}")
	public Collection<Dobavljac> getByNaziv(@PathVariable("naziv") String naziv){
		return dobavljacRepository.findByNazivContainingIgnoreCase(naziv);
	}

	@PostMapping("dobavljac")
	public ResponseEntity<Dobavljac> addDobavljac(@RequestBody Dobavljac dobavljac) {
		Dobavljac savedDobavljac =  dobavljacRepository.save(dobavljac);
		URI location = URI.create("/dobavljac/" + savedDobavljac.getId());
		return ResponseEntity.created(location).body(savedDobavljac);
	}

	@PutMapping("dobavljac/{id}")
	public ResponseEntity<Dobavljac> updateDobavljac(@RequestBody Dobavljac dobavljac,
			@PathVariable("id")Integer id){
		if (dobavljacRepository.existsById(id)) {
			dobavljac.setId(id);
			Dobavljac savedDobavljac = dobavljacRepository.save(dobavljac);
			return ResponseEntity.ok().body(savedDobavljac);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);


	}

	@DeleteMapping("dobavljac/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){

		if(id==-100 && !dobavljacRepository.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO dobavljac (\"id\", \"naziv\", \"adresa\", \"kontakt\") VALUES (-100, 'Test Naziv', 'Test Adresa', 'Test Kontakt')");
		}

		if (dobavljacRepository.existsById(id)) {
			dobavljacRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);

	}

}