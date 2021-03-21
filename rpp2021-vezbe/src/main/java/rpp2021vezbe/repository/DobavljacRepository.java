package rpp2021vezbe.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021vezbe.model.Dobavljac;

public interface DobavljacRepository extends JpaRepository<Dobavljac, Integer>{

	Collection<Dobavljac> findByNazivContainingIgnoreCase(String naziv);

}
