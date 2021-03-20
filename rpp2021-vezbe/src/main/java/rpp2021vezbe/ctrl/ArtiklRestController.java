package rpp2021vezbe.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rpp2021vezbe.model.Artikl;
import rpp2021vezbe.repository.ArtiklRepository;

@RestController
public class ArtiklRestController {

	/*
	 * Anotacija @Autowired se može primeniti nad varijablama instace, setter metodama i
	 * konstruktorima. Označava da je neophodno injektovati zavisni objekat. Prilikom
	 * pokretanja aplikacije IoC kontejner prolazi kroz kompletan kod tražeči anotacije
	 * koje označavaju da je potrebno kreirati objekte. Upotrebom @Autowired anotacije
	 * stavljeno je do znanja da je potrebno kreirati objekta klase koja će implementirati
	 * repozitorijum AriklRepository i proslediti klasi ArtiklRestController referencu
	 * na taj objekat.
	 */

	@Autowired
	private ArtiklRepository artiklRepository;

	/*
	 * HTTP GET je jedna od HTTP metoda koja je analogna opciji READ iz CRUD operacija.
	 * Anotacija @GetMapping se koristi kako bi se mapirao HTTP GET zahtev.
	 * Predstavlja skraćenu verziju metode @RequestMapping(method = RequestMethod.GET)
	 * U konkretnom slučaju HTTP GET zahtevi (a to je npr. svako učitavanje stranice u
	 * browser-u) upućeni na adresu localhost:8082/artikl biće prosleđeni ovoj metodi.
	 *
	 * Poziv metode artiklRepository.findAll() će vratiti kolekciju koja sadrži sve
	 * artikala koji će potom u browseru biti prikazani u JSON formatu
	 */

	@GetMapping("artikl")
	public Collection<Artikl> getAll(){
		return artiklRepository.findAll();
	}

	/*
	 * U slučaju metode getOne(), novina je uvođenje promenljive koja je predstavljena kao
	 * {id} u @GetMapping("artikl/{id}"). Mapiranje promenljive u vrednost koja se prosleđuje
	 * konkretnoj metodi getOne() vrši se upotrebom anotacije @PathVariable.
	 * U konkretnom slučaju HTTP GET zahtev upućen na adresu localhost:8082/artikl/1 biće
	 * prosleđen ovoj metodi, a vrednost 1 kao ID.
	 *
	 *  Poziv metode artiklRepository.getOne(id) će vratiti konkretan artikal sa datim ID-je
	 *  i taj artikal će potom biti prikazan u browseru u JSON formatu.
	 */

	@GetMapping("artikl/{id}")
	public Artikl getOne(@PathVariable("id") Integer id) {
		Artikl artikl = artiklRepository.getOne(id);
		return artikl;
	}

}
