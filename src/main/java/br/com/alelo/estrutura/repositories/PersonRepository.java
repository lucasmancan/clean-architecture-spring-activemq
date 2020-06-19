package br.com.alelo.estrutura.repositories;

import br.com.alelo.estrutura.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByDocument(String document);
}
