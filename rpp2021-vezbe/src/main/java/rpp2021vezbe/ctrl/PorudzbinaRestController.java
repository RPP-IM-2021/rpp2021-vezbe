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

import io.swagger.annotations.ApiOperation;
import rpp2021vezbe.model.Porudzbina;
import rpp2021vezbe.repository.PorudzbinaRepository;

@RestController
public class PorudzbinaRestController {

	@Autowired
	private PorudzbinaRepository porudzbinaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Returns collection of all Porudzbina from database")
	@GetMapping("porudzbina")
	public Collection<Porudzbina> getPorudzbine(){
		return porudzbinaRepository.findAll();
	}

	@ApiOperation(value = "Returns Porudzbina with id that was forwarded as path variable")
	@GetMapping("porudzbina/{id}")
	public ResponseEntity<Porudzbina> getPorudzbina(@PathVariable("id") Integer id){
		if (porudzbinaRepository.findById(id).isPresent()) {
			Porudzbina porudzbina = porudzbinaRepository.getOne(id);
			return new ResponseEntity<>(porudzbina, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	@GetMapping("porudzbinePlacene")
	public Collection<Porudzbina> getPaidPorudzbine(){
		return porudzbinaRepository.findByPlacenoTrue();

	}

	@PostMapping("porudzbina")
	public ResponseEntity<Porudzbina> addOne(@RequestBody Porudzbina porudzbina){
		Porudzbina savedPorudzbina = porudzbinaRepository.save(porudzbina);
		URI location = URI.create("/porudzbina/" + savedPorudzbina.getId());
		return ResponseEntity.created(location).body(savedPorudzbina);
	}

	@PutMapping("porudzbina/{id}")
	public ResponseEntity<Porudzbina> update(@PathVariable("id") Integer id,
			@RequestBody Porudzbina porudzbina){
		if(porudzbinaRepository.existsById(id)) {
			porudzbina.setId(id);
			Porudzbina savedPorudzbina = porudzbinaRepository.save(porudzbina);
			return ResponseEntity.ok().body(porudzbina);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("porudzbina/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){

		if(id == -100 && !porudzbinaRepository.existsById(-100)) {

			jdbcTemplate.execute("INSERT INTO porudzbina "
					+ "(\"id\", \"dobavljac\", \"placeno\", \"iznos\", \"isporuceno\", \"datum\") "
					+ "VALUES ('-100', '1', 'true', '1000', "
					+ "to_date('29.03.2021.', 'dd.mm.yyyy'), to_date('29.03.2021.', 'dd.mm.yyyy'))");
		}

		if (porudzbinaRepository.existsById(id)) {
			porudzbinaRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);



	}

}
