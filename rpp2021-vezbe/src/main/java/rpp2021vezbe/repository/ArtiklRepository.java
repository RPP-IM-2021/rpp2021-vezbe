package rpp2021vezbe.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021vezbe.model.Artikl;

public interface ArtiklRepository extends JpaRepository<Artikl, Integer> {

	/* JPA omogućava manuelno kreiranje upita, kao što je npr. kreiran upit
	 * sa anotacijom @NamedQuery u klasi Artikal, ali i kreiranje upita na osnovu naziva
	 * metoda. Postoji određen broj rezervisanih reči koje možete koristiti u nazivima
	 * metode, a koje če omogućiti da se iz samog naziva automatski generiše upit.
	 * Neke od rezervisanih reči su npr. StartsWith, EndsWith, NotContaining, Containing,
	 * Contains. And, After, Like, OrderBy itd. U konkretnom slučaju upotrebom rezervisanih
	 * reči definisana je metoda koja će pronalaziti sve artikle koja u nezivu sadrži prosleđeni
	 * String ingorišući da li ja napisan malim ili valikim slovima.
	 */

	Collection<Artikl> findByNazivContainingIgnoreCase(String naziv);

}
