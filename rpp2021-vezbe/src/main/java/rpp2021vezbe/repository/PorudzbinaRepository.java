package rpp2021vezbe.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021vezbe.model.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer>{

	Collection<Porudzbina> findByPlacenoTrue();

}
