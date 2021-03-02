package rpp2021vezbe.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Anotacije - Java programski jezik, počevši od verzije 5.0 podržava anotacije.
 * Anotacije predstavljaju rezervisane reči koje počinju znakom @.
 * Anotacije definišu kontekst za određene klase, metode ili varijable instanci.
 */

/*
 * @RestController - predstavlja anotaciju koja se koristi na nivou klase.
 * @RestController anotacija je kobinaciju anotacija @Controller i @ResponseBody
 * @Controller - anotacija koja se koristi da bi se označilo da se radi o klasi koja je
 * Spring Controller i može se koristiti u npr. Spring MVC
 * @ResponseBody - označava da rezultat izvršavanja treba da bude smešten u Response Body u
 * željenom formatu (podrazumevani format je JSON).
 * Više informacija HTTP porukama možete pronaći na linku
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages
 */

@RestController
public class HelloRestController {


	/*
	 * @RequestMapping - predstavlja anotaciju koja se može koristiti i na nivou klase i na
	 * nivou metode. Služi za mapiranje web zahteva na određene klase ili metode. U zagradi
	 * se navodi deo URI-ja koji predstavlja putanju. U slučaju metode helloWorld(),
	 * @RequestMapping("/") označava da će ova metoda biti pozvana kada se u browseru unese
	 * adresa localhost:8082
	 */

	@RequestMapping("/")
	public String helloWorld() {
		return "Hello world!";
	}

	@RequestMapping("/zbir")
	public String zbir() {
		long x = Math.round(Math.random()*10);
		long y = Math.round(Math.random()*10);
		return x + " + " + y + " = " + (x+y);
	}

}
